package validation.constraints.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import message.MessageResource;
import validation.constraints.Email;

/**
 * Verify to match the element value to email pattern.
 * @author hironobu-igawa
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
    private Email email;

    @Override
    public void initialize(Email email) {
        this.email = email;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        if (Pattern.matches("^\\s*?(.+)@(.+?)\\s*$", value)) {
            return true;
        }

        String label = MessageResource.get(email.label());
        String message = MessageResource.get(email.message(), label);

        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        return false;
    }
}
