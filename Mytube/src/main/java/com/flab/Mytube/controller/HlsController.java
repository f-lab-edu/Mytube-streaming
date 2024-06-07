package com.flab.Mytube.controller;

import com.flab.Mytube.service.HlsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class HlsController {
    private final HlsService hlsService;

    @ResponseBody
    @GetMapping("/hls/{filename}")
    public ResponseEntity<InputStreamResource> getHlsFile(
            @PathVariable String filename
    )throws FileNotFoundException {
        File file = hlsService.getHlsFile(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/x-mpegURL"))
                .body(resource);
    }
}
