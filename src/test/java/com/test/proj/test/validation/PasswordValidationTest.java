package com.test.proj.test.validation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class PasswordValidationTest {

    PasswordValidator passwordValidator;

    @Before
    public void setup() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void shouldReturnTrueWhenPasswordIsValid() {
        assertTrue(passwordValidator.isValid("Password123456_", null));
        assertTrue(passwordValidator.isValid("Password123456#", null));
        assertTrue(passwordValidator.isValid("Password123456_", null));
        assertTrue(passwordValidator.isValid("Password123456$", null));
        assertTrue(passwordValidator.isValid("Password123456%", null));
        assertTrue(passwordValidator.isValid("Password123456.", null));
    }

    @Test
    public void shouldReturnFalseWhenPasswordIsNotValid() {
        assertFalse(passwordValidator.isValid("Pa1_", null));
        assertFalse(passwordValidator.isValid("Password#", null));
        assertFalse(passwordValidator.isValid("12345678_", null));
        assertFalse(passwordValidator.isValid("PASSWORD123456$", null));
        assertFalse(passwordValidator.isValid("password123456%", null));
        assertFalse(passwordValidator.isValid("Password123456", null));
    }
}
