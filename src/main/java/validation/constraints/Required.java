package validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.constraints.validator.RequiredStringValidator;
import validation.constraints.validator.RequiredValidator;

/**
 * The annotated element must required.
 * Accepts any type.
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
@Constraint(validatedBy={RequiredStringValidator.class, RequiredValidator.class})
@Documented
public @interface Required {
    /**
     * Return the message template key.
     * @return message template key
     */
    String message() default "";

    /**
     * Return the label key.
     * @return label key
     */
    String label() default "";

    /**
     * Return the groups.
     * @return groups
     */
    Class<?>[] groups() default {};

    /**
     * Return the payload
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};
}
