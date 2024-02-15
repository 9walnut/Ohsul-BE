package com.Ohsul.Ohsul.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;

@Component
@Slf4j
public class CustomAuthFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      HttpSession sesseion = request.getSession();
      log.warn("session id {}", sesseion.getId());

      Object userId = sesseion.getAttribute("userId");
      log.warn("userNumber {}", userId);
      if(userId != null){
        // 1. 사용자 정보를 담느느 공간? 토큰 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(String.valueOf(userId), null, AuthorityUtils.NO_AUTHORITIES);
        // 2. SecurityContextHolder 에 authentication 정보 set
        // SecurityContextHolder : 클라이언트 요청 -> 응답 사이에 일시적으로 auth 정보를 저장할 수 있는 공간
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SecurityContextHolder.getContext()
      }
    } catch (Exception e){
      log.error("filter error {}", e.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}
