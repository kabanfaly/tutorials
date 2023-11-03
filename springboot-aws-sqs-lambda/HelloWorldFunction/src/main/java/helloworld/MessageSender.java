package helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helloworld.dto.CustomerRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public String index(){
        return "hello world";
    }

    @GetMapping("/send/{message}")
    public void send(@PathVariable(value = "message") String message){
        var payload = MessageBuilder.withPayload(message).build();
        log.info("Sending message {}", message);
        queueMessagingTemplate.send(endpoint, payload);
    }

    @PostMapping("/send")
    public CustomerRegistration sendData(@RequestBody CustomerRegistration message) throws JsonProcessingException {
        var payload = MessageBuilder.withPayload(objectMapper.writeValueAsString(message)).build();
        log.info("Sending message {}", message);
        queueMessagingTemplate.send(endpoint, payload);
        return message;
    }
}
