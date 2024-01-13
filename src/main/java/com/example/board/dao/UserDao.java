package com.example.board.dao;

import com.example.board.dto.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// spring이 관리하는 bean
@Repository
public class UserDao {
    //Spring JDBC를 이용한 코드.
    @Transactional
    public User addUser(String email, String name, String password){
        //Serivce에서 이미 트랜잭션이 시작했기때문에 그 트랜잭션이 포함된다. 위에@Transactionmal로 새로운게 만들어지지 않는다.
         // insert into user (email,name,password,regdate) values (?,?,?,now());
        // select LAST_INSERT_ID();

        return null;
    }

    @Transactional
    public void mappingUserRole(int userId){
        // insert into user_role(user_id,role_id) values (?,1);
    }
}
