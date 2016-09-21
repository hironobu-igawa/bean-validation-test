package validation.constraints;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * Tests MinlengthValidator.
 * @author hironobu-igawa
 */
public class MinLengthValidatorTest extends ValidationTest {
    /**
     * Tests "greater than" pattern.
     */
    @Test
    public void testGreaterThan() {
        TestBean bean = new TestBean();
        bean.minlength = "1234567";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests "equals" pattern.
     */
    @Test
    public void testEquals() {
        TestBean bean = new TestBean();
        bean.minlength = "123456";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests "less than" pattern.
     */
    @Test
    public void testLessThan() {
        TestBean bean = new TestBean();
        bean.minlength = "12345";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "MinLength constraint violation. label: minlength, min: 6");
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.minlength = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.minlength = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    private static class TestBean {
        @MinLength(min=6, message="MINLENGTH", label="label.minlength")
        public String minlength;
    }
}
