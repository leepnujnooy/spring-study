package com.example.authprac230710.service;

import com.example.authprac230710.entity.UserEntity;
import com.example.authprac230710.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        return customUserDetails.newUserDetails(optional.get());
    }
    @Override
    public void createUser(UserDetails user) {
        if(userExists(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try{
            CustomUserDetails customUserDetails = CustomUserDetails.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
            userRepository.save(customUserDetails.newUserEntity());
        }catch (ClassCastException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }


}
