package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(다른언어)Object -> 테이블로 맵핑해주는 기술
@Entity // User클래스가 mySQL에 테이블이 생성된다.
//@DynamicInsert insert시에 null인 필드를 제외시켜준다
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    //DB는 RoleType이라는게 없다
    @Enumerated(EnumType.STRING)
    private RoleType role; //enum쓰는게 좋다

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
