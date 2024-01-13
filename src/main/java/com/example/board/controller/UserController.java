package com.example.board.controller;

import com.example.board.dto.LoginInfo;
import com.example.board.dto.User;
import com.example.board.service.Userservice;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final Userservice userservice; // 자동으로 생성자 초기화해주는 롬복때매
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

        System.out.println(name);
        System.out.println(email);
        userservice.addUser(name,email,password);

        return "redirect:/welcome"; //브라우저에게 자동으로 http://lcoalhost:8080/welcom으로 이동
        //welcome으로 리다이렉트하게되면 (GET방식으로/welcome경로를 요청)
        //서버는 당연히 Get방식으로 /welcome을 처리할수있어야한다 ??
    }
    //http://lcoalhost:8080/welcom --------> 요청왔을때 /welcome 이라는 템플릿사용됨
    @GetMapping ("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/loginForm") // /loginForm이라는 요청이들어오면 login이란 템플릿이 보여지도록
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/login")
    //login은 loginForm에서 전달해주는 폼을 받아야하므로

    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession httpSession
            //컨트롤러의 메서드 뒤에다가 세션설정 제일쉬움 , 스프링이 자동으로 세션을 처리하는 HTTP세션객체
    ){
        //email에 해당하는 회원정보읽어온후 비번이 맞다면 세션에 회원정보를 저장한다.(todo)
        try{
            User user=userservice.getUser(email);
            if(user.getPassword().equals(password)){
                System.out.println("암호가같다");
                LoginInfo loginInfo = new LoginInfo(user.getUserId(),user.getEmail(),user.getName());
                httpSession.setAttribute("loginInfo", loginInfo); //첫번째파라미터 key, 두번째 값.
                //세션은 현 부라우저사용자만 접근 각각 다른사용자의 브라우저마다 다르게설정
                System.out.println("세션에 로그인정보저장");

            }else{
                throw new RuntimeException("일치하지않아");
            }
        }catch (Exception e){
            return "redirect:/loginForm?error=true";
        }
        return "redirect:/";
    }

    //list.html에서 logout 태그를 선택하면 게시물목록으로 이동하도록
    //logout을 Get방식으로 요청 응답값옴(*302) -> 브라우저한테 자동 리다이렉트 응답시키는것
    @GetMapping("/logout")
        public String logout(){
        //세션에서 회원정보를 삭제한다(todo)
            return "redirect:/"; //
        }

}
