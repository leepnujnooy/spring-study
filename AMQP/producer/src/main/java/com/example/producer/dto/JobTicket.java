package com.example.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class JobTicket {
    //사용자에게 요청의 처리상태를 확인할 수 있는 JobID 를 반환하는 DTO
    private String jobId;
}
