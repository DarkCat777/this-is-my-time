package edu.app.server.config;

import edu.app.server.model.Authority;
import edu.app.server.model.Resource;
import edu.app.server.service.AuthorityService;
import edu.app.server.service.ResourceService;
import edu.app.server.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Iterator;
import java.util.List;

import static edu.app.server.config.Constants.LOGIN_URL;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailServiceImpl;
    private final UserService userService;
    private final AuthorityService authorityService;
    private final ResourceService resourceService;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailServiceImpl, UserService userService, AuthorityService authorityService, ResourceService resourceService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.userService = userService;
        this.authorityService = authorityService;
        this.resourceService = resourceService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JWTAuthorizationFilter jwtAuthorizationFilterBean() {
        return new JWTAuthorizationFilter(this.userService);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailServiceImpl).passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 1. Se desactiva el uso de cookies
         * 2. Se activa la configuración CORS con los valores por defecto
         * 3. Se desactiva el filtro CSRF
         * 4. Se indica que el login no requiere autenticación
         * 5. Se indica que el resto de URLs esten securizadas
         */
        /*
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthenticationFilter("/login", this.authenticationManagerBean(), this.userService, this.bCryptPasswordEncoder), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
        */
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                        .cors().and()
                        .csrf().disable()
                        .authorizeRequests()
                        .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                        .antMatchers(HttpMethod.GET, LOGIN_URL).permitAll();
        /*List<Authority> authorities = this.authorityService.getAllAuthorities();
        log.info("Cargando permisos: " + authorities.size());
        for (Authority authority : authorities) {
            log.info("Otorgando permisos a: " + authority.getAuthority());
            for (Resource resource : authority.getResources()) {
                log.info("Permitiendo aceso a: " + resource.getResource() + " mediante Http: " + resource.getHttpMethod().name());
                registry.antMatchers(resource.getHttpMethod(), resource.getResource()).hasAuthority(authority.getAuthority());
            }
        }*/
        List<Resource> resources = this.resourceService.getAllResources();
        log.info("Cargando permisos: " + resources.size());
        for (Resource resource : resources) {
            log.info("Permitiendo aceso a: " + resource.getResource() + " mediante Http: " + resource.getHttpMethod().name());
            log.info("Otorgando permisos a: " + resource.getAuthorities());
            String[] authorities = getAuthorities(resource);
            registry.antMatchers(resource.getHttpMethod(), resource.getResource()).hasAnyAuthority(authorities);
        }
        registry.and().formLogin();
        http
                .addFilterBefore(new JWTAuthenticationFilter("/login", this.authenticationManagerBean(), this.userService, this.bCryptPasswordEncoder), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    private String[] getAuthorities(Resource resource) {
        String[] authorities = new String[resource.getAuthorities().size()];
        Iterator<Authority> authorityIterator = resource.getAuthorities().iterator();
        for (int i = 0; authorityIterator.hasNext(); i++) {
            authorities[i] = authorityIterator.next().getAuthority();
        }
        return authorities;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
