package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/osul/login")
public class LoginController {
  private UserService userService;
//  @GetMapping("")
//  public String home(@CookieValue(name = "userId", required=false) Long userId, Model model){
//    model.addAttribute("loginType", "cookie-login");
//    model.addAttribute("pageName", "쿠키 로그인");
//  if (loginUser != null){
//
//  }
////    User loginUser = userService.get
//  }
//  return "home";
}
