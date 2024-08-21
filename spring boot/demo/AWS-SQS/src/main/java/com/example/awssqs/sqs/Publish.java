package com.example.awssqs.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.example.awssqs.dto.MessageRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sau khi event được publish thì consumer ngẫu nhiên sẽ lấy được đầu tiền
 * Có nhiều cách publish message như SqsTemplate, QueueMessagingTemplate, AmazonSQS
 * Có những cách cần dễ dàng tranfer trực tiếp object (QueueMessagingTemplate: tự động convert json rồi send)
 * Có những cách cần convert qua String (json) trước khi publish
 *
 * 3 Cách tương ứng 3 file config bean
 */
@RestController("")
public class Publish {
    @Value("${sqs.url}")
    private String sqsUrl;

    @Autowired
    @Qualifier("publish")
    private SqsTemplate sqsTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    private AmazonSQS amazonSQS;

    /**
     * String message as url parameter
     * @param message
     * @return
     */
    @PostMapping("/v1/publish/{msg}")
    public ResponseEntity<String> postMessage(@PathVariable("msg") String message) {
        sqsTemplate.send(sqsUrl, message);
        return new ResponseEntity<>("Message Published", HttpStatus.OK);
    }

    /**
     * Message object in request body
     * Send to topic as string value json
     *
     * @param message
     * @return
     */
    @PostMapping("/v1/publish")
    public ResponseEntity<String> postMessageObject1(@RequestBody MessageRequestDto message) {
        try {
            String messageBody = objectMapper.writeValueAsString(message);
            sqsTemplate.send(sqsUrl, messageBody);
            System.out.println("send1: " + messageBody);
            return new ResponseEntity<>("Message Published", HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Message object in request body
     * Send to object, consumer received object
     *
     * @param message
     * @return
     */
    @PostMapping("/v2/publish")
    public ResponseEntity<String> postMessageObject2(@RequestBody MessageRequestDto message) {
        try {
            // Methods support auto convert and sends
            queueMessagingTemplate.convertAndSend(sqsUrl, message);
            System.out.println("send2: " + message);
            return new ResponseEntity<>("Message Published", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Message object in request body
     * Send to topic as string value json
     *
     * @param message
     * @return
     */
    @PostMapping("/v3/publish")
    public ResponseEntity<String> postMessageObject3(@RequestBody MessageRequestDto message) {
        try {
            amazonSQS.sendMessage(sqsUrl, objectMapper.writeValueAsString(message));
            System.out.println("send3: " + message);

            return new ResponseEntity<>("Message Published", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}