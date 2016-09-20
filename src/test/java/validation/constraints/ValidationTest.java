package validation.constraints;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Validation test superclass.
 * @author hironobu-igawa
 */
public abstract class ValidationTest {
    /**
     * Validates all constraints on user.
     * @return constraint violations or an empty set if none
     */
    protected <T> Set<ConstraintViolation<T>> validate(T bean) {
        return getValidator().validate(bean);
    }

    /**
     * Return the validator.
     * @return validator
     */
    protected Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
