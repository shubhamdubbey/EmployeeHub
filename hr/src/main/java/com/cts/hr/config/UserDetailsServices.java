package com.cts.hr.config;

import com.cts.hr.entity.LoginDetails;
import com.cts.hr.repository.LoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    private LoginDetailsRepository loginDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDetails loginDetails = loginDetailsRepository.findById(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + username));
        List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + loginDetails.getRoles().name()));
        System.out.println(loginDetails.getRoles().name());
        return new User(loginDetails.getUsername(), loginDetails.getPassword(), authorityList);
    }
}