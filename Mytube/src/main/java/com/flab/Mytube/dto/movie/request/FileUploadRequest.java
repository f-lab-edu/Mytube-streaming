package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
public class FileUploadRequest {
    private long id;
    private MultipartFile file;
    private String url;
    private long chanelId;
    private String subject;

    @Builder
    public FileUploadRequest(MultipartFile file, long chanelId) {
        this.file = file;
        this.chanelId = chanelId;
    }

    public void addSubject(){
        try{
            subject = file.getOriginalFilename().split("\\.")[0];
        }catch (Exception e){
            subject= file.getOriginalFilename();
        }
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

    public void addPath(String path, String name){
        StringBuilder sb = new StringBuilder(path);
        sb.append("/"+name);
        this.url = sb.toString();
    }
}
