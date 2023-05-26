package com.example.springcrudsample.security;

import com.example.springcrudsample.domain.entity.Authority;
import com.example.springcrudsample.domain.entity.User;
import com.example.springcrudsample.exceptions.UserNotActivatedException;
import com.example.springcrudsample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);
    private UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) {
        log.debug("احراز هویت {}", login);

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        User user = userRepository.findOneByLogin(lowercaseLogin).
                orElseThrow(() -> new UsernameNotFoundException("کاربر با نام کاربری " + login + " وجود ندارد."));

        return createSpringSecurityUser(lowercaseLogin, user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("کاربر " + lowercaseLogin + " غیر فعال شده است.");
        }
        List<GrantedAuthority> grantedAuthorities = user
            .getAuthorities()
            .stream()
            .map(Authority::getName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                grantedAuthorities);
    }
}
