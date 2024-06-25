package com.flab.Mytube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class HlsService {
    @Value("src/main/resources/static/hls")
    private String hlsOutputPath;

    public File getHlsFile(String filename) {
        String path = hlsOutputPath + "/streamer/1/" + filename;
        System.out.println(path);
        return new File(path);
    }
}
