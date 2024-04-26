package com.flab.Mytube.controller;

import com.flab.Mytube.dto.Test;
import com.flab.Mytube.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping
    public void addTest(@RequestBody Test.AddTestParam param){
        testService.addTest(param);
    }
}
