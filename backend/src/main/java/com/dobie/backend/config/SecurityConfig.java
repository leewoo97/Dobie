package com.dobie.backend.config;

import com.dobie.backend.security.entrypoint.JwtAuthenticationEntryPoint;
import com.dobie.backend.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //Lombok라이브러리의 어노테이션. final또는 @NonNull이 붙은 모든 필드에 대해 생성자 자동 생성 -> 의존성 주입 편리하게 수행
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final TokenProvider tokenProvider;

    @Bean // PasswordEncoder는 BCryptPasswordEncoder를 사용
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean//SecurityFilterChain에 Bean으로 등록하는 과정
    public SecurityFilterChain filterChain(HttpSecurity security) throws  Exception{

        // token을 사용하는 방식이기 때문에 csrf를 disable함
        security
                .csrf((auth) -> auth.disable())
//                .formLogin((auth) -> auth.disable()) // from 로그인 방식 disable
                .httpBasic((basic) -> basic.disable()); //http 기본 인증 비활성화(jwt 사용하니까 불필요)

        //경로별 인가 작업
        security
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                "/api-docs/**",
                                "/v3/api-docs/**",
                                "/webjars/**",
                                "/swagger/**",
                                "/swagger-ui/**",
                                "/swagger-config/**",
                                "/swagger-resources/**"
                                ).permitAll() // 문서 관련 경로는 인증 없이 접근 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN") //권한을 가진 사용자만 접근 허용
                        .anyRequest().authenticated()//로그인한 사용자만 접근 허용
                );

        //세션 설정
        security
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        security
                .exceptionHandling(handlingConfigurer -> {
            handlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint);
        });

        return security.build();
    }
}
