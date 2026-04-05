package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.entity.Role;
import com.finance.finance_dashboard.entity.User;
import com.finance.finance_dashboard.repository.RoleRepository;
import com.finance.finance_dashboard.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user){

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // assign default role
        Role role = roleRepository.findByName("VIEWER");

        user.setRole(role);

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User user){

        User existing = getUser(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return userRepository.save(existing);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User changeStatus(Long id, boolean active){

        User user = getUser(id);

        user.setActive(active);

        return userRepository.save(user);
    }
}