package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitSenderServiceImp implements RabbitSenderService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public RabbitSenderServiceImp(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMessage(RabbitMessageDTO rabbitMessageDTO, String names) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(rabbitMessageDTO);
        amqpTemplate.convertAndSend(names, messageStr);
        System.out.println(String.format("Сообщение [%s] в очередь [%s] отправлено", messageStr, names));
    }

    @Override
    public void sendError(String str) {
        amqpTemplate.convertAndSend(QueueNames.ALERT_QUEUE_NAME, str);
        System.out.println("Сообщение с ошибкой направлено в очередь error");
    }
}
