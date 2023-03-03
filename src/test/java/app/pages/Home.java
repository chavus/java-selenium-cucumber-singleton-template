package app.pages;

import app.driver.Driver;
import app.properties.AppProps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends BasePage{

//    private static WebDriver driver = Driver.get();

    private static By VIEW_DOCS_BUTTON = By.cssSelector("a[title='View Docs']");

    public static void go(){
        Driver.get().get(AppProps.BASE_URL);
    }

    public static void clickViewDocs(){
        clickElement(VIEW_DOCS_BUTTON);
    }

}
