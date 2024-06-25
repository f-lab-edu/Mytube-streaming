package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;


@Getter
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
  }

  public void addSubject() {
    try {
      subject = file.getOriginalFilename().split("\\.")[0];
    } catch (Exception e) {
      subject = file.getOriginalFilename();
    }
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
