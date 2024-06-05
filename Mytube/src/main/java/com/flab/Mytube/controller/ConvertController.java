package com.flab.Mytube.controller;

import com.flab.Mytube.service.ConvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import java.net.http.HttpHeaders;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConvertController {
    private final ConvertService convertService;
    @ResponseBody
    @GetMapping("/convert/hls/{movie}")
    public String convertToHls(
            @PathVariable("movie") String name
    ) {
        convertService.convertToHls(name);
        return "success";
    }

    @GetMapping("/hls/{fileName}.m3u8")
    public ResponseEntity<Resource> videoHlsM3U8(@PathVariable String fileName) {
        log.debug("************** class = {}, function = {}", this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName());
        String fileFullPath = "/Users/aristo/Desktop/2024/f-lab/project/Mytube-streaming/Mytube/src/main/resources/static/hls/forTest.mp4/master.m3u8";
        Resource resource = new FileSystemResource(fileFullPath);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".m3u8");
        headers.setContentType(MediaType.parseMediaType("application/vnd.apple.mpegurl"));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
// 진행상황 저장하는 redis