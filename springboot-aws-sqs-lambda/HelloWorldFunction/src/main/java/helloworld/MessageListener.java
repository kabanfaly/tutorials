package helloworld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helloworld.dto.CustomerRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {
    private final ObjectMapper objectMapper;

    @SqsListener(value = "mySqsQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveStringMessage(final String message) throws JsonProcessingException {
        log.info("message received {}", message);
        CustomerRegistration customerRegistration = objectMapper.readValue(message, CustomerRegistration.class);
        log.info("object received {}", customerRegistration);
    }

}
