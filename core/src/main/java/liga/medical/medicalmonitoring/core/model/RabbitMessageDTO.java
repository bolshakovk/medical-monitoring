package liga.medical.medicalmonitoring.core.model;

import lombok.Data;

@Data
public class RabbitMessageDTO {
    private MessageType type;
    private String content;
}
