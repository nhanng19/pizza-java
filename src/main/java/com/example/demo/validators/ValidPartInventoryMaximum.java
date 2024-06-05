package com.example.demo.validators;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PartInventoryMaximumValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPartInventoryMaximum {
    String message() default "Inventory cannot exceed the maximum value";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
