package com.test.proj.test.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Password validator enforcing following requirement
 * Password need to be greater than 8 characters,
 * containing at least 1 number,
 * 1 Capitalized letter,
 * 1 special character in this set “_ # $ % .”
 * Return error messages if not valid
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    public static final int MIN_LENGTH = 8;
    public static final String INVALID_PWD_MSG = "Invalid password provided. Password must be greater than 8 " +
            "characters containing at least one number, one upper case letter, one lower case and one of the " +
            "following special characters: _ # $ % . ";

    private static final Logger LOG = LoggerFactory.getLogger(PasswordValidator.class);

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[_#$%.]).*");
        boolean result = password != null && password.length() > MIN_LENGTH && pattern.matcher(password).matches();
        if (!result) {
            LOG.debug(INVALID_PWD_MSG);
        }

        return result;
    }
}
