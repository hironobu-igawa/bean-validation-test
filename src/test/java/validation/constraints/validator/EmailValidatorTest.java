package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.Email;

/**
 * Tests EmailValidator.
 * @author hironobu-igawa
 */
public class EmailValidatorTest extends ValidatorTest {
    /**
     * Tests to match email pattern.
     */
    @Test
    public void testMatch() {
        TestBean bean = new TestBean();
        bean.email = "zin920314@gmail.com";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests to unmatch email pattern.
     */
    @Test
    public void testUnmatch() {
        TestBean bean = new TestBean();
        bean.email = "井川拓信gmail.com";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("Email constraint violation. label: Email", violations.iterator().next().getMessage());
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.email = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.email = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    private static class TestBean {
        @Email(message="EMAIL", label="label.email")
        public String email;
    }
}
