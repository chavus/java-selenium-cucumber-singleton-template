package app.customSeleniumUtils;

import app.utils.AppUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Arrays;

public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> amountOfElementsFoundIs(By byLocator, int expectedQtyOfElements){

        return new ExpectedCondition<Boolean>() {

            @Override
            public  Boolean apply(WebDriver driver) {
                return driver.findElements(byLocator).size() == expectedQtyOfElements;
            }

            public String toString() {
                return String.format("finding \"%d\" elements with locator: \"%s\".",expectedQtyOfElements, byLocator  );
            }
        };
    }

    public static ExpectedCondition<Boolean> elementHasClass(By byLocator, String className){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Arrays.asList(driver
                                .findElement(byLocator)
                                .getAttribute("class")
                                .split("\\s+"))
                        .contains(className);
            }

            public String toString() {
                return String.format("class \"%s\" in element with locator: \"%s\".",className, byLocator  );
            }
        };
    }

    public static ExpectedCondition<Boolean> elementHasClass(WebElement webElement, String className){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Arrays.asList(webElement
                                .getAttribute("class")
                                .split("\\s+"))
                        .contains(className);
            }

            public String toString() {
                return String.format("class \"%s\" in element with locator: \"%s\".",className, SeleniumTools.getLocatorStringFromWebElement(webElement));
            }
        };
    }


    public static ExpectedCondition<Boolean> elementDoesNotHaveClass(WebElement webElement, String className){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return !Arrays.asList(webElement
                                .getAttribute("class")
                                .split("\\s+"))
                        .contains(className);
            }

            public String toString() {
                return String.format("class \"%s\" not to be in element with locator: \"%s\".",className, SeleniumTools.getLocatorStringFromWebElement(webElement));
            }
        };
    }

    public static ExpectedCondition<Boolean> elementHasTitle(By byLocator, String titleName){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Arrays.asList(driver
                                .findElement(byLocator)
                                .getAttribute("title")
                                .split("\\s+"))
                        .contains(titleName);
            }

            public String toString() {
                return String.format("title \"%s\" in element with locator: \"%s\".",titleName, byLocator  );
            }
        };
    }

    public static ExpectedCondition<Boolean> elementStopsScrolling(By byLocator){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(byLocator);
                Point initialLocation = element.getLocation();
                AppUtils.waitByTime(100);
                Point newLocation = element.getLocation();
                return initialLocation.x == newLocation.x && initialLocation.y == newLocation.y;
            }

            public String toString() {
                return String.format("scrolling to stop of element located by %s", byLocator  );
            }

        };

    }
    public static ExpectedCondition<Boolean> elementHasBackgroundRGBAColor(By byLocator, String rgbaCode){
        // rgbaCode example = "rgba(85,85,85,1)"
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(byLocator);
                String currentElementBackgroundColor = element.getCssValue("background-color").replace(" ", "");
                return currentElementBackgroundColor.equals(rgbaCode);
            }

            public String toString() {
                return String.format("background RGBA color to be: %s of element located at %s ", rgbaCode, byLocator  );
            }

        };

    }
}
