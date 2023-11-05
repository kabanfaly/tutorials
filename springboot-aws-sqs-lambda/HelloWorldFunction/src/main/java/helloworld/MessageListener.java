package helloworld;

import helloworld.dto.CustomerRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class MessageListener {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @SqsListener(value = "mySqsQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveStringMessage(@Payload CustomerRegistration customerRegistration, @Headers Map<String, Object> payloadHeaders) {
        log.info("message received {}, message headers {}", customerRegistration, payloadHeaders);

        var validator = factory.getValidator();
        Set<ConstraintViolation<CustomerRegistration>> violations = validator.validate(customerRegistration);
        violations.forEach(customerRegistrationConstraintViolation -> {
            log.warn("{}, {}", customerRegistrationConstraintViolation.getPropertyPath(), customerRegistrationConstraintViolation.getMessage());
        });
        log.info("{}", violations);
    }



}
