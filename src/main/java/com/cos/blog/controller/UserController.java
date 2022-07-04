package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인이 안된 사용자들이 출입할 스 있는 경로를 /auth/**허용
//그냥 주소가 /　이면 index.jsp허용
//static이하에 있는 /js/**, /image/**
@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm() {
    return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


}
