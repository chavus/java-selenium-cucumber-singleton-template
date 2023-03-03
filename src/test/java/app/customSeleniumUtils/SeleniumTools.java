package app.customSeleniumUtils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.codec.w3c.W3CHttpCommandCodec;
import org.openqa.selenium.remote.codec.w3c.W3CHttpResponseCodec;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumTools {

    public static String getLocatorStringFromWebElement(WebElement webElement){

        String elementString = webElement.toString();
        elementString = elementString.substring(1,elementString.length()-1);
        String[] webElementAsArray = elementString.split("->");
        List<String> webElementList = Arrays.stream(webElementAsArray).map(item -> item.replace("]]", ""))
                                            .collect(Collectors.toList());
        return String.join(">", webElementList.subList(1,webElementAsArray.length));
    }

    public static RemoteWebDriver createDriverFromSession(String sessionId, URL command_executor){
        CommandExecutor executor = new HttpCommandExecutor(command_executor) {

            @Override
            public Response execute(Command command) throws IOException {
                Response response;
                if (command.getName().equals("newSession")) {
                    response = new Response();
                    response.setSessionId(sessionId);
                    response.setStatus(0);
                    response.setValue(Collections.<String, String>emptyMap());

                    try {
                        Field commandCodec;
                        commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
                        commandCodec.setAccessible(true);
                        commandCodec.set(this, new W3CHttpCommandCodec());

                        Field responseCodec;
                        responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
                        responseCodec.setAccessible(true);
                        responseCodec.set(this, new W3CHttpResponseCodec());
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    response = super.execute(command);
                }
                return response;
            }
        };

        return new RemoteWebDriver(executor, new DesiredCapabilities());
    }

}
