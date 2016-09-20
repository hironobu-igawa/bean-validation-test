package validation.constraints;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import org.junit.Test;

import validation.constraints.Required;

/**
 * Tests ReuquiredStringValidator.
 * @author hironobu-igawa
 */
public class RequiredStringValidatorTest extends ValidationTest {
    /**
     * Tests the normal pattern.
     */
    @Test
    public void testNormal() {
        class Bean {
            @Required(message="REQUIRED", label="label.name")
            public String name;
        }

        Bean bean = new Bean();
        bean.name = "Hironobu";

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(violations.size(), 0);
    }

    /**
     * Tests the null error pattern.
     */
    @Test
    public void testNullError() {
        class Bean {
            @Required(message="REQUIRED", label = "label.name")
            public String name;
        }

        Bean bean = new Bean();
        bean.name = null;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Please enter Name.");
    }

    /**
     * Tests the blank error pattern.
     */
    @Test
    public void testBlankError() {
        class Bean {
            @Required(message="REQUIRED", label = "label.name")
            public String name;
        }

        Bean bean = new Bean();
        bean.name = " ";

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertEquals(violations.size(), 1);
        assertEquals(violations.iterator().next().getMessage(), "Please enter Name.");
    }
}
