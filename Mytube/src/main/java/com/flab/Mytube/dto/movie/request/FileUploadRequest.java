package com.flab.Mytube.dto.movie.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Getter
public class FileUploadRequest {
    private long id;
    private MultipartFile file;
    private String url;
    private long streamerId;
    private String subject;

    public FileUploadRequest(MultipartFile file, long streamerId, String subject) {
        this.file = file;
        this.streamerId = streamerId;
        this.subject = subject;
    }

    public boolean isEmptyFile() {
        return file.isEmpty();
    }

    public byte[] getBytes() {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOriginFileName(){
        return file.getOriginalFilename();
    }

    public void addPath(String path){
        this.url = path;
    }
}
