package liga.medical.medicalmonitoring.core.service;

import api.RabbitSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.QueueNames;
import model.RabbitMessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitSenderServiceImp  implements RabbitSenderService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public RabbitSenderServiceImp(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMessage(RabbitMessageDto rabbitMessageDTO, String names) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(rabbitMessageDTO);
        amqpTemplate.convertAndSend(names, messageStr);
        System.out.println(String.format("Message [%s] send to queue [%s]", messageStr, names));
    }

    @Override
    public void sendError(String str) {
        amqpTemplate.convertAndSend(QueueNames.ERROR_QUEUE_NAME, str);
        System.out.println("Message with" + str + " send to queue " + QueueNames.ERROR_QUEUE_NAME);
    }
}
