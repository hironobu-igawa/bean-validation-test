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
     * Tests the normal pattern.
     */
    @Test
    public void testNormal() {
        class Bean {
            @Required(message="REQUIRED", label = "label.point")
            public Integer point;
        }

        Bean bean = new Bean();
        bean.point = 10;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests the null error pattern.
     */
    @Test
    public void testNullError() {
        class Bean {
            @Required(message="REQUIRED", label = "label.point")
            public Integer point;
        }

        Bean bean = new Bean();
        bean.point = null;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Please enter Point.");
    }
}
