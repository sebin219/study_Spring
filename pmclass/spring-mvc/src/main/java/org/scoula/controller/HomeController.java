package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("================> HomeController /");
        return "index";
    }

    @GetMapping("/log")
    public String logTest() {
        log.info("INFO 로그입니다 !");
        log.warn("WARN 로그입니다 !");
        log.error("ERROR 로그입니다 !");
        return "OK";
    }
}