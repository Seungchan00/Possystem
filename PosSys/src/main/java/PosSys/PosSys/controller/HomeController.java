package PosSys.PosSys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/") //첫번째 화면
    public String home(){
        log.info("home controller");
        return "home"; //home html

    }

}
