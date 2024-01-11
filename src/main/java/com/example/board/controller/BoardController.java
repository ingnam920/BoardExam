package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Http요청을 받아서 응답을 하는 컴포넌트 , 스프링부트가 자동으로 Bean으로 생성한다.
public class BoardController {
    //게시물 목록을 보여준다.
    //컨트롤러의 메소드가 리턴하는 문자열은 템플릿 이름이다.
    // http://localhost:8080 / ----> 스프링 내부적으로 "list"리턴받아서 "list"라는 이름의 템플릿을 사용(Forward)하여 화면에 출력.
    //list를 리턴한다는것은 classpath:/(resources)templates/list.html list를 사용한다는것이다 . 확장자는 html이다
    @GetMapping("/") // /에대한 요청을 받아들여서 처리하는 메인페이지
    public String list(){
        return "list"; //리스트란이름의 템플릿을 리턴한다.
    }
}
