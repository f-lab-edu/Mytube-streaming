package com.flab.Mytube.utils;

import com.flab.Mytube.dto.streaming.LiveStatus;
import com.flab.Mytube.error.exceptions.AlreadyEndedLiveException;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Validations {

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean notValidLive(LiveStatus stored){
    if (stored == null) {
      throw new ResourceNotFoundException("찾을 수 없는 라이브 입니다.");
    }
    if (stored.isEndLive()) {
      throw new AlreadyEndedLiveException("이미 종료된 라이브 입니다.");
    }
    return true;
  }

}
