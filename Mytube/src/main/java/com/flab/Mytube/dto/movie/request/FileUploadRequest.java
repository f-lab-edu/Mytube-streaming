package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.error.exceptions.InvalidFileExtension;
import com.flab.Mytube.utils.MoviePath;
import io.netty.util.internal.StringUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Getter
public class FileUploadRequest {

  private long id;
  private MultipartFile file;
  private long channelId;
  private String subject;
  private String url;

//  private MoviePath moviePath;
  @Builder
  public FileUploadRequest(MultipartFile file, long channelId) {
    this.file = file;
    this.channelId = channelId;
    this.subject = setSubject(file);
  }

  public Path originPath(){
    String fileName = this.subject;
    String rawPath = "src/main/resources/static/origin/channel-" + channelId + "/" + file.getOriginalFilename();
    Path originPath = Paths.get(rawPath);
//    Path originPath = moviePath.outputRootPath(this.channelId, file.getOriginalFilename());
    originPath = originPath.resolve(fileName);
    return originPath;
  }

  public String setSubject(MultipartFile file) {
    String extention = StringUtils.getFilenameExtension(file.getOriginalFilename());
    if (extention.equals("mp4") == false && extention.equals(".mov") == false){
      throw new InvalidFileExtension("동영상 파일(.mp4, .mov)만 스트리밍이 가능합니다.");
    }
    return StringUtils.getFilename(file.getOriginalFilename().split("\\.")[0]);
  }

  public boolean isEmptyFile() {
    return file.isEmpty();
  }

  public String getOriginFileName() {
    return file.getOriginalFilename();
  }

  public void addPath(String path, String name) {
    StringBuilder sb = new StringBuilder(path);
    sb.append("/" + name);
    this.url = sb.toString();
  }
}
