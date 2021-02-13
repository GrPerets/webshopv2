package com.grperets.webshopv2.service.impl;

import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.model.Status;
import com.grperets.webshopv2.model.User;
import com.grperets.webshopv2.repository.RoleRepository;
import com.grperets.webshopv2.repository.UserRepository;
import com.grperets.webshopv2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user;

    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User user) {
        Role role = roleRepository.findByRolename("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User createUser = this.userRepository.save(user);

        log.info("IN create - manager: {} successfully created", createUser);
        return createUser;
    }

    @Override
    public User update(User user) {
        User existingUser = this.userRepository.findById(user.getId()).orElse(new User());
        //existingUser.setId(user.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        User updateUser = this.userRepository.save(existingUser);

        return updateUser;
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);

    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
