package com.flab.Mytube.controller;

import com.flab.Mytube.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ConvertController {
    private final ConvertService convertService;
    @ResponseBody
    @PostMapping("/convert/hls/{movie}")
    public String convertToHls(
            @PathVariable("movie") String name
    ) {
        convertService.convertToHls(name);
        return "success";
    }
}
