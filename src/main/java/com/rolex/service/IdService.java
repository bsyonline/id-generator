package com.rolex.service;

import com.google.common.collect.Lists;
import com.rolex.mapper.IdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author rolex
 * @since 2018
 */
@Service
public class IdService {
    
    @Autowired
    IdMapper idMapper;
    
    @Transactional
    public Long getId() {
        idMapper.replace();
        return idMapper.next();
    }
    
    public synchronized List<Long> getBatchIds(int range) {
        List<Long> set = Lists.newCopyOnWriteArrayList();
        for (int i = 0; i < range; i++) {
            set.add(getId());
        }
        return set;
    }
    
}
