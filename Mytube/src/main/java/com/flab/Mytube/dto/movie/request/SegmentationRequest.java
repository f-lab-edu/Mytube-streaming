package com.flab.Mytube.dto.movie.request;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SegmentationRequest {

  String name;
  String originPath;
  String m3u8Name;
  String output;

//  File output = new File(path);

}
