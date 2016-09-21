package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.MaxLength;

/**
 * Tests MaxLengthValidator.
 * @author hironobu-igawa
 */
public class MaxLengthValidatorTest extends ValidatorTest {
    /**
     * Tests "less than" pattern.
     */
    @Test
    public void testLessThan() {
        TestBean bean = new TestBean();
        bean.maxlength = "12345";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests "equals" pattern.
     */
    @Test
    public void testEquals() {
        TestBean bean = new TestBean();
        bean.maxlength = "123456";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests "greater than" pattern.
     */
    @Test
    public void testGreaterThan() {
        TestBean bean = new TestBean();
        bean.maxlength = "1234567";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("MaxLength constraint violation. label: maxlength, max: 6", violations.iterator().next().getMessage());
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.maxlength = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.maxlength = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    private static class TestBean {
        @MaxLength(max=6, message="MAXLENGTH", label="label.maxlength")
        public String maxlength;
    }
}
