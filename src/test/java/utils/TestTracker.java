package utils;

public class TestTracker {
    private static TestTracker testTrackerInstance = null;

    private String testStep;

    public static TestTracker get(){
        if (testTrackerInstance ==  null){
            testTrackerInstance = new TestTracker();
        }
        return testTrackerInstance;
    }

    public String getTestStep() {
        return testStep;
    }

    public void setTestStep(String testStep) {
        this.testStep = testStep;
    }

}
