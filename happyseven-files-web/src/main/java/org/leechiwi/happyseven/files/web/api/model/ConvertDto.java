package org.leechiwi.happyseven.files.web.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class ConvertDto {
    @NotBlank(message = "url不能为空")
    private String Url;
}
