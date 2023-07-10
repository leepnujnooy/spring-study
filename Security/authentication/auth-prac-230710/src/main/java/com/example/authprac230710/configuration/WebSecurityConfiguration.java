package com.example.authprac230710.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//5.7 이전 : extends WebSecurityConfigurerAdapter
//6.1 이후 : 빌더 > 람다를 이용 DSL 기반 설정
//@EnableWebSecurity 는 필수가 아니다
@Configuration //웹의 보안과 관련된 설정을 담당하는 클래스.
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authHttp -> authHttp
                                .requestMatchers("/users").permitAll() // "/" 는 누구든 들어올 수 있다
                                .requestMatchers("/users/main","/users/logout").authenticated() // "main" 은 인가된 사용자만 접근할 수 있다
                                .requestMatchers("/users/signup").anonymous() // "signup" 은 미인증 사용자만 접근할 수 있다
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/users") //로그인 경로
                                .defaultSuccessUrl("/users/main") //로그인 성공시 디폴트 경로
                                .failureUrl("/users/login?fail") //로그인 실패시 경로
                                .permitAll()
                )
                .logout(
                        logOut -> logOut
                                .logoutUrl("/users/logout") //로그아웃 경로
                                .logoutSuccessUrl("/users") //로그아웃 성공시 경로
                );
                return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
