package su.vistar.springwebsocket.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.vistar.springwebsocket.dto.TemperatureDto;

/**
 *
 * @author dantonov
 */

@Controller
@RequestMapping("/api/status")
public class DataController {
    
    static private final DateFormat RESPONSE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    
    @RequestMapping(value = "/{areaId}/temperature",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TemperatureDto getTemperature(@PathVariable Long areaId){
        TemperatureDto result = new TemperatureDto();
        
        result.setAreaId(areaId);
        result.setDatetime(RESPONSE_DATE_FORMAT.format(new Date()));
        result.setValue((float) (Math.random() * 40 - 20));
        
        return result;
    }
    
}
