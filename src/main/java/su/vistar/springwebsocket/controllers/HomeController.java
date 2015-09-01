package su.vistar.springwebsocket.controllers;

import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dantonov
 */
@Controller
public class HomeController {
    
    static private final Random RANDOM = new Random();
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute("value", RANDOM.nextInt());
        return "index";
    }
    
}
