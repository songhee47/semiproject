package com.example.demo.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // SecurityFilterChain : 스프링 시큐리티에서 적용할 보안규칙들을 필터로 구현해 둔 것
    // HTTP Basic 인증
    // 세션/쿠키 기반 인증과 달리, 서버가 별도의 "로그아웃" 처리를 하지 않음
    // 매 요청마다 클라이언트가 Authorization 헤더에 아이디/비밀번호를 실어 보내는 방식
    // 서버 입장: 별도의 세션이 없으므로, 서버에서 "로그아웃" 처리를 할 필요가 없음
    // 클라이언트 입장: 브라우저나 API 클라이언트가 Authorization 헤더를
    // 더 이상 보내지 않으면 "로그아웃"  =>  완전히 로그아웃하려면 브라우저를 닫음
    // SecurityFilterChain 빈 등록: Spring Security의 필터 체인을 구성하는 메서드
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF(Cross-Site Request Forgery) 보호 기능 비활성화
            // REST API 서버에서는 일반적으로 CSRF를 사용하지 않으므로 disable
            .csrf(csrf -> csrf.disable())

            // 요청에 대한 인가(Authorization) 규칙 설정
            .authorizeHttpRequests(auth -> auth
                // 모든 요청(anyRequest)에 대해 인증 필요 (로그인해야 접근 가능)
                .anyRequest().authenticated()
            )

            // HTTP Basic 인증 방식 활성화
            // realmName은 인증 창에 표시되는 영역 이름(옵션)
            .httpBasic(httpBasic -> httpBasic.realmName("HttpBasic"));

        // 설정를 적용해서 SecurityFilterChain 객체 반환
        return http.build();
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화 (API용)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/board/list").hasRole("ADMIN") // 관리자 권한 필요
                .requestMatchers("/member/logout").hasRole("USER") // 인증 필요
                .anyRequest().permitAll() // 그 외는 모두 허용
            )
            .httpBasic(httpBasic -> httpBasic.realmName("HttpBasic")); // HTTP Basic 인증 활성화
        return http.build();
    }
    // UserDetailsService 빈 등록: 사용자 정보를 메모리에서 관리하는 서비스
    @Bean
    public UserDetailsService userDetailsService() {
        // 일반 사용자 계정 생성: username = "user", password = "user123", 권한 = "USER"
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user123")) // 비밀번호는 암호화 필요
            .roles("USER")
            .build();

        // 관리자 계정 생성: username = "admin", password = "admin123", 권한 = "USER", "ADMIN"
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("USER", "ADMIN")
            .build();

        // 위에서 만든 두 계정 정보를 InMemoryUserDetailsManager에 등록 (메모리 기반 사용자 관리)
        return new InMemoryUserDetailsManager(user, admin);
    }

    // PasswordEncoder 빈 등록: 비밀번호를 암호화하기 위한 인코더(Bcrypt 알고리즘 사용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
