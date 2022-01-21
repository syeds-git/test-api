package com.test.proj.test.model;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class RegInfoDTOTest {

    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldHaveNoErrorsWhenAllFieldsAreValid() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("123");
        info.setUsername("user");
        info.setPassword("Password1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldHaveErrorWhenUsernameIsNull() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("123");
        info.setPassword("Password1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("username", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenUsernameIsEmpty() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("123");
        info.setUsername("");
        info.setPassword("Password1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("username", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenIPAddressIsNull() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setUsername("user");
        info.setPassword("Password1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("ipAddress", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenIPAddressIsEmpty() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("");
        info.setUsername("user");
        info.setPassword("Password1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("ipAddress", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordIsNull() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(2, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordIsEmpty() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(2, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordIsLessThan8Chars() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("Va1#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordHasNoUpperCaseChars() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("password22#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordHasNoLowerCaseChars() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("PASSWORD1*#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordHasNoDigits() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("Password_*#");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldHaveErrorWhenPasswordHasNoSpecialChars() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("Password123456");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(1, violations.size());
        violations.forEach(violation -> {
            assertEquals("password", violation.getPropertyPath().toString());
        });
    }

    @Test
    public void shouldNotHaveErrorWhenPasswordIsValid() {
        // given
        RegInfoDTO info = new RegInfoDTO();
        info.setIpAddress("10.0.0.7");
        info.setUsername("user");
        info.setPassword("Password123456_");

        // when
        Set<ConstraintViolation<RegInfoDTO>> violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());

        // given
        info.setPassword("Password123456#");

        // when
        violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());

        // given
        info.setPassword("Password123456$");

        // when
        violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());

        // given
        info.setPassword("Password123456%");

        // when
        violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());

        // given
        info.setPassword("Password123456.");

        // when
        violations = validator.validate(info);

        // then
        assertEquals(0, violations.size());
    }
}
