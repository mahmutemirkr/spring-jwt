package com.jvtpe.security.service;

import com.jvtpe.domain.User;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = null;
        try {
            user = userRepository.findByUserName(username).
                    orElseThrow(()-> new ResourceNotFoundException("User not found"));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        return UserDetailsImpl.build(user);
    }


}
