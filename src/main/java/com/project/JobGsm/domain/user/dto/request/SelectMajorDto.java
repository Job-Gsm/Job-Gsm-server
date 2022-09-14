package com.project.JobGsm.domain.user.dto.request;


import com.project.JobGsm.domain.user.enumType.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectMajorDto {

    @NotBlank
    private Major major;


}
