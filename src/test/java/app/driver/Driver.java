package app.driver;

import app.properties.AppProps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;

public class Driver {

    private static WebDriver driver = null;

    public static WebDriver get() {
        if (driver == null) {
            driver = createDriver(AppProps.BROWSER);
        }
        return driver;
    }

    /**
     * createDriver calls the specific driver initialization for the browser, which uses Selenium Manager (from Selenium 4.6),
     * webdriver files are no longer required.
     * @param browserName
     * @return WebDriver
     */
    private static WebDriver createDriver(String browserName) {
        WebDriver webDriver = null;
        switch (browserName.toLowerCase()) {
            case "chrome":
                webDriver = getChromeDriver();
                break;
            case "edge":
                webDriver = getEdgeDriver();
                break;
            case "firefox":
                webDriver = getFirefoxDriver();
                break;
            case "safari":
                webDriver = getSafariDriver();
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Browser:%s not supported. Please choose from: [chrome, edge, firefox or safari]"
                                    ,browserName));
        }
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static WebDriver getChromeDriver() {
        final ChromeOptions chromeOptions = new ChromeOptions();

        //Arguments: https://peter.sh/experiments/chromium-command-line-switches/
//        chromeOptions.addArguments("user-data-dir=/path/to/custom/profile");
//        chromeOptions.addArguments("start-maximized");

        //Preferences: https://chromium.googlesource.com/chromium/src/+/master/chrome/common/pref_names.cc
        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", "/new/download/path");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        System.out.println(String.format("Driver session: %s", chromeDriver.getSessionId()));
        return chromeDriver;

    }

    private static WebDriver getEdgeDriver() {
        final EdgeOptions edgeOptions = new EdgeOptions();

        //Arguments:
//        edgeOptions.addArguments("window-size=500,500");
//        edgeOptions.addArguments("start-maximized");

        //Preferences:
        HashMap<String, Object> edgePrefs = new HashMap<>();
//        edgePrefs.put("profile.default_content_settings.popups", 0)
        edgeOptions.setExperimentalOption("prefs", edgePrefs);

        WebDriver edgeDriver = new EdgeDriver(edgeOptions);
        System.out.println(String.format("Driver session: %s", ((RemoteWebDriver) edgeDriver).getSessionId()));
        return edgeDriver;
    }

    private static WebDriver getFirefoxDriver(){
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        final FirefoxProfile firefoxProfile = new FirefoxProfile();

        //Arguments:
//        firefoxOptions.addArguments("--private");

        //Preferences: Type "about:config" in firefox browser search bar to see options
//        firefoxProfile.setPreference("browser.startup.homepage","https://qaconsultants.com/");
        firefoxOptions.setProfile(firefoxProfile);

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        System.out.println(String.format("Driver session: %s", ((RemoteWebDriver) firefoxDriver).getSessionId()));
        return firefoxDriver;
    }

    private static WebDriver getSafariDriver(){
        SafariDriver safariDriver = new SafariDriver();

        // Add arguments and preferences

        System.out.println(String.format("Driver session: %s", ((RemoteWebDriver) safariDriver).getSessionId()));
        return safariDriver;
    }

    public static void quit() {
        if (driver == null){
            System.out.println("WARNING: there is not driver to quit. Ignoring operation.");
        } else {
            driver.quit();
            driver = null;
        }
    }

}