package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.error.exceptions.InvalidFileExtension;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Slf4j
public class FileUploadRequest {

  private long id;
  private MultipartFile file;
  private long channelId;
  private String subject;
  private String url;

  @Builder
  public FileUploadRequest(MultipartFile file, long channelId) {
    this.file = file;
    this.channelId = channelId;
    this.subject = setSubject(file);
  }

  public String setSubject(MultipartFile file) {
    String extention = StringUtils.getFilenameExtension(file.getOriginalFilename());
    if (extention.equals("mp4") == false && extention.equals(".mov") == false) {
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
  public String createKey(){
    String fileName = getOriginFileName();
    return fileName.split("\\.")[0];
  }

  public void addPath(String path) {
    log.info("[DB] path >>>" + path);
    this.url = path;
  }
}
