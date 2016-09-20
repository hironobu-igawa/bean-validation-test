package validation.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import message.MessageResource;
import validation.constraints.Mask;

/**
 * Verify to match element value to regex pattern.
 * @author hironobu-igawa
 */
public class MaskValidator implements ConstraintValidator<Mask, String> {
    private Mask mask;

    @Override
    public void initialize(Mask mask) {
        this.mask = mask;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (Pattern.matches(mask.regexp(), value)) {
            return true;
        }

        String label = MessageResource.get(mask.label());
        String message = MessageResource.get(mask.message(), label);

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }

}
