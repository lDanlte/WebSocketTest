package su.vistar.springwebsocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import su.vistar.springwebsocket.dto.TemperatureDto;
import su.vistar.springwebsocket.service.TemperatureService;

/**
 *
 * @author dantonov
 */

@Controller
public class SocketController {
    
    @Autowired
    private TemperatureService tempService;
    
    
    //@SendTo("/topic/temperature")
    @SubscribeMapping("/temperature")
    public TemperatureDto socket(){
        return tempService.getCurrentTemp();
    }
}
