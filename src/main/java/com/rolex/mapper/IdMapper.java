package com.rolex.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdMapper {
    
    void replace();
    
    Long next();
    
}