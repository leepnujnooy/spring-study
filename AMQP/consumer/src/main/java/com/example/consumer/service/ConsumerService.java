package com.example.consumer.service;

import com.example.consumer.dto.JobPayload;
import com.example.consumer.entity.JobEntity;
import com.example.consumer.entity.JobRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
// 이 클래스는 래빗 MQ 의 Queue 에 적재되는 메세지를 받아 처리하기 위한 Queue 이다
@RabbitListener(queues = "boot.amqp.worker-queue")
public class ConsumerService {
    private final JobRepository jobRepository;
    private final Gson gson;

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        // 역직렬화
        JobPayload newJob = gson.fromJson(message, JobPayload.class);
        String jobId = newJob.getJobId();
        log.info("Received Job : {}",jobId);

        // 엔티티 검색
        Optional<JobEntity> optional = jobRepository.findByJobId(jobId);

        // 요청을 처리상태로 업베이트
        JobEntity jobEntity = optional.get();
        jobEntity.setStatus("PROCESSING");
        jobRepository.save(jobEntity);


        log.info("START PROCESSING JOB : {}",jobId);
        TimeUnit.SECONDS.sleep(5);

        jobEntity.setStatus("DONE");
        jobEntity.setResultPath(String.format("/media/user-uploaded/processed/%s",newJob.getFilename()));
        jobRepository.save(jobEntity);
    }
}
