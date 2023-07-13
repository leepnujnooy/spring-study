package com.example.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JobRequest {
    // 사용자가 인코딩 요청을 보내는 DTO
    private String filename;
}
