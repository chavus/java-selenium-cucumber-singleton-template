# Java-Selenium-Cucumber with Singleton Driver Framework Template
This is a complete BDD automation framework template for web functional testing using Java, Cucumber and Selenium.
It is based on the Page Object model, using a singleton WebDriver manager `Driver` and it provides all the required
pieces to start creating page classes and tests cases.

Even though I intended to make this template as complete as possible, it is susceptible to improvements and modifications
based on your experience and requirements. I just wanted to share an approach that has worked for me on several projects.
I hope you find it helpful.

## Requirements
- Java SDK
- Maven

## Main Libraries
- Cucumber-7 with Junit-5
- Selenium 4.8

## Project Structure Overview
The project has a single module: `test`, which contains the test classes and feature files. All the Selenium code, is
contained inside the `app` folder, including properties management `AppProps` and utilities `AppUtils`.

## Usage:
1. Add properties that need to be passed to the app classes in the `app.properties.AppProps` class. This template 
has already some predefined. For example: `BASE_URL` and `DEFAULT_TIMEOUT`.
2. Create new page classes under `pages`. Make new pages classes to extend `Page` class, which contains commonly used methods
to interact with a web page using Selenium. Then, add the element locators and methods for that page. Take `pages.Home` as an example.
3. Customize the creation of the web drivers in `app.driver.Driver`. Add arguments and preferences as required.
**Note: the `Driver` class is using the [Selenium Manager(Beta)](https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/#1-selenium-manager-beta) approach to create the driver, which doesn't require the
driver files. Modify the `Driver` class with any other suggested approach from Selenium if required.**
4. Create a [test steps](https://cucumber.io/docs/cucumber/step-definitions/?lang=java) file under `testSteps`.
`@Before` and `@After` steps are included in the `testSteps.CommonSteps` class. To call a specific page class, just
import the class and call it static method. Take `testSteps.LoginSteps` as an example.
5. Create [feature files](https://cucumber.io/docs/gherkin/reference/#steps) using existing test steps.
6. Execute tests typing `mvn test` in the terminal.

## Test properties
Test properties, like the app URL, credentials, etc., can be retrieved from several places using the following order of precedence:
1. Command argument `-Dkey=<value>`
2. `test.properties` file
3. Default value (if applicable)

`test.properties` file is located at root directory by default. **Note: test.properties is usually not shared in a
repository since it might contain sensitive information like credentials**

## Execution methods:
Test execution is triggered by "mvn test" command in a terminal from the project directory.
- `mvn test`: takes test properties values from test.properties file
- `mvn test -DtestPropertyKey=<testPropertyValue>`: Overwrites values from test.properties
- `mvn test -DexcludedGroups="properties" -Dgroups="login | navigation"`: executes tests with -Dgroups tags and
  excludes the ones in -DexcludedGroups. Refer to [Cucumber-Junit tags filtering](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#tags)

## Parallel testing
This framework **does not** support parallel test execution using the [Cucumber JUnit Platform Engine](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#parallel-execution) settings.
Some refactor, probably in the Driver class, is required. I will update this template if support is added in the future. 

## Other notes
- Features are run in alphabetical order of the .feature file name