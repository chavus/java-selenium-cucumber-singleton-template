package app.customSeleniumUtils;
import org.openqa.selenium.remote.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DebugSandbox {

    public static void main(String [] args) throws MalformedURLException {
        String PORT = "55269"; // Enter this value from existing driver
        String SESSION_ID = "e39f809b1a4eea5cca8706e90b286b09"; // Enter this value from existing driver
        RemoteWebDriver newDriver = SeleniumTools.createDriverFromSession(SESSION_ID
                                                          , new URL(String.format("http://localhost:%s", PORT)));
        System.out.println("Stopping here");
        //        newDriver.get("https://www.google.com");
    }
}