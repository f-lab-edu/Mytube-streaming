package com.flab.Mytube.notices.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private final int code;
    private final String message;

    public Result(int num, String message){
        this.code=num;
        this.message=message;
    }
}
