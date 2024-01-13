package com.example.board.dao;

import com.example.board.dto.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;

// spring이 관리하는 bean
@Repository
public class UserDao {
    //Spring JDBC를 이용한 코드.
    private final NamedParameterJdbcTemplate jdbcTemplate;
    //이렇게 선언되면 생성자에서 바로 값을 초기화해야한다
    private SimpleJdbcInsertOperations insertUser;
    //쉽게 인서트해주는 인터페이스
    public UserDao(DataSource dataSource){ // yml파일의 소스에 접속과 관련된 설정을해주면 히카리라고 불리는 데이터 소스
        //를 구현해주는 객체가 생성된다, 생성자에다가 적어주면 스프링이 자동으로 빈을 생성하면서 넣어준다.
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertUser = new SimpleJdbcInsert(dataSource).withTableName("user").usingGeneratedKeyColumns("user_id");
                //자동으로 증가되는 id설정.
        //위 simplejdbcinsertoperations 인터페이스 구현하고있는 객체(simplejdbcinsert) 선언
    }


    @Transactional
    public User addUser(String email, String name, String password){
        //Serivce에서 이미 트랜잭션이 시작했기때문에 그 트랜잭션이 포함된다. 위에@Transactionmal로 새로운게 만들어지지 않는다.
         // insert into user (email,name,password,regdate) values (?,?,?,now());
        // select LAST_INSERT_ID();
        User user =new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegdate(LocalDateTime.now());
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        //Dto를 넣어줘야함 그러면 params(파라미터)로 바꿔줌
        //각 칼럼에 자동으로 값을 대입해줌
        // insertUser.execute(params); // 그 params 를 넣어주게된다.
        Number number= insertUser.executeAndReturnKey(params); // 위와다르게 내부적으로 자동으로 생성된 아이디를 리턴해줄수잇음
        int userid=number.intValue();
        user.setUserId(userid);
        return user;
    }

    @Transactional
    public void mappingUserRole(int userId){
        // insert into user_role(user_id,role_id) values (?,1);
        String sql = "insert into user_role(user_id,role_id) values (:userId,1)";
        SqlParameterSource params = new MapSqlParameterSource("userId",userId);
        jdbcTemplate.update(sql, params);
    }

    @Transactional
    public User getUser(String email) {
        String sql= "select user_id, email, name, password, regdate from user where email= :email";
        //select도 jdbtemplate이용
        SqlParameterSource params = new MapSqlParameterSource("email",email); // "email"을 외부로부터 들어온 email로 채워줘
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);
        // User.class저보를 통해서 각각의 컬럼을 매핑해주는 로우매퍼
        User user =jdbcTemplate.queryForObject(sql,params,rowMapper ); //queryforobject는 유저를 리턴
                //row매퍼가 dto와 동일한 규칙을 갖고있다면 빈 프로퍼티로우매퍼를 이용해줄수있다.
        return user;
    }
}
