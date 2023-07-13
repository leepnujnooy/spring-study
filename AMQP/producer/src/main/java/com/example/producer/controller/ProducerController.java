package com.example.producer.controller;

import com.example.producer.dto.JobRequest;
import com.example.producer.dto.JobTicket;
import com.example.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService  producerService;

    @PostMapping("/make-job")
    public JobTicket makeJob(@RequestBody JobRequest jobRequest){
        return producerService.send(jobRequest.getFilename());
    }
}
