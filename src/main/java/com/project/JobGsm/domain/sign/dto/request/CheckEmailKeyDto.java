package com.project.JobGsm.domain.sign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailKeyDto {

    @Size(min = 5, max = 5)
    @NotNull
    private String key;

}
