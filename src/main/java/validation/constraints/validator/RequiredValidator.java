package validation.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import validation.constraints.Required;
import message.MessageResource;

/**
 * Verify to exists element value.
 * @author hironobu-igawa
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {
    private Required required;

    @Override
    public void initialize(Required required) {
        this.required = required;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value != null) {
            return true;
        }

        String label = MessageResource.get(required.label());
        String message = MessageResource.get(required.message(), label);

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }
}
