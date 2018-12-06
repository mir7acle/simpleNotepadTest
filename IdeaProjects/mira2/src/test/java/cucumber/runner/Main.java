package cucumber.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {
                "src/test/resources/features/",
        },
        glue = {"glue.steps"},
        plugin = {"pretty", "html:target", "json:target/cucumber.json"})

public class Main extends AbstractTestNGCucumberTests {


}
