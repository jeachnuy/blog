package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> Java(다른언어)Object -> 테이블로 맵핑해주는 기술
@Entity // User클래스가 mySQL에 테이블이 생성된다.
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false,length = 30)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role;

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
