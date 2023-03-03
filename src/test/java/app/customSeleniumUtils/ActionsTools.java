package app.customSeleniumUtils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsTools {

    public static void pasteFromClipboard(WebDriver driver){
        // CTRL + V
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.LEFT_CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.LEFT_CONTROL);
        actions.build().perform();
    }

    public static void moveToElement(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }

    public static void moveToElementAndClick(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click();
        actions.build().perform();
    }

    public static void selectTextFromSelectedTextInput(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.HOME).build().perform();
        actions.keyDown(Keys.LEFT_SHIFT);
        actions.sendKeys(Keys.END);
        actions.keyUp(Keys.LEFT_SHIFT);
        actions.build().perform();
    }

}
