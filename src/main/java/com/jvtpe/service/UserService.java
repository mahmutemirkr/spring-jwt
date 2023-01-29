package com.jvtpe.service;

import com.jvtpe.controller.dto.RegisterRequest;
import com.jvtpe.domain.Role;
import com.jvtpe.domain.User;
import com.jvtpe.domain.enums.UserRole;
import com.jvtpe.exception.ConflictException;
import com.jvtpe.exception.ResourceNotFoundException;
import com.jvtpe.repository.RoleRepository;
import com.jvtpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void registerUser(RegisterRequest registerRequest) throws ResourceNotFoundException {

        if(userRepository.existsByUserName(registerRequest.getUserName())){


            throw new ConflictException("Not Found Username");

        }

        Role role = roleRepository.findByName(UserRole.ROLE_STUDENT).orElseThrow(
                ()-> new ResourceNotFoundException("Role bilgisi bulunamadı")
        );

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUserName(registerRequest.getUserName());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

    }
}
