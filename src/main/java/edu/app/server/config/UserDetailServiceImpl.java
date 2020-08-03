package edu.app.server.config;

import edu.app.server.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        edu.app.server.model.User user = this.userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The Username: " + username + " don't exist in database.");
        }
        log.info("Authorities: " + user.getAuthorities());
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
