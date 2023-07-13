package com.example.producer.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    @Bean
    public Queue queue(){
        return new Queue(
                // name : Producer Consumer 둘다 같은 Queue 를 사용하기 위해 작성
                "boot.amqp.worker-queue",
                // durable : Producer 가 종료된 후에도 Queue 를 유지시킬건지
                true,
                // exclusive : 지금 이 서버만 Queue 를 사용할 수 있는지
                false,
                // autoDelete : 사용되고 있지 않은 Queue 를 자동 삭제 할 건지
                true);
    }
}
