package com.example.authprac230710.configuration;

import org.springframework.context.annotation.Configuration;

//5.7 이전 : extends WebSecurityConfigurerAdapter
//6.1 이후 : 빌더 > 람다를 이용 DSL 기반 설정
//@EnableWebSecurity 는 필수가 아니다
@Configuration //웹의 보안과 관련된 설정을 담당하는 클래스.
public class WebSecurityConfiguration {

}
