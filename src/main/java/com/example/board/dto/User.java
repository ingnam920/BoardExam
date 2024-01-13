package com.example.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//기본생성자가 자동으로 만들어진다.
@Setter
@Getter
@NoArgsConstructor
public class User {
    private int userId;
    private String email;
    private String name;
    private String password;
    private String regdate; // 원래는 날짜 type으로 읽어온후 문자열로 변환


}
