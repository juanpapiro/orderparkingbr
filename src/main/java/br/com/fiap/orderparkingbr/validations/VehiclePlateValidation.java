package br.com.fiap.orderparkingbr.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VehiclePlateValidationImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VehiclePlateValidation {

    String message() default "Placa em formato incorreto!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
