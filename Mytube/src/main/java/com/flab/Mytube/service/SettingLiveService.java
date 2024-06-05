package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.MovieDTO;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.Progress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingLiveService {
    private final PostMapper postMapper;
    private final FFmpeg fFmpeg;
    private final FFprobe fFprobe;

    @Value("src/main/resources/static/origin")
    private String savedPath;

    @Value("src/main/resources/static/hls")
    private String hlsOutputPath;

    @Value("src/main/resources/static/mp4}")
    private String mp4OutputPath;

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
        String fileName=  request.getFile().getOriginalFilename();// 확장자 들어가든말듵ㄴ 노신경..
        // Path 객체 생성, savedPath 에 file 객체 fileName 을 생성한다.
        Path filepath = Paths.get(savedPath, fileName);

        // 해당 path 에 파일의 스트림 데이터를 저장
        try (OutputStream os = Files.newOutputStream(filepath)) {
            byte[] bytes = request.getFile().getBytes();
            //Path 파일에 작성
            Files.write(filepath, bytes);
            System.out.println("Upload SUCCESS!!! >>> ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        movieBuilder(filepath, request);
        return HttpStatus.CREATED;
    }

    private void movieBuilder(Path filepath, FileUploadRequest fileRequest){
        String path = savedPath + "/" + fileRequest.getOriginFileName();

        File output = new File(hlsOutputPath+"/streamer/"+fileRequest.getStreamerId());
        if (!output.exists()) {
            output.mkdirs();
        }

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path) // 입력 소스
                .overrideOutputFiles(true)
                .addOutput(output.getAbsolutePath()+"/"+fileRequest.getOriginFileName().split("\\.")[0]+".m3m8")
                .setFormat("hls")
                .addExtraArgs("-hls_time", "10") // 10초
                .addExtraArgs("-hls_list_size", "0")
                .addExtraArgs("-hls_segment_filename", output.getAbsolutePath() + "/master_%08d.ts") // 청크 파일 이름
                .done();

        run(builder);
        fileRequest.addPath(output.getPath());
        postMapper.addMovie(fileRequest);
    }


    private void run(FFmpegBuilder builder) {
        FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);

        executor
                .createJob(builder, progress -> {
                    log.info("progress ==> {}", progress);
                    if (progress.status.equals(Progress.Status.END)) {
                        log.info("================================= JOB FINISHED =================================");
                    }
                })
                .run();
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
