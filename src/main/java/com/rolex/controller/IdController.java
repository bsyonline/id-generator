package com.rolex.controller;

import com.rolex.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************************
 * - Copyright (c)  2018  chinadaas.com
 *   @author rolex
 * - File Name: com.rolex.controller.IdController
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
@RestController
public class IdController {
    
    @Autowired
    IdService idService;
    
    @GetMapping("/ids")
    public Long id() {
        return idService.getId();
    }
    
}
