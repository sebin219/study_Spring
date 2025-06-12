package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    // 모든 HTTP 메서드에 대해 /sample -> basic()
    @RequestMapping("")
    public void basic() {
        log.info("basic........");
    }

    // GET, POST  -> /sample/basic
    @RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
    public void basicGet() {
        log.info("basic get............");
    }

    // GET -> /sample/basicOnlyGet
    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
    }

    // ex04 : ?name=aaa&age=11&page=9
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto,
                       @RequestParam("page") int page,
                       Model model) {
        log.info("SampleDTO: {}", dto);
        log.info("Page: {}", page);

        model.addAttribute("dto", dto);
        model.addAttribute("page", page);

        return "sample/ex04";
    }

    // ex05
    public void ex05() {
        log.info("/ex05........");
    }

    // ex06 : /sample/ex06 → /sample/ex06-2?name=AAA&age=10
    @GetMapping("/ex06")
    public String ex06(RedirectAttributes rttr) {
        rttr.addAttribute("name", "AAA");
        rttr.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    // ex06-2
    @GetMapping("/ex06-2")
    public String ex06_2(@ModelAttribute SampleDTO dto, Model model) {
        model.addAttribute("dto", dto);
        return "sample/ex06-2";
    }
}