package message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Manage message resource.
 * @author hironobu-igawa
 */
public class MessageResource {
    private static final String FILE_NAME = "message-resources";

    private static ResourceBundle resourceBundle;

    private MessageResource() {
        /* do nothing */
    }

    /**
     * Return a message that matches the key.
     * @param key - Match key.
     * @return Matched message.
     */
    public static String get(String key) {
        if (!getResourceBundle().containsKey(key)) {
            return null;
        }
        return getResourceBundle().getString(key);
    }

    /**
     * Return a message that matches the key to bind the value.
     * @param key - Match key.
     * @param value - Bind value.
     * @return Matched message.
     */
    public static String get(String key, Object value) {
        return get(key, new Object[] { value });
    }

    /**
     * Return a message that matches the key to bind values.
     * @param key - Match key.
     * @param value0 - Value to bind to the 0th.
     * @param value1 - Value to bind to the 1th.
     * @return Matched message.
     */
    public static String get(String key, Object value0, Object value1) {
        return get(key, new Object[] { value0, value1 });
    }

    /**
     * Return a message that matches the key to bind values.
     * @param key - Match key.
     * @param value0 - Value to bind to the 0th.
     * @param value1 - Value to bind to the 1th.
     * @param value2 - Value to bind to the 2th.
     * @return Matched message.
     */
    public static String get(String key, Object value0, Object value1, Object value2) {
        return get(key, new Object[] { value0, value1, value2 });
    }

    /**
     * Return a message that matches the key to bind values.
     * @param key - Match key.
     * @param value0 - Value to bind to the 0th.
     * @param value1 - Value to bind to the 1th.
     * @param value2 - Value to bind to the 2th.
     * @param value3 - Value to bind to the 3th.
     * @return Matched message.
     */
    public static String get(String key, Object value0, Object value1, Object value2, Object value3) {
        return get(key, new Object[] { value0, value1, value2, value3 });
    }

    /**
     * Return a message that matches the key to bind values.
     * @param key - Match key.
     * @param values - Bind values.
     * @return Matched message.
     */
    public static String get(String key, Object[] values) {
        if (!getResourceBundle().containsKey(key)) {
            return null;
        }
        return new MessageFormat(getResourceBundle().getString(key)).format(values);
    }

    private static ResourceBundle getResourceBundle() {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(FILE_NAME);
        }
        return resourceBundle;
    }
}
