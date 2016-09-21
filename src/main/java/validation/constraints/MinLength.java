package validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.constraints.validator.MinLengthValidator;

/**
 * The annotated element length must be minimum value and over.
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
@Constraint(validatedBy={MinLengthValidator.class})
@Documented
public @interface MinLength {
    /**
     * Return the minimum value of the string length.
     * @return Minimum value.
     */
    int min();

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
