package com.cinetpay.billing.application.utils;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {
    //private final Logger logger = (Logger) LoggerFactory.getLogger(KafkaProducer.class);

    @KafkaListener(topics = "payment-billing", groupId = "default-kafka-group")
    public void consume(String message) throws IOException {
        //logger.info(String.format("#### -> Consumed message -> %s", message));
        System.out.println("******************"+message+"*****************");
    }
}
