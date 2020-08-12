package edu.app.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.app.server.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static edu.app.server.config.Constants.*;

@Log4j2
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(new AntPathRequestMatcher(url, HttpMethod.POST.name()));
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        /**
         * Podria restringirse la entrada mediante errores y comparando los recursos que tiene el usuario dentro de sus authorities y dentro de los recursos.
         */
        log.info(request.getPathInfo() + "|" + request.getContextPath() + "|" + request.getServletPath());
        try {
            edu.app.server.model.User credenciales = new ObjectMapper().readValue(request.getInputStream(), edu.app.server.model.User.class);
            edu.app.server.model.User userInBD = this.userService.getByUsername(credenciales.getUsername());
            if (this.bCryptPasswordEncoder.matches(credenciales.getPassword(), userInBD.getPassword())) {
                log.info("This is a correct account: ");
                log.info("USER in DB: " + userInBD);
                log.info("Authorities: " + userInBD.getAuthorities());
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userInBD.getUsername(), credenciales.getPassword(), userInBD.getAuthorities()));
            } else {
                throw new AuthenticationCredentialsNotFoundException("Your Credential is bad.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setIssuer(ISSUER_INFO)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
                .compact();
        log.info("GENERATE TOKEN: " + token);
        response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
    }
}
