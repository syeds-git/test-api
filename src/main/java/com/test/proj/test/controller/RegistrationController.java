package com.test.proj.test.controller;

import com.test.proj.test.model.OutputDTO;
import com.test.proj.test.model.RegInfoDTO;
import com.test.proj.test.service.RegistrationProcessor;
import com.test.proj.test.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller to handle registration requests
 */
@RestController
@RequestMapping("/api")
public class RegistrationController {

    public static final String FIELDS_MUST_NOT_BE_BLANK = "Username or IP address fields must not be blank";

    @Autowired
    RegistrationProcessor registrationProcessor;

    @PostMapping(path="register")
    public OutputDTO registerUser(@Validated @RequestBody RegInfoDTO regInfoDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            FieldError fe = errors.getFieldError("password");
            if (fe != null) {
                throw new ResponseStatusException(status, PasswordValidator.INVALID_PWD_MSG);
            } else {
                throw new ResponseStatusException(status, FIELDS_MUST_NOT_BE_BLANK);
            }
        }

        return registrationProcessor.processRegistrationData(regInfoDTO);
    }
}
