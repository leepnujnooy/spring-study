package com.example.producer.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작업을 식별하기 위한 아이디 (UUID)
    private String jobId;

    // 작업의 상태를 저장하기위한 status
    // IDLE : 처리 시작 전
    // PROCESSING : consumer 에 의해 처리 시작
    // DONE : 처리 끝
    private String status;

    // encoding 이 끝난 영상이 위치할 url
    private String resultPath;
}
