package properties;

    /**
     * Retrieve location of test assets. For example: images, files, etc
     * Retrieve values of properties with the following precedence:
     *      1. Command arguments -D<argument>=<value>
     *      2. Defined properties file
     *      3. Default value, if applicable
     *
     *    The values can then be easily accessed by typing: TestAssets.<ASSET_NAME>
     */
public class TestAssets {

    public static final String TREE_IMAGE = "src/test/resources/images/tree.jpg";

}
