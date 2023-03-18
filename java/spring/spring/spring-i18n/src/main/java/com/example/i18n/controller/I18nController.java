package com.example.i18n.controller;

import com.example.i18n.exception.I18nException;
import com.example.i18n.model.entity.ApiResult;
import com.example.i18n.model.vo.NewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * I18nController.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/i18n")
public class I18nController {
    
    private final MessageSource messageSource;
    
    /**
     * create
     *
     * @param newUser info
     * @return success
     */
    @PostMapping("/message")
    public ApiResult<String> message(@RequestBody @Valid NewUser newUser) {
        return ApiResult.ok(messageSource.getMessage("test.message", new String[]{newUser.getName()}, LocaleContextHolder.getLocale()));
    }
    
    /**
     * error
     *
     * @return success
     */
    @PostMapping("/error")
    public ApiResult<String> error() {
        throw new I18nException("test.error");
    }
}
