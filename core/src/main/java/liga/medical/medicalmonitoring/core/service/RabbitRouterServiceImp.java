package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.model.MessageType;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitRouterServiceImp implements RabbitRouterService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;

    public RabbitRouterServiceImp(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, RabbitSenderService rabbitSenderService) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.rabbitSenderService = rabbitSenderService;
    }
    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDTO rabbitMessageDTO = objectMapper.readValue(message, RabbitMessageDTO.class);
            System.out.println(objectMapper.readValue(message, RabbitMessageDTO.class));
            MessageType messageType = rabbitMessageDTO.getType();
            switch (messageType){
                case DAILY:
                    rabbitSenderService.sendMessage(rabbitMessageDTO, QueueNames.DAILY_QUEUE_NAME);
                    System.out.println("Send to daily. Content: " + rabbitMessageDTO.getContent() + ". Type: " + rabbitMessageDTO.getType());
                    break;
                case ALLERT:
                    rabbitSenderService.sendMessage(rabbitMessageDTO, QueueNames.ALERT_QUEUE_NAME);
                    System.out.println("Send to Alert: " + rabbitMessageDTO);
                    break;
                case ERROR:
                    rabbitSenderService.sendMessage(rabbitMessageDTO, QueueNames.ERROR_QUEUE_NAME);
                    System.out.println("Send to error: " + rabbitMessageDTO);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
