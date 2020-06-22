package edu.app.server.service.security;

import edu.app.server.model.Authority;
import edu.app.server.model.Resource;
import edu.app.server.service.AuthorityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailServiceImpl;
    private final AuthorityService authorityService;

    public SpringSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailServiceImpl, AuthorityService authorityService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.authorityService = authorityService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailServiceImpl).passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.httpBasic().and().authorizeRequests();
        List<Authority> authorities = this.authorityService.getAllAuthorities();
        for (Authority authority : authorities) {
            for (Resource resource : authority.getResources()) {
                registry.antMatchers(resource.getHttpMethod(), resource.getResource()).hasAuthority(authority.getAuthority());
            }
        }
        if (authorities.isEmpty()) {
            registry.anyRequest().authenticated();
        }
        registry.and()
                .csrf().disable()
                .formLogin().disable();
    }
}
