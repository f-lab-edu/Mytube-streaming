package com.flab.Mytube.service;

import com.flab.Mytube.dto.Test;
import com.flab.Mytube.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMapper testMapper;

    @Transactional
    public void addTest(Test.AddTestParam param){
        testMapper.addTest(param);
    }
}
