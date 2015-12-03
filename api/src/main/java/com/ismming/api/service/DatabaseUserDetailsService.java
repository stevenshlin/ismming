package com.ismming.api.service;

import com.ismming.api.domain.UserProfile;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = new UserProfile(username, username);
        if (userProfile == null) {
            throw new UsernameNotFoundException("could not find the user '" + username + "'");
        }
        return new User(
                userProfile.getUsername(),
                userProfile.getPassword(),
                userProfile.isEnabled(),
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(userProfile.getFormattedRoles())
        );
    }
}
