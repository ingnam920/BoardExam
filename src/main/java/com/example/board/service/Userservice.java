package com.example.board.service;

import com.example.board.dao.UserDao;
import com.example.board.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 트랜잭션 단위로 실행될 메소드를 선언하고 있는 클래스 ( 인터페이스 연습 )
@Service
// @RequiredArgsConstructor // 생성자를 안만들어도 lombok이 final필드를 초기화하는 생성자를 자동으로 생성.
//스프링이 관리해주는 Bean
public class Userservice {
    private final UserDao userDao;
    public Userservice(UserDao userDao){ // spring이 Userservice를 빈으로 생성할때 생성자를 이용해 생성을 하는데
        //이때 UserDao bean이 있는지 보고 그빈을 주입한다. 생성자 주입.
        this.userDao=userDao;
    }
    //보통 서비스에서는 @tran~붙여서 하나의 트랜잭션으로 처리하게한다.
    //Spring boot는 트랜잭션을 처리해주는 트랜잭션 관리자를 가지고있음.
    @Transactional //일단 서비스가가지고있는 메서드에는 붙이자.
    public User addUser(String name, String email, String password){
        //@Transactional 때문에 여기서 틀잰잭션이 시작된다.
        /*insert into user (email,name,password,regdate) values (?,?,?,now());
          select LAST_INSERT_ID();
          insert into user_role(user_id,role_id) values (?,1);*/
        //이3개의 쿼리가 실행
        User user = userDao.addUser(name,email,password);
        userDao.mappingUserRole(user.getUserId());

        return user;
        //트랜잭션이 끝난다.
    }
}
