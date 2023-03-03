package app.customSeleniumUtils;

import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.TimeoutException;
import org.opentest4j.AssertionFailedError;

public class CustomAssertions {

    /**
     * Asserts that an executable does not throw a TimeOutException from selenium.
     * @param executable
     * @param message
     */
    public static void assertNoTimeOut(Executable executable, String message){
        try {
            executable.execute();
        }
        catch (Throwable e) {
            if (e.getClass().getName().equals(TimeoutException.class.getName())){
                throw new AssertionFailedError(String.format("%s: Execution of method threw %s"
                        ,message
                        ,TimeoutException.class.getName()),e);
            }
            throw new RuntimeException(e);
        }
    }

}
