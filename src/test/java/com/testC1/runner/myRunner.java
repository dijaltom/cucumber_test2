package com.testC1.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber_report.html",
                "json:target/cucumber_report.json",
                "rerun:target/failed_scenario.txt",
        },
        monochrome = true,
        tags = "@Test",
        features = "src/test/resources/Features",
        glue = {"com.testC1.steps","com.testC1.hooks"}
)
public class myRunner extends AbstractTestNGCucumberTests {
        @DataProvider(parallel = false)
        public Object[][] Scenarios()
        {
            return super.scenarios();
        }
}
