package helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageListener {
    @SqsListener(value = "mySqsQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveStringMessage(final String message, @Header("SenderId") String senderId) {
        log.info("message received {} {}",senderId,message);
    }

}
