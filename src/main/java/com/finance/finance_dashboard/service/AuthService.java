package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.entity.Role;
import com.finance.finance_dashboard.entity.User;
import com.finance.finance_dashboard.repository.RoleRepository;
import com.finance.finance_dashboard.repository.UserRepository;
import com.finance.finance_dashboard.security.JwtService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    public String register(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("ANALYST");

        if (role == null) {
            role = new Role();
            role.setName("ANALYST");
            roleRepository.save(role);
        }

        user.setRole(role);

        userRepository.save(user);

        return "User registered";
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new RuntimeException("User not found");

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");

        return jwtService.generateToken(email);
    }
}