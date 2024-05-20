package com.flab.Mytube.dao;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LiveListDAO {
    List<LivePageDAO> liveBoard;
}
