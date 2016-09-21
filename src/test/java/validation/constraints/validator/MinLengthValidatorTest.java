package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.MinLength;

/**
 * Tests MinLengthValidator.
 * @author hironobu-igawa
 */
public class MinLengthValidatorTest extends ValidatorTest {
    /**
     * Tests "greater than" pattern.
     */
    @Test
    public void testGreaterThan() {
        TestBean bean = new TestBean();
        bean.minlength = "1234567";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests "equals" pattern.
     */
    @Test
    public void testEquals() {
        TestBean bean = new TestBean();
        bean.minlength = "123456";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests "less than" pattern.
     */
    @Test
    public void testLessThan() {
        TestBean bean = new TestBean();
        bean.minlength = "12345";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("MinLength constraint violation. label: minlength, min: 6", violations.iterator().next().getMessage());
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.minlength = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.minlength = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    private static class TestBean {
        @MinLength(min=6, message="MINLENGTH", label="label.minlength")
        public String minlength;
    }
}
