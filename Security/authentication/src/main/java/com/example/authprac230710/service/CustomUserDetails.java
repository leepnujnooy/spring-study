package com.example.authprac230710.service;

import com.example.authprac230710.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    @Getter
    private Long id;

    private String username;
    private String password;

    @Getter
    private String nickname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    //UserEntity -->> UserDetails
    public CustomUserDetails newUserDetails(UserEntity userEntity){
        return CustomUserDetails
                .builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .build();
    }

    //UserDetails -->> UserEntity
    public UserEntity newUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setNickname(nickname);
        return userEntity;
    }
}
