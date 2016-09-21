package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.Mask;

/**
 * Tests MaskValidator.
 * @author hironobu-igawa
 */
public class MaskValidatorTest extends ValidatorTest {
    /**
     * Tests match pattern.
     */
    @Test
    public void testMatch() {
        TestBean bean = new TestBean();
        bean.mask = "abc123";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests unmatch pattern.
     */
    @Test
    public void testUnmatch() {
        TestBean bean = new TestBean();
        bean.mask = "Abc123";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Mask constraint violation. label: Mask");
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.mask = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.mask = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    private static class TestBean {
        @Mask(regexp="^[a-z0-9]*$", message="MASK", label="label.mask")
        public String mask;
    }
}
