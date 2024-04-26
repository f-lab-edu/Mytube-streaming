package com.flab.Mytube.notices.vo;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.TIMESTAMP;

import java.math.BigInteger;

@Getter
@Setter
public class Movie {
    private BigInteger id;
    private BigInteger userId;
    private String subject;
    private String url;
    private TIMESTAMP timestamp;
}
