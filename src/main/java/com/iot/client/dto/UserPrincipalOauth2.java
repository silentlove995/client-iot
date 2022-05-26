package com.iot.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.client.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipalOauth2 implements UserDetails, Principal {

    private String username;

    @JsonIgnore
    private String password;

    private String fullName;
    private Long userId;
    private String phoneNumber;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipalOauth2(String username, String password , String phoneNumber , String fullName , Long userId , Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userId = userId;
        this.authorities = authorities;
        this.phoneNumber = phoneNumber;
    }

    public static UserPrincipalOauth2 createPrincipalOauth2(UserEntity userEntity){
        List<GrantedAuthority> authorities = userEntity.getRoles().stream().map(roleEntity ->
                new SimpleGrantedAuthority(roleEntity.getCode())
        ).collect(Collectors.toList());
        return new UserPrincipalOauth2(
                userEntity.getNumberPhone(),
                userEntity.getPassword(),
                userEntity.getNumberPhone(),
                userEntity.getFullName(),
                userEntity.getId(),
                authorities
        );
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
