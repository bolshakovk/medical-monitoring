package liga.medical.medicalmonitoring.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.RabbitMessageDto;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDto rabbitMessageDTO, String names) throws JsonProcessingException;
    void sendError(String str);
}
