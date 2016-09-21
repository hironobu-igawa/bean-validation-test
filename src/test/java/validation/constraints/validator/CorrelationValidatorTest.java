package validation.constraints.validator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import validation.constraints.CorrelationValid;

/**
 * Tests CorrelationValidator
 * @author hironobu-igawa
 */
public class CorrelationValidatorTest extends ValidatorTest {
    /**
     * Tests valid pattern.
     */
    @Test
    public void testValid() {
        TestBean bean = new TestBean();
        bean.correlation1 = "a";
        bean.correlation2 = "a";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    /**
     * Tests invalid pattern.
     */
    @Test
    public void testInvalid() {
        TestBean bean = new TestBean();
        bean.correlation1 = "a";
        bean.correlation2 = "b";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(1, violations.size());
        assertEquals("CorrelationValid constraint violation. labels: [CorrelationValid1, CorrelationValid2]", violations.iterator().next().getMessage());
    }

    /**
     * Tests null pattern.
     */
    @Test
    public void test() {
        TestBean bean = new TestBean();
        bean.correlation1 = null;
        bean.correlation2 = "b";
        Set<ConstraintViolation<TestBean>> violations = validate(bean);

        assertEquals(0, violations.size());
    }

    private static class TestBean {
        public String correlation1;
        public String correlation2;

        @CorrelationValid(message="CORRELATIONVALID", labels={"label.correlationvalid1", "label.correlationvalid2"})
        public boolean isValidMatchPassword() {
            if (correlation1 == null || correlation2 == null) {
                return true;
            }

            return correlation1.equals(correlation2);
        }
    }
}
