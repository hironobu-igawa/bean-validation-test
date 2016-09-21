package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import org.junit.Test;

import validation.constraints.Required;

/**
 * Tests ReuquiredStringValidator.
 * @author hironobu-igawa
 */
public class RequiredStringValidatorTest extends ValidatorTest {
    /**
     * Tests valid pattern.
     */
    @Test
    public void testValid() {
        TestBean bean = new TestBean();
        bean.required = "a";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.required = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("Required constraint violation. label: Required", violations.iterator().next().getMessage());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.required = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals("Required constraint violation. label: Required", violations.iterator().next().getMessage());
    }

    private static class TestBean {
        @Required(message="REQUIRED", label = "label.required")
        public String required;
    }
}
