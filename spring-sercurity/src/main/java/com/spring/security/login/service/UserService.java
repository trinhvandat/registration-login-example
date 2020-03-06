package com.spring.security.login.service;

import com.spring.security.login.model.CustomUserDetails;
import com.spring.security.login.model.User;
import com.spring.security.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException(username);
        }

        System.out.println(user);
        return new CustomUserDetails(user);
    }


    public UserDetails loadUserById(Integer id) {

        User user = repository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + id)
        );

        return new CustomUserDetails(user);
    }
}
