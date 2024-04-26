package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TestMapper {
    void addTest(@Param("param") Test.AddTestParam param);
}
