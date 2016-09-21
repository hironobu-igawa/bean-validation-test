package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.Required;

/**
 * Test RequiredValidator.
 * @author h_igawa
 */
public class RequiredValidatorTest extends ValidatorTest {
    /**
     * Tests valid pattern.
     */
    @Test
    public void testValid() {
        Bean bean = new Bean();
        bean.required = 10;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        Bean bean = new Bean();
        bean.required = null;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("Required constraint violation. label: Required", violations.iterator().next().getMessage());
    }

    private static class Bean {
        @Required(message="REQUIRED", label = "label.required")
        public Integer required;
    }
}
