package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.mapper.PostMapper;
import com.flab.Mytube.Utils.Movies;
import com.flab.Mytube.vo.MovieVO;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ConvertMovieService {
    private final PostMapper postMapper;
    private final FFmpeg fFmpeg;
    private final FFprobe fFprobe;
    private final Movies movie = new Movies();

    @Value("src/main/resources/static/origin")
    private String savedPath;

    @Value("src/main/resources/static/hls")
    private String hlsOutputPath;

    @Value("src/main/resources/static/mp4")
    private String mp4OutputPath;

    //동영상 업로드
    @Transactional
    public HttpStatus uploadMovie(FileUploadRequest request){
        if (request.isEmptyFile()) {
            System.out.println("파일 넣으십셔.");
            return HttpStatus.BAD_REQUEST;
        }
        String fileName=  request.getFile().getOriginalFilename();
        Path filepath = movie.createPath(request, savedPath);
        filepath = filepath.resolve(fileName);

        try (OutputStream os = Files.newOutputStream(filepath)) {
            byte[] bytes = request.getFile().getBytes();
            Files.write(filepath, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        movieBuilder(filepath, request);
        return HttpStatus.CREATED;
    }

    private void movieBuilder(Path filepath, FileUploadRequest request){
        String path = filepath.toString();
        String outPath = movie.createPath(request, hlsOutputPath).toString(); // 저장 위치 생성
        File output = new File(outPath);
        if (!output.exists()) {
            output.mkdirs();
        }

        String fileName = request.getOriginFileName().split("\\.")[0];
        String tsName = fileName;

        String source = fileName + ".m3m8";
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path) // 입력 소스
                .overrideOutputFiles(true)
                .addOutput(output.getAbsolutePath()+"/"+source) // 저장경로
                .setFormat("hls")
                .addExtraArgs("-hls_time", "10") // 10초
                .addExtraArgs("-hls_list_size", "0")
                .addExtraArgs("-hls_segment_filename", output.getAbsolutePath() + "/"+tsName+"_%08d.ts") // 청크 파일 이름
                .done();

        run(builder);
        request.addPath(output.getPath(), source);
        postMapper.addMovie(request);
    }


    private void run(FFmpegBuilder builder) {
        FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);

        executor
                .createJob(builder, progress -> {
                    log.info("progress ==> {}", progress);
                    if (progress.status.equals(Progress.Status.END)) {
                        log.info("============================= JOB FINISHED =============================");
                    }
                })
                .run();
    }
    public File getLiveFile(MovieDtailRequest request){
        int chanelId=request.getChanelId();
        String fileName = request.getFileName();
        // movie 의 id 가 입력된 경우
        if(isNumberic(fileName)){
            return getLiveFile(Long.valueOf(fileName));
        }

        String key = fileName.split("\\.")[0];
        StringBuilder sb = new StringBuilder();
        sb.append(hlsOutputPath).append("/chanel-" + chanelId).append("/").append(key).append("/").append(fileName);
        String filePath =sb.toString();
        return new File(filePath);
    }
    public boolean isNumberic(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public File getLiveFile(Long fileId){
        long chanelId = fileId;
        MovieVO movie = postMapper.getMovieUrl(chanelId);
        String filePath = movie.getUrl();
        System.out.println(">>> >>> >>> "+filePath);

        return new File(filePath);
    }

//    // 업로드한 동영상 리스트 뽑아오는 코드 필요할 듯? id 랑 subject 반환해주기
//    @Transactional
//    public List<MovieVO> getUploadMovie(long streamerId){
//        // sreamerId 와 연관된 동영상 반환해오기
//        List<MovieVO> result = postMapper.uploadMovieList(streamerId);
//        return result;
//    }

//    @Transactional // 방송 시작하기
//    public StartingShowResponse startShow(long liveId) {
//        LiveStreamingVO result = postMapper.findByStartingLiveId(liveId);
//        System.out.println(result.toString());
//        long movieId = result.getMovieId();
//        // 필요한 값만 가져오기 위한 객체를 생성해? 말아?
//        MovieVO movie = postMapper.getMovieURI(movieId);
//        StartingShowResponse response= StartingShowResponse.builder()
//                .id(result.getId())
//                .url(movie.getUrl())
//                .title(result.getTitle())
//                .contents(result.getContents())
//                .userCount(result.getUserCount())
//                .thumbsUp(result.getThumbsUp()).build();
//        return response;
//    }
}
