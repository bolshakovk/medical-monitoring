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
@RequiredArgsConstructor
public class RabbitRouterServiceImp implements RabbitRouterService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;


    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDTO rabbitMessageDTO = objectMapper.readValue(message, RabbitMessageDTO.class);
            MessageType messageType = rabbitMessageDTO.getMessageType();
            switch (messageType){
                case DAILY:
                    rabbitSenderService.sendMessage(rabbitMessageDTO, QueueNames.DAILY_QUEUE_NAME);
                    break;
                case ALLERT:
                    rabbitSenderService.sendMessage(rabbitMessageDTO, QueueNames.ALERT_QUEUE_NAME);
                    break;
            }
        }catch (Exception e){
            rabbitSenderService.sendError(e.getMessage());
        }
    }

}
