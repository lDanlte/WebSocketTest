package su.vistar.springwebsocket.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import su.vistar.springwebsocket.dto.TemperatureDto;

/**
 *
 * @author dantonov
 */

@Service
public class TemperatureService {
    
    static private final DateFormat RESPONSE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    
    private final MessageSendingOperations<String> messaging;
    
    private TemperatureDto currentTemp;
    
    @Autowired
    public TemperatureService(MessageSendingOperations<String> messaging){
        this.messaging = messaging;
    }
    
    
    @Scheduled(fixedDelay = 4000L)
    public void sendData(){
        currentTemp = getRandomTemperatureDto();
        messaging.convertAndSend("/topic/temperature", currentTemp);
    }
    
    public TemperatureDto getRandomTemperatureDto(){
        TemperatureDto result = new TemperatureDto();
        
        result.setAreaId((int) (Math.random() * 40));
        result.setDatetime(RESPONSE_DATE_FORMAT.format(new Date()));
        result.setValue((float) (Math.random() * 40 - 20));
        
        return result;
    }

    public TemperatureDto getCurrentTemp() { return currentTemp; }
}
