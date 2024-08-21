package com.example.awssqs.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.example.awssqs.dto.MessageRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
//import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private AmazonSQS amazonSQS;

    /**
     * Way1: Publish STRING and Param string
     * @param message
     */
    @SqsListener(value="my-first-queue")
    public void myConsumer(String message) {
        System.out.println("Received0 message:"+message);

//        acknowledgement.acknowledge();

//        sqsTemplate.receive(from -> {
//            from.queue("my-first-queue")
//                    .pollTimeout(Duration.ofSeconds(1));
//        });
    }


    /**
     * Way1: Publish JSON type STRING and Param string
     * @param message
     */
    @SqsListener(value="my-first-queue")
    public void myConsumerMessage(String message) {
        try {
            MessageRequestDto messageRequestDto = objectMapper.readValue(message, MessageRequestDto.class);
            System.out.println("Received1 message: " + messageRequestDto);

//            acknowledgement.acknowledge();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Way2: Publish object and Param object
     * @param message
     */
    @SqsListener(value="my-first-queue")
    public void myConsumerMessage2(MessageRequestDto message) {
        try {
            System.out.println("Received2 message= " + message.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Way3: No param
     */
    @Scheduled(fixedDelay = 5000)
    public void myConsumerMessage3() {
        try {
            String queueUrl = amazonSQS.getQueueUrl("my-first-queue").getQueueUrl();

            ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(queueUrl);

            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message msg = receiveMessageResult.getMessages().get(0);
                System.out.println("Received3 message= " + msg.getBody());
                amazonSQS.deleteMessage(queueUrl, msg.getReceiptHandle());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
