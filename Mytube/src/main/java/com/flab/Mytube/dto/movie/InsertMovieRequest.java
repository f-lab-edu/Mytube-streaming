package com.flab.Mytube.dto.movie;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

public class InsertMovieRequest {
    private InsertMovieRequest(){
        throw new IllegalStateException();
    }

    @Getter
    @Setter
    public static class Param{
        private BigInteger ID;
        BigInteger streamer_id;
        String subject;
        String url;

        // ID 속성에 대한 getter 및 setter 메서드 추가
        public BigInteger getId() {
            return ID;
        }

        public void setId(BigInteger id) {
            this.ID = id;
        }

    }
}
