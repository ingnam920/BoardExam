package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // 모든 필드생성자채워주게
public class LoginInfo {
    private int userId;
    private String email;
    private String name;
}
