package com.finance.finance_dashboard.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setActive(boolean active) { this.active = active; }

    public void setRole(Role role) { this.role = role; }
}