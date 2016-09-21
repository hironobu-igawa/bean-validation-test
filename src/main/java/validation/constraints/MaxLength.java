package validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.constraints.validator.MaxlengthValidator;

/**
 * The annotated element length must be maximum value and under.
 * Accepts String type.
 * @author hironobu-igawa
 */
@Target({
    ElementType.METHOD,
    ElementType.FIELD,
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={MaxlengthValidator.class})
@Documented
public @interface MaxLength {
    /**
     * Return the maximum value of the string length.
     * @return Maximum value.
     */
    int max();

    /**
     * Return the message template key.
     * @return Message template key.
     */
    String message() default "";

    /**
     * Return the label key.
     * @return Label key.
     */
    String label() default "";

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
