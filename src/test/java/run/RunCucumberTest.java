package run;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
// ConfigurationParameter can also be defined under resources/junit-platform.properties. https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#configuration-options
//@ConfigurationParameter(key = "cucumber.publish.quiet", value = "true")
//@ConfigurationParameter(key = "cucumber.plugin",value = "pretty, json:target/cucumber-report/cucumber.json,html:target/cucumber-report/test-report.html")
//@ConfigurationParameter(key="cucumber.filter.tags", value="@login")
public class RunCucumberTest{
}


