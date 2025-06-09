package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /*
    * SecurityFilterChain 빈 등록
    * - 스프링 시큐리티의 각종 보안규칙(인증, 인가 등) 을 필터 체인 형태로 적용
    * - httpSecurity를 통해 다양한 보안설정 추가 가능
    *
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        * 기본 설정만 적용하여 필터체인 생성, 추가적인 보안설정 필요시 http에 메서드 체이닝으로 옵션 추가
        * */
        return http.build();
    }
}