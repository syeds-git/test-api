package com.test.proj.test.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.test.proj.test.validation.PasswordValidator.INVALID_PWD_MSG;
import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default INVALID_PWD_MSG;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}