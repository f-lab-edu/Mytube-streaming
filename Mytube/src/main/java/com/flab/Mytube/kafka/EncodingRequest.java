package com.flab.Mytube.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
public class EncodingRequest {
  String topic;
  String path;
  String key;
}
