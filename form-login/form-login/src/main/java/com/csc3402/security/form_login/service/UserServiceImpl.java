package com.csc3402.security.form_login.service;

import com.csc3402.security.form_login.dto.UserDto;
import com.csc3402.security.form_login.model.Role;
import com.csc3402.security.form_login.model.User;
import com.csc3402.security.form_login.repository.RoleRepository;
import com.csc3402.security.form_login.repository.UserRepository;
import com.csc3402.security.form_login.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        // Fetch or create ROLE_USER
        Role userRole = roleRepository.findByName(TbConstants.Roles.USER);
        if (userRole == null) {
            userRole = roleRepository.save(new Role(TbConstants.Roles.USER));
        }

        // Fetch or create ROLE_ADMIN
        Role adminRole = roleRepository.findByName(TbConstants.Roles.ADMIN);
        if (adminRole == null) {
            adminRole = roleRepository.save(new Role(TbConstants.Roles.ADMIN));
        }

        // Assign both roles
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        roles.add(adminRole);

        User user = new User(
                userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                roles
        );

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
