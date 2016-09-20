package validation.constraints.validator;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import message.MessageResource;
import validation.constraints.RequiredIf;

/**
 * Verify to return method value that is true.
 * @author hironobu-igawa
 */
public class RequiredIfValidator implements ConstraintValidator<RequiredIf, Boolean> {
    private RequiredIf requiredIf;

    @Override
    public void initialize(RequiredIf requiredIf) {
        this.requiredIf = requiredIf;
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value) {
            return true;
        }

        Object[] labels = Arrays.stream(requiredIf.labels())
                .map(l -> MessageResource.get(l)).toArray();
        String message = MessageResource.get(requiredIf.message(), labels);

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }
}
