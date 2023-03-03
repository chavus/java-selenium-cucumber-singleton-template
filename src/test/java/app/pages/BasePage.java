package app.pages;
import app.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends Page {

    private final static By LOGIN_BUTTON = By.cssSelector("a[title='Login']");

    public static void clickLogin(){
        clickElement(LOGIN_BUTTON);
    }

    public static void selectOptionAndSubOptionFromMenu(String optionName, String subOptionName){
     clickElementWithExactText(optionName,"a");
     clickElementWithExactText(subOptionName, "a");
    }

}
