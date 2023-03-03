package app.pages;

import app.customSeleniumUtils.CustomExpectedConditions;
import app.customSeleniumUtils.enums.WaitFor;
import app.driver.Driver;
import app.properties.AppProps;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Class containing many helper methods to locate and interact with elements. It is recommended to extend this class
 * in your page classes.
 */
public class Page {

    static int WAIT_FOR_DEFAULT_TIME = AppProps.DEFAULT_TIMEOUT;
    private static final WebDriverWait driverWait = new WebDriverWait(Driver.get(), Duration.ofSeconds(WAIT_FOR_DEFAULT_TIME));

    ///////////////////////////////////////////////////
    ////////////////// element ///////////////////////
    /////////////////////////////////////////////////
    public static WebElement element(By byLocator) {
        return Driver.get().findElement(byLocator);
    }

    public static List<WebElement> elements(By byLocator) {
        return Driver.get().findElements(byLocator);
    }

    public static WebElement element(By byLocator, WaitFor waitForCondition) {
        return element(byLocator, waitForCondition, WAIT_FOR_DEFAULT_TIME);
    }

    public static WebElement element(By byLocator, WaitFor waitForCondition, int timeInSeconds) {
        switch (waitForCondition) {
            case EXISTS:
                waitForElementToExist(byLocator, timeInSeconds);
                break;
            case DISPLAYED:
                waitForElementToBeVisible(byLocator, timeInSeconds);
                break;
            case INVISIBLE:
                waitForElementToBeInvisible(byLocator, timeInSeconds);
                break;
            case CLICKABLE:
                waitForElementToBeClickable(byLocator, timeInSeconds);
                break;
        }
        return Driver.get().findElement(byLocator);
    }

    ///////////////////////////////////////////////////
    /////////////// WebDriver Wait /////////////////
    /////////////////////////////////////////////////

    public static WebDriverWait driverWait(int timeInSeconds) {
        return new WebDriverWait(Driver.get(), Duration.ofSeconds(timeInSeconds));
    }

    public static WebDriverWait driverWait() {
        return driverWait;
    }

    ///////////////////////////////////////////////////
    //////////// waitForElementToExist /////////////////
    /////////////////////////////////////////////////
    public static WebElement waitForElementToExist(By byLocator) {
        // Element exists in the DOM.
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(WAIT_FOR_DEFAULT_TIME));
        return wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public static WebElement waitForElementToExist(By byLocator, int timeInSeconds) {
        // Element exists in the DOM.
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public static boolean waitForElementToNotExist(By byLocator) {
        // Element no longer exists in the DOM.
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(WAIT_FOR_DEFAULT_TIME));
        return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(byLocator)));
    }

    ///////////////////////////////////////////////////
    ////// waitForElementToBeVisible and Invisible ////////
    /////////////////////////////////////////////////
    public static WebElement waitForElementToBeVisible(By byLocator) {
        return waitForElementToBeVisible(byLocator, WAIT_FOR_DEFAULT_TIME);
    }

    public static WebElement waitForElementToBeVisible(By byLocator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public static void waitForElementToBeInvisible(By byLocator) {
        waitForElementToBeInvisible(byLocator, WAIT_FOR_DEFAULT_TIME);
    }

    public static void waitForElementToBeInvisible(By byLocator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }

    ///////////////////////////////////////////////////

    public static boolean waitForTextInElement(By byLocator, String text) {
        WebElement element = element(byLocator);
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(WAIT_FOR_DEFAULT_TIME));
        if (element.getTagName().equals("input")) {
            return wait.until(ExpectedConditions.attributeToBe(element, "value", text));
        } else {
            return wait.until(ExpectedConditions.textToBe(byLocator, text));
        }
    }

    public static void waitForTextNotEmpty(By byLocator) {
        driverWait.until(ExpectedConditions.textMatches(byLocator, Pattern.compile("\\S")));
    }

    public static void waitForElementToBeClickable(By byLocator) {
        waitForElementToBeClickable(byLocator, WAIT_FOR_DEFAULT_TIME);
    }


    public static void waitForElementToBeClickable(By byLocator, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(byLocator));
    }

    public static WebElement waitForElementToBeClickable(WebElement webElement) {
        return driverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitForClassInElement(By byLocator, String className) {
        driverWait.until(CustomExpectedConditions.elementHasClass(byLocator, className));
    }

    public static void waitForClassInElement(WebElement webElement, String className) {
        driverWait.until(CustomExpectedConditions.elementHasClass(webElement, className));
    }

    public static void waitForClassNotInElement(WebElement webElement, String className) {
        driverWait.until(CustomExpectedConditions.elementDoesNotHaveClass(webElement, className));
    }

    public static void waitForNumberOfElementsByLocator(By byLocator, int qtyExpectedOfElements) {
        driverWait.until(ExpectedConditions.numberOfElementsToBe(byLocator, qtyExpectedOfElements));
    }

    public static void waitForElementToStopMoving(By byLocator) {
        driverWait.until(CustomExpectedConditions.elementStopsScrolling(byLocator));
    }

    ///////////////////////////////////////////////////
    ////// Boolean Methods for Elements Exists /////////
    ///////////////////////////////////////////////
    public static boolean elementExists(By byLocator) {
        try{
            return Driver.get().findElements(byLocator).size() > 0;
        }catch (InvalidArgumentException e){
            // Work around for unknown issue with uniqueContextId
            if (e.getMessage().split("\n")[0].equals("invalid argument: uniqueContextId not found")) {
                return false;
            } else {
                throw e;
            }

        }
    }

    public static boolean elementHasClass(By byLocator, String className) {
        return Arrays.asList(Driver.get()
                        .findElement(byLocator)
                        .getAttribute("class")
                        .split("\\s+"))
                .contains(className);
    }

    public static boolean elementHasClass(WebElement element, String className) {
        return Arrays.asList(element
                        .getAttribute("class")
                        .split("\\s+"))
                .contains(className);
    }

    public static boolean elementHasTitle(By byLocator, String titleName) {
        return Arrays.asList(Driver.get()
                        .findElement(byLocator)
                        .getAttribute("title")
                )
                .contains(titleName);
    }

    ///////////////////////////////////

    public static void switchToNextWindowInHandles() {
        waitByTime(2000); // Wait for page to be opened. Can be improved
        if (Driver.get().getWindowHandles().size() > 1) {
            String currentHandle = Driver.get().getWindowHandle();
            String newHandle = Driver.get().getWindowHandles().stream().filter(handle -> !handle.equals(currentHandle)).findFirst().get();
            Driver.get().switchTo().window(newHandle);
            Driver.get().manage().window().maximize();
        } else {
            System.out.println("Window not switched. There is only 1 window running");
        }
    }

    public static void switchToLastWindowInHandles() {
        waitByTime(3000); // Wait for page to be opened. Can be improved
        String currentHandle = null;
        try{
            currentHandle = Driver.get().getWindowHandle();
        }catch (NoSuchWindowException e){
            System.out.println("Current driver window is now closed.");
        }
        ArrayList<String> handlesList = new ArrayList<>(Driver.get().getWindowHandles());
        int handlesSize = handlesList.size();
        String lastHandle = handlesList.get(handlesSize - 1);
        if (lastHandle.equalsIgnoreCase(currentHandle)){
            System.out.println("Window not switched. Already on desired Window.");
        } else{
            Driver.get().switchTo().window(lastHandle);
            Driver.get().manage().window().maximize();
        }
    }

    public static void waitByTime(int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////////////////////////////////
    ///////////////// Element actions //////////////
    ///////////////////////////////////////////////

    public static void clickElement(By byLocator) {
        element(byLocator, WaitFor.CLICKABLE).click();
    }

    public static void clickElementWithExactText(String text){
        clickElementWithExactText(text, "*");
    }

    public static void clickElementWithExactText(String text, String element){
        By elementXpath = By.xpath(String.format("//%s[text()='%s']",element, text));
        clickElement(elementXpath);
    }

    public static void enterText(By byLocator, String text){
        WebElement inputElement = waitForElementToBeVisible(byLocator);
        inputElement.clear();
        inputElement.sendKeys(text);
    }

    public static void enterText(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static void setInputFile(By byLocator, String pathAndFileName){
        WebElement inputElement = waitForElementToExist(byLocator);
        inputElement.sendKeys(pathAndFileName);
    }

    ///////////////////////////////////////////////////
    ///////////////// Frames Handling //////////////
    ///////////////////////////////////////////////

    public static void switchToDefaultFrame(By elementToExistLocator) {
        Driver.get().switchTo().defaultContent();
        waitForElementToExist(elementToExistLocator); // Replace for customWaitForElementToExist if start seeing "org.openqa.selenium.InvalidArgumentException: invalid argument: uniqueContextId not found"
    }

    public static void switchToFrame(WebElement frameElement, By elementToExistLocator) {
        Driver.get().switchTo().frame(frameElement);
        waitForElementToExist(elementToExistLocator); // Replace for customWaitForElementToExist if start seeing "org.openqa.selenium.InvalidArgumentException: invalid argument: uniqueContextId not found"
    }

    public static void switchToFrame(String frameID, By elementToExistLocator) {
        waitForElementToExist(By.id(frameID));
        Driver.get().switchTo().frame(frameID);
        waitForElementToExist(elementToExistLocator); // Replace for customWaitForElementToExist if start seeing "org.openqa.selenium.InvalidArgumentException: invalid argument: uniqueContextId not found"
    }

    /**
     * Method to efficiently handle "org.openqa.selenium.InvalidArgumentException: invalid argument: uniqueContextId not found",
     * caused by switching frames and trying to locate an element immediately.
     *
     * @param elementToExistLocator locator statement of type By of the element
     */
    private static void customWaitForElementToExist(By elementToExistLocator) {
        int MAX_TRY_TIME_IN_MILLS = 2000;
        int WAIT_TIME_TO_TRY_AGAIN_IN_MILLS = 10;
        int tries = (MAX_TRY_TIME_IN_MILLS / WAIT_TIME_TO_TRY_AGAIN_IN_MILLS);
        for (int i = 0; i <= tries; i++) {
            try {
                waitForElementToExist(elementToExistLocator);
                break;
            } catch (InvalidArgumentException e) {
                if (e.getMessage().split("\n")[0].equals("invalid argument: uniqueContextId not found") && i != tries) {
                    waitByTime(10);
                } else {
                    throw e;
                }
            }
        }
    }
}
