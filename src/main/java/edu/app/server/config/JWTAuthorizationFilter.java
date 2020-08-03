package edu.app.server.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static edu.app.server.config.Constants.*;

import edu.app.server.model.User;
import edu.app.server.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public JWTAuthorizationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        log.info(req.getPathInfo() + "|" + req.getContextPath() + "|" + req.getServletPath());
        String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
        if (StringUtils.isEmpty(header) || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        String token = header.replace(TOKEN_BEARER_PREFIX + " ", "");
        String username = getUsername(token);
        User userDetails = this.userService.getByUsername(username);
        log.info("DETAIL USER " + userDetails.toString());
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token, userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, User userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SUPER_SECRET_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    private String getUsername(String token) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SUPER_SECRET_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

}