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

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests to unmatch email pattern.
     */
    @Test
    public void testUnmatch() {
        TestBean bean = new TestBean();
        bean.email = "井川拓信gmail.com";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Email constraint violation. label: Email");
    }

    /**
     * Tests blank pattern.
     */
    @Test
    public void testBlank() {
        TestBean bean = new TestBean();
        bean.email = " ";

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void testNull() {
        TestBean bean = new TestBean();
        bean.email = null;

        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    private static class TestBean {
        @Email(message="EMAIL", label="label.email")
        public String email;
    }
}
