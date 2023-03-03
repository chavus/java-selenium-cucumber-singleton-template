package properties;

import utils.Utils;

/**
 * Retrieve test properties values. For example: credentials, data sets, etc
 * Retrieve values of properties with the following precedence:
 *      1. Command arguments -D<argument>=<value>
 *      2. Defined properties file
 *      3. Default value, if applicable
 *
 *    The values can then be easily accessed by typing: TestProps.<PROPERTY_NAME>
 */

public class TestProps {

    private static final String TEST_PROPERTIES_FILE = "test.properties";

    public static final String A_TEST_PROPERTY = Utils.getPropertyOrDefault("aTestProperty", TEST_PROPERTIES_FILE,"aDefaultValue");

}
