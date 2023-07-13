package com.example.producer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class JobPayload {
    // producer 가 발생시킬 잡을 정의한 DTO
    private String jobId;
    private String filename;
    private String path;
}
