package com.example.springvalidatordemo.controller;

import com.example.springvalidatordemo.model.dto.User;
import com.example.springvalidatordemo.model.entity.ApiResult;
import com.example.springvalidatordemo.model.vo.NewUser;
import com.example.springvalidatordemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ValidController.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/valid")
public class ValidController {
    
    private final UserService userService;
    
    /**
     * create
     *
     * @param newUser info
     * @return success ?
     */
    @PostMapping("/create")
    public ApiResult<Boolean> create(@RequestBody @Valid NewUser newUser) {
        return ApiResult.ok(userService.create(newUser));
    }
    
    /**
     * all
     *
     * @return success ?
     */
    @GetMapping("/list/all")
    public ApiResult<List<User>> all() {
        return ApiResult.ok(userService.all());
    }
}
