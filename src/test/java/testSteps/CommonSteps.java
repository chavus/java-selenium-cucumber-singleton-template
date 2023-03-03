package testSteps;

import app.driver.Driver;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import utils.TestTracker;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


public class CommonSteps{

    private int currentStepDefIndex = 0; // Required for getStepName.

    @BeforeAll
    public static void beforeAll(){
    }

    @Before
    public void before(Scenario scenario){
//        System.out.print("Starting Scenario: " + scenario.getName());
    }

    @After
    public void after(Scenario scenario) {
            Driver.quit();
    }

    @AfterAll
    public static void afterAll(){
    }

    @BeforeStep
    public void beforeStep(Scenario scenario){
         String stepName = getStepName(scenario) ; // getStepName(scenario) must be called only once.
         TestTracker.get().setTestStep(stepName != null ? stepName : "<unidentified>"); // set current Test Step to TestTracker for global reference
//         System.out.println("Test Step: " + TestTracker.get().getTestStep());
    }

    private String getStepName(Scenario scenario){
        // IMPORTANT!!!: This method must be called only once in order to show correct step. Check there is only 1 usage.
        String stepName;
        try{
            Field f = scenario.getClass().getDeclaredField("delegate");
            f.setAccessible(true);
            io.cucumber.core.backend.TestCaseState sc = (io.cucumber.core.backend.TestCaseState) f.get(scenario);
            Field f1 = sc.getClass().getDeclaredField("testCase");
            f1.setAccessible(true);
            TestCase testCase = (TestCase) f1.get(sc);
            List<PickleStepTestStep> testSteps = testCase.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x).collect(Collectors.toList());
            stepName = testSteps.get(currentStepDefIndex).getStep().getKeyword()
                     + testSteps.get(currentStepDefIndex).getStep().getText();
            currentStepDefIndex += 1;
        }catch (Exception e){
            stepName = null;
        }
        return stepName;
    }
}
