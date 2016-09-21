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
     * Tests valid pattern.
     */
    @Test
    public void testValid() {
        TestBean bean = new TestBean();
        bean.required1 = "a";
        bean.required2 = "a";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests invalid pattern.
     */
    @Test
    public void testInvalid() {
        TestBean bean = new TestBean();
        bean.required1 = "a";
        bean.required2 = "b";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("RequiredIf constraint violation. labels: [RequiredIf1, RequiredIf2]", violations.iterator().next().getMessage());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void test() {
        TestBean bean = new TestBean();
        bean.required1 = null;
        bean.required2 = "b";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    private static class TestBean {
        public String required1;
        public String required2;

        @RequiredIf(message="REQUIREDIF", labels={"label.requiredif1", "label.requiredif2"})
        public boolean isValidMatchPassword() {
            if (required1 == null || required2 == null) {
                return true;
            }

            return required1.equals(required2);
        }
    }
}
