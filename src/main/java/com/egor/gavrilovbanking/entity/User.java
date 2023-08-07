package com.egor.gavrilovbanking.entity;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(max = 120)
    private String password;

    @Column(name = "email", unique = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "tel", unique = true)
    private long tel;

    @Column(name = "amountOfMoney")
    private long amountOfMoney;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles", 
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @Column(name = "userIsNotBlocked")
    @Builder.Default
    private boolean userIsNotBlocked = true;

    @Column(name = "userIsNotExpired")
    @Builder.Default
    private boolean credentialsIsNotExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userIsNotBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsIsNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
