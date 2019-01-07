package com.rolex.controller;

import com.rolex.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rolex
 * @since 2018
 */
@RestController
public class IdController {
    
    @Autowired
    IdService idService;
    
    @GetMapping("/ids")
    public Long id() {
        return idService.getId();
    }
    
    @GetMapping("/blocks/ids")
    public List<Long> ids() {
        return idService.getBatchIds(20);
    }
}
