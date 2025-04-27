package com.testC1.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber_report.html",
                "rerun:target/failed_scenario_2.txt",
        },
        monochrome = true,
        tags = "",
        features = "@target/failed_scenario.txt",
        glue = {"com.testC1.steps","com.testC1.hooks"}
)
public class myFailedRunner extends AbstractTestNGCucumberTests {
        @DataProvider(parallel = false)
        public Object[][] Scenarios()
        {
            return super.scenarios();
        }
}
