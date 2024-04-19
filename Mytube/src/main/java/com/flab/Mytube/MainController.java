package com.flab.Mytube;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("posts")
    @ResponseBody
    public String posts(){
        return "index";
    }

}
