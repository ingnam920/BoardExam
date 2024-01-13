package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Http요청을 받아서 응답을 하는 컴포넌트 , 스프링부트가 자동으로 Bean으로 생성한다.
public class BoardController {
    //게시물 목록을 보여준다.
    //컨트롤러의 메소드가 리턴하는 문자열은 템플릿 이름이다.
    // http://localhost:8080 / ----> 스프링 내부적으로 "list"리턴받아서 "list"라는 이름의 템플릿을 사용(Forward)하여 화면에 출력.
    //list를 리턴한다는것은 classpath:/(resources)templates/list.html list를 사용한다는것이다 . 확장자는 html이다
    @GetMapping("/") // /에대한 요청을 받아들여서 처리하는 메인페이지
    public String list(){
        //게시물의목록을읽어온다, 페이징처리(todo)
        return "list"; //리스트란이름의 템플릿을 리턴한다.
    }

    // 글의제목태그아이디 boardid?=1 , board=2 ....처리하기위해
    @GetMapping("/board")
    public String board(
            @RequestParam("id") int id
    ){
        //해당게시물을 가져온다.(todo)
        //id의 해당하는 게시글의 조회수도 1 증가(todo)
        return "board";
    }
    @GetMapping("/writeForm")
    public String writeForm(){
        //로그인한사용자만 글을 써야한다 .(todo)
        //세션에서 로그인한 정보를 읽어들인다. 로그인을 하지 않았따면 리스트보기로 자동 이동시킨다.(todo)
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
        @RequestParam("title") String title,
        @RequestParam("content") String content){

        //로그인한 회원정보 , 사용자가 입력한 제목 내용을 저장한다
        return "redirect:/";
    }

    //삭제한다(todo)
    //수정한다(todo)
}
