package com.example.authprac230710.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //이 클래스는 엔티티 임을 명시하는 어노테이션
@Table(name = "users") //db에 table 로 등록될 것임을 명시하는 어노테이션
@Getter //필드 값을 가져올 수 있는 어노테이션
@Setter //필드 값을 정할 수 있는 어노테이션
@ToString //필드 값을 참조할 수 있는 어노테이션
public class UserEntity {
    @Id //해당 컬럼이 id 임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key 임을 명시
    private Long id;

    @Column(nullable = false, unique = true) // 해당 컬럼은 Not Null 이며 Unique 함을 명시
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;
}
