package org.leechiwi.happyseven.files.web.api.controller;

import org.leechiwi.happyseven.files.web.api.model.ConvertDto;
import org.leechiwi.happyseven.files.web.exception.APIException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ConvertController {
    @GetMapping("/test")
    public String test(@Valid ConvertDto convertDto){
        return convertDto.getUrl();
    }
}
