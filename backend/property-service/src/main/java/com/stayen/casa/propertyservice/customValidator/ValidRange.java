package com.stayen.casa.propertyservice.customValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRange {
String message() default "Invalid Property Details for Selected Category";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
