package siqueir4.dpvat.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import siqueir4.dpvat.validation.NumeroCelularValidation;

@Documented
@Constraint(validatedBy = NumeroCelularValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroCelularConstraint {
    String message() default "Número de telefone inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
