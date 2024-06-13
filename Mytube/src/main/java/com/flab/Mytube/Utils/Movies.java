package com.flab.Mytube.Utils;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
@Component
public class Movies {
    public Path createPath(FileUploadRequest request, String savedPath) {
        String fileName = request.getOriginFileName().split("\\.")[0];

        // savedPath: ./origin/chanel-{idd}/{subject} : 원본 저장 위치
        String path = savedPath+"/chanel-"+request.getChanelId()+"/" + fileName;
        Path filepath = null;
        try {
            filepath = Paths.get(path);
            Files.createDirectories(filepath); // 디렉토리 생성
        } catch (FileAlreadyExistsException e) {
            System.err.print("경로가 이미 존재합니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
}
