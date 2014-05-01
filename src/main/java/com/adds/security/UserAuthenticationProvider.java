package com.adds.security;

import com.adds.domain.Role;
import com.adds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userAuthenticationProvider")
@Transactional(readOnly = true)
public class UserAuthenticationProvider implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.adds.domain.User user = userRepository.findByUserName(username);
//            boolean enabled = true;
//            boolean accountNonExpired = true;
//            boolean credentialsNonExpired = true;
//            boolean accountNonLocked = true;

        return new User(user.getUserName(),
                user.getUserPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getRole().getId()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        return getGrantedAuthorities(getRoles(role));
    }

    public List<String> getRoles(Integer role) {
        List<String> roles = new ArrayList<String>();

        if (role == 1) {
            roles.add(Role.ROLE_USER);
            roles.add(Role.ROLE_ADMIN);
        } else if (role == 2) {
            roles.add(Role.ROLE_USER);
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
