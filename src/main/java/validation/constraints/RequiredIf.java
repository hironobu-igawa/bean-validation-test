package validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.constraints.validator.RequiredIfValidator;

/**
 * The annotated method must return true.
 * Accepts boolean type.
 * @author hironobu-igawa
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={RequiredIfValidator.class})
@Documented
public @interface RequiredIf {
    /**
     * Return the message template key.
     * @return Message template key.
     */
    String message() default "";

    /**
     * Return the label keys.
     * @return Label keys.
     */
    String[] labels() default {};

    /**
     * Return the groups.
     * @return Groups.
     */
    Class<?>[] groups() default {};

    /**
     * Return the payload.
     * @return Payload.
     */
    Class<? extends Payload>[] payload() default {};
}
