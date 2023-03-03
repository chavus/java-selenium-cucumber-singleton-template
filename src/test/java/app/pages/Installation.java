package app.pages;

import app.driver.Driver;
import app.properties.AppProps;

public class Installation extends BasePage{

    private static String PAGE_PATH = "/docs/installation/";

    public static void go(){
        Driver.get().get(AppProps.BASE_URL + PAGE_PATH);
    }

    public static boolean imHere(){
        return Driver.get().getCurrentUrl().contains(PAGE_PATH);
    }

}
