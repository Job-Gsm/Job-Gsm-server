package com.project.JobGsm.domain.user.controller;

import com.project.JobGsm.global.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    private final ResponseService responseService;

//    @PostMapping("/signup")
//    public CommonResultResponse register(@RequestBody UserRegisterDto userRegisterDto) {
//
//    }
}
