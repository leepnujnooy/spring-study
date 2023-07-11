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
        //db 에서 클라이언트의 요청에 담겨 온 username 을 사용하는 user 가 이미 존재하는지 확인
        if(userExists(user.getUsername())){
            //이미 존재한다면 예외 처리
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try{
            CustomUserDetails customUserDetails = CustomUserDetails.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
            //db에 user 저장
            userRepository.save(customUserDetails.newUserEntity());
        }catch (ClassCastException e){
            //예상치 못한 에러 처리
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
