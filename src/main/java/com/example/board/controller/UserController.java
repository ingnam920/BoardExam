package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    // 회원가입폼은 Getmapping
    // http://localhost:8080/userRegForm요청을하면
    // classpath:/templates/userRegForm.html.html
    @GetMapping ("/userRegForm") //userRegForm이 오면 회원가입
    public String userRegForm(){
        return "userRegForm";
    }

    //이후에 회원가입폼을 하고 회원가입을 누르면 서버로 /userReg방식으로 넘어오는걸 Post 받아서처리(PostMapping)
    //userReg Post 처리 후 redirect응답
    @PostMapping("/userReg")
    public String userReg(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("password") String password){
        return "redirect:/welcome"; //브라우저에게 자동으로 http://lcoalhost:8080/welcom으로 이동
        //welcome으로 리다이렉트하게되면 (GET방식으로/welcome경로를 요청)
        //서버는 당연히 Get방식으로 /welcome을 처리할수있어야한다 ??
    }
    //http://lcoalhost:8080/welcom --------> 요청왔을때 /welcome 이라는 템플릿사용됨
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
