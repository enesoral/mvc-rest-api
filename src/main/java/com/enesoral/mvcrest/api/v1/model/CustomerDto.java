package com.enesoral.mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;
    private String lastName;

    private String customerUrl;
}
