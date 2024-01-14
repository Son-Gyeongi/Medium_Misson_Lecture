package com.ll.mediumlecture.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
Spring Security를 사용하여 모든 요청을 허용하고,
프레임 옵션을 설정하며, 특정 요청은 CSRF 보호에서 제외하는 보안 구성
 */
@Configuration
public class SecurityConfig {

    // SecurityFilterChain 빈을 생성하는 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // HTTP 보안 설정 시작
        http
                .authorizeHttpRequests(
                        // 모든 요청에 대해 허용하는 설정
                        authorizeRequests -> authorizeRequests.requestMatchers("/**")
                                .permitAll()
                )
                .headers(
                        // 프레임 옵션 설정 (X-Frame-Options) : 웹 애플리케이션의 페이지가 다른 웹 페이지의 프레임 안에서 로드되는 것을 제어하는 보안 메커니즘 중 하나
                        headers -> headers.frameOptions(
                                frameOptions -> frameOptions.sameOrigin()
                                /*
                                SAMEORIGIN: 동일한 출처의 프레임에서만 로드가 허용
                                보안상의 이유로 사용자의 웹 페이지가 다른 웹 페이지에 삽입되는 것을 방지하고,
                                Clickjacking과 같은 공격을 예방하는 데 도움
                                 */
                        )
                )
                .csrf( // Spring Security의 CSRF(Cross-Site Request Forgery) 보호는 기본적으로 활성화
                        // CSRF(Cross-Site Request Forgery) 설정, 특정 요청은 무시
                        // 대개 개발자가 관리하는 관리자용 콘솔과 같은 특정 경로에서 CSRF 보호를 비활성화하고자 할 때 사용
                        csrf -> csrf.ignoringRequestMatchers(
                                "/h2-console/**"
                        )
                        /*
                        CSRF : 크로스 사이트 요청 위조
                        공격자가 희생자의 권한을 사용하여 특정 동작을 수행하도록 속이는 공격 유형 중 하나
                         */
                );

        // 보안 필터 체인 반환
        return http.build();
    }
}
