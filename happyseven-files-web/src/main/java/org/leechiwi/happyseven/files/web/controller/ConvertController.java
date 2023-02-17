package org.leechiwi.happyseven.files.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {
    @GetMapping("/test")
    public String test(String nameSpace){
        return nameSpace;
    }
}
