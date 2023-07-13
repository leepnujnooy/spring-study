package com.example.producer.dto;

import com.example.producer.jpa.JobEntity;
import lombok.Data;

@Data
public class JobStatus {
    //사용자에게 요청처리 상태를 알리는 DTO
    private String jobId;
    private String status;
    private String resultPath;
    public static JobStatus makeStatus(JobEntity jobEntity){
        JobStatus jobStatus = new JobStatus();
        jobStatus.setJobId(jobEntity.getJobId());
        jobStatus.setResultPath(jobEntity.getResultPath());
        jobStatus.setStatus(jobEntity.getStatus());
        return jobStatus;
    }
}
