package com.rolex.service;

import com.rolex.mapper.IdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;


/*******************************************************************************
 * - Copyright (c)  2018  chinadaas.com
 *   @author rolex
 * - File Name: com.rolex.service.IdService
 * - Description:
 *
 *
 * - Function List:
 *
 *
 * - History:
 * Date         Author          Modification
 * 2019/01/04   rolex           Create file
 *******************************************************************************/
@Service
public class IdService {
    
    @Autowired
    IdMapper idMapper;
    
    AtomicInteger atomicInteger;
    
    @Transactional
    public Long getId() {
        idMapper.replace();
        return idMapper.next();
    }
    
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
    }
    
}
