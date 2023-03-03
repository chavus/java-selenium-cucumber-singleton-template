package app.properties;
import utils.Utils;

public class AppProps {

    private static final String TEST_PROPERTIES_FILE = "test.properties";
    public static final String BROWSER = Utils.getPropertyOrDefault("browser", TEST_PROPERTIES_FILE,"chrome");
    public static final int DEFAULT_TIMEOUT = Utils.getPropertyOrDefault("defaultTimeout", TEST_PROPERTIES_FILE, 10);
    public static final int EXTENDED_TIMEOUT = Utils.getPropertyOrDefault("extendedTimeout", TEST_PROPERTIES_FILE, 60);
    public static final String BASE_URL = Utils.getMandatoryProperty("baseUrl",TEST_PROPERTIES_FILE);

}
