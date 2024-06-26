package com.flab.Mytube.dto;

import lombok.Getter;
import lombok.Setter;

public class Test {
    private Test(){
        throw new IllegalStateException();
    }
    @Getter
    @Setter
    public static class AddTestParam{
        Long id;
        String name;
        Integer age;
    }
}
