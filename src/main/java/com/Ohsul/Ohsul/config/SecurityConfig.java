package com.Ohsul.Ohsul.config;


import com.Ohsul.Ohsul.security.*;
import com.Ohsul.Ohsul.service.*;
import jakarta.servlet.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.cors.*;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

  @Autowired
  CustomAuthFilter customAuthFilter;
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
            .cors(Customizer.withDefaults())
            .csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(authroize -> authroize
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .requestMatchers("/osul/**", "/register/**", "/login/**").permitAll() // 예외
                    .anyRequest().authenticated() // 어떤 요청이라도 인증 필요
            );
    http.addFilterAfter(customAuthFilter, UsernamePasswordAuthenticationFilter.class);
            // form 방식 로그인 사용
//            .formLogin(login -> login
//                    .loginPage("/login")
//                    .usernameParameter("userId")
//                    .passwordParameter("userPw")
//                    .defaultSuccessUrl("/main", true)
//                    .permitAll())
//            .logout(Customizer.withDefaults());
    return http.build();
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();

    // cors 설정
    config.setAllowCredentials(true); // 실제 응답을 보낼 때, 브라우저에게 자격 증명과 함께 요청을 보낼 수 있도록 허용합니다.
    config.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 원본에서의 요청을 허용합니다.
    config.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT", "PATCH")); // 허용할 HTTP 메서드를 설정합니다.
    config.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더의 요청을 허용합니다.


    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 위에서 설정한 CORS 설정을 적용합니다.

    return source;
  };
}
