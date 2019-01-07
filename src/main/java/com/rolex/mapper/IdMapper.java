package com.rolex.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IdMapper {
    
    void replace();
    
    Long next();
    
    List<Long> batchinsertCallId(List<Long> list);
}