package validation.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import message.MessageResource;
import validation.constraints.MaxLength;

/**
 * Verify to be the element value length is the maximum value and under.
 * @author hironobu-igawa
 */
public class MaxlengthValidator implements ConstraintValidator<MaxLength, String> {
    private MaxLength maxLength;

    @Override
    public void initialize(MaxLength maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (value.length() <= maxLength.max()) {
            return true;
        }

        String label = MessageResource.get(maxLength.label());
        String message = MessageResource.get(maxLength.message(), label, maxLength.max());

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }
}
