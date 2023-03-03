package app.pages;

import org.openqa.selenium.By;

public class Login extends BasePage{

    private static final String PAGE_PATH = "/users/sign_in";

    private static final By EMAIL_INPUT = By.id("user_email");
    private static final By PASSWORD_INPUT = By.id("user_password");
    private static final By SIGN_IN_BUTTON = By.cssSelector("input[name='commit']");
    private static final By ERROR_MESSAGE = By.cssSelector("div.ht-alert__content p");

    public static void attemptToLogin(String username, String password){
        enterText(EMAIL_INPUT, username);
        enterText(PASSWORD_INPUT, password);
        clickElement(SIGN_IN_BUTTON);
    }

    public static String getErrorMessage(){
        return element(ERROR_MESSAGE).getText();
    }
}
