package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.RequiredIf;

/**
 * Tests RequiredIfValidator
 * @author hironobu-igawa
 */
public class RequiredIfValidatorTest extends ValidatorTest {
    /**
     * Tests input normal pattern.
     */
    @Test
    public void testInputNormal() {
        TestBean bean = new TestBean();
        bean.password1 = "Passw0rd!";
        bean.password2 = "Passw0rd!";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests not imput normal pattern.
     */
    @Test
    public void testNotImputNormal() {
        TestBean bean = new TestBean();
        bean.password1 = null;
        bean.password2 = "Passw0rd!";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests error pattern.
     */
    @Test
    public void testError() {
        TestBean bean = new TestBean();
        bean.password1 = "PasswOrd1";
        bean.password2 = "Passw0rd!";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Please match New password and Confirm password.");
    }

    private static class TestBean {
        public String password1;
        public String password2;

        @RequiredIf(message="MATCH.VALUES", labels={"label.password1", "label.password2"})
        public boolean isValidMatchPassword() {
            if (password1 == null || password2 == null) {
                return true;
            }

            return password1.equals(password2);
        }
    }
}
