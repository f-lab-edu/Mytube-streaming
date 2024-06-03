package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class SettingLiveService {
    private final PostMapper postMapper;

//    @Transactional // 동영상 업로드
//    public Response insertMovie(UploadMovieRequest request){
//        postMapper.addMovie(request.uploadMovie());
//        return new Response( 201, "Success");
//    }
    @Transactional
    public HttpStatus insertMovie(FileUploadRequest request){
        if (request.isEmptyFile()) {
            System.out.println("파일 넣으십셔.");
            return HttpStatus.BAD_REQUEST;
        }
        Path filepath = Paths.get("src/main/resources/static", request.getFile().getOriginalFilename());

        // 해당 path 에 파일의 스트림 데이터를 저장
        try (OutputStream os = Files.newOutputStream(filepath)) {
            byte[] bytes = request.getBytes();
            Files.write(filepath, bytes);
            System.out.println(" SUCCESS!!! >>> ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return HttpStatus.CREATED;
    }

    @Transactional // 방송 예약하기
    public Response reserveMovie(ReserveShowRequest request){
        postMapper.reserveShow(request.makeReservation());
        return new Response( 201, "Success");
    }

    @Transactional // 방송 시작하기
    public StartingShowResponse startShow(long streamingId){
// Id 를 활용해 테이블을 찾아오는 매퍼 호출
//        return mapper.method(streamingId);
        StartingShowResponse response = postMapper.findByStartingStreamingId(streamingId);
        return response;
    }
}
