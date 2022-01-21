package com.test.proj.test.controller;

import static com.test.proj.test.controller.RegistrationController.FIELDS_MUST_NOT_BE_BLANK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.test.proj.test.model.OutputDTO;
import com.test.proj.test.model.RegInfoDTO;
import com.test.proj.test.service.RegistrationProcessor;
import com.test.proj.test.validation.PasswordValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

    @TestConfiguration
    static class RegistrationControllerTestContextConfiguration {

        @Bean
        public RegistrationController registrationController() {
            return new RegistrationController();
        }
    }

    @Autowired
    RegistrationController registrationController;

    @MockBean
    RegistrationProcessor registrationProcessor;

    @Mock
    BindingResult bindingResult;

    @Test
    public void expectedOutputIsCreatedWhenThereAreNoValidationErrors() {
        // given
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        RegInfoDTO regInfoDTO = new RegInfoDTO();
        OutputDTO expected = new OutputDTO("test");
        Mockito.when(registrationProcessor.processRegistrationData(regInfoDTO)).thenReturn(expected);

        // when
        registrationController.registerUser(regInfoDTO, bindingResult);

        // then
        Mockito.verify(bindingResult, Mockito.times(1)).hasErrors();
        Mockito.verify(registrationProcessor, Mockito.times(1)).processRegistrationData(regInfoDTO);
    }

    @Test
    public void expectResponseStatusExceptionWhenInputHasPasswordError() {
        // given
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getFieldError("password")).thenReturn(getFieldError());
        RegInfoDTO regInfoDTO = new RegInfoDTO();
        OutputDTO expected = new OutputDTO("test");
        Mockito.when(registrationProcessor.processRegistrationData(regInfoDTO)).thenReturn(expected);

        // when
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                registrationController.registerUser(regInfoDTO, bindingResult)
        );

        assertTrue(exception.getMessage().contains(PasswordValidator.INVALID_PWD_MSG));

        // then
        Mockito.verify(bindingResult, Mockito.times(1)).hasErrors();
        Mockito.verify(bindingResult, Mockito.times(1)).getFieldError("password");
        Mockito.verify(registrationProcessor, Mockito.never()).processRegistrationData(regInfoDTO);
    }

    @Test
    public void expectResponseStatusExceptionWhenInputHasValidationErrors() {
        // given
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getFieldError("password")).thenReturn(null);
        RegInfoDTO regInfoDTO = new RegInfoDTO();
        OutputDTO expected = new OutputDTO("test");
        Mockito.when(registrationProcessor.processRegistrationData(regInfoDTO)).thenReturn(expected);

        // when
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                registrationController.registerUser(regInfoDTO, bindingResult)
        );

        assertTrue(exception.getMessage().contains(FIELDS_MUST_NOT_BE_BLANK));

        // then
        Mockito.verify(bindingResult, Mockito.times(1)).hasErrors();
        Mockito.verify(bindingResult, Mockito.times(1)).getFieldError("password");
        Mockito.verify(registrationProcessor, Mockito.never()).processRegistrationData(regInfoDTO);
    }

    private FieldError getFieldError() {
        return new FieldError("", "", "");
    }
}
