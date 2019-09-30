package com.ibank.payment.annotations;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RequiredIfRepeatable.class)
@Constraint(validatedBy = {RequiredIfValidator.class})
public @interface RequiredIf {
	String message() default "Field '{requiredField}' is required if '{field}'='{value}'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
    String field();
 
    String value();
    
    String requiredField();
    
    String requiredValue() default "";
}

