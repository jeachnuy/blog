package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//DAO
//자동으로 bean등록이 된다.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
    //JPA Naming 쿼리
    //SELECT * FROM user WHERE username = ? AND password = ?;
//    User findByUsernameAndPassword(String username, String password);

    //위에와 기능 같음
//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
    Optional<User> findByUsername(String username);
}
