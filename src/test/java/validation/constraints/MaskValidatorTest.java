package validation.constraints;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * Tests MaskValidator.
 * @author hironobu-igawa
 */
public class MaskValidatorTest extends ValidationTest {
    /**
     * Tests match pattern.
     */
    @Test
    public void testMatch() {
        TestBean bean = new TestBean();
        bean.userId = "Zin920314";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests unmatch pattern.
     */
    @Test
    public void testUnmatch() {
        TestBean bean = new TestBean();
        bean.userId = "井川拓信";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Please enter User ID in alphanumeric only.");
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.userId = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.userId = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    private static class TestBean {
        @Mask(regexp="^[a-zA-Z0-9]*$", message="MASK.ALPHANUMERIC.ONLY", label="label.user.id")
        public String userId;
    }
}
