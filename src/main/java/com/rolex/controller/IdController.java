package com.rolex.controller;

import com.rolex.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
