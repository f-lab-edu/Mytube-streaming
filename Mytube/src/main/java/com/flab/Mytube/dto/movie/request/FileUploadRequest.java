package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@AllArgsConstructor
public class FileUploadRequest {
    private MultipartFile file;
    private long streamerId;
    private String subject;

    public boolean isEmptyFile(){
        if(file.isEmpty()) return true;
        return false;
    }

    public byte[] getBytes(){
        try{
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
