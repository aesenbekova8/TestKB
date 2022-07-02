package com.example.testkb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5)
    private String username;

    @NotBlank
    @Size(min=5)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public User(String username,
                String password,
                Set<Role> roles,
                Bank bank) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.bank = bank;
    }

    public User(Long id,
                String password,
                Set<Role> roles,
                Bank bank) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.bank = bank;
    }
}
