package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 <hml>태그가 섞여서 디자인 됨.

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) //Board = Many, User = One 하나의 유저는 많은 보드를 만들수 있다.
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다 (난 FK키가 아니에) DB에 칼럼을 만들지 마세요.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
