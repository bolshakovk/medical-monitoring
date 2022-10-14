package liga.medical.medicalmonitoring.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import liga.medical.medicalmonitoring.core.model.RabbitMessageDTO;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDTO rabbitMessageDTO, String names) throws JsonProcessingException;
    void sendError(String str);
}
