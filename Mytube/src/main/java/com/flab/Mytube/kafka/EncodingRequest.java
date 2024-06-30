package com.flab.Mytube.kafka;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class EncodingRequest implements Serializable {
  String topic;
  String path;
  String key;
}
