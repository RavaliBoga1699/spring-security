package com.ravali.spring.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ravali.spring.security.annotation.impl.PasswordValidation;

@Target( {ElementType.FIELD, ElementType.METHOD})
@Retention( RetentionPolicy.RUNTIME)
@Constraint( validatedBy = PasswordValidation.class)
public @interface Password {
  String message() default "Invalid password";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  
}