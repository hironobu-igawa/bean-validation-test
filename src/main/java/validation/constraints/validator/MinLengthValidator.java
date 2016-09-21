package validation.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import message.MessageResource;
import validation.constraints.MinLength;

/**
 * Verify to be the element value length is the minimum value and over.
 * @author hironobu-igawa
 */
public class MinLengthValidator implements ConstraintValidator<MinLength, String> {
    private MinLength minLength;

    @Override
    public void initialize(MinLength minlength) {
        this.minLength = minlength;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (value.length() >= minLength.min()) {
            return true;
        }

        String label = MessageResource.get(minLength.label());
        String message = MessageResource.get(minLength.message(), label, minLength.min());

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }
}
