package com.testC1.hooks;

import com.testC1.utils.MainUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class myHooks extends MainUtils {
    public static WebDriver driver;
    @Before
    public void setUpBrowser()
    {
        System.setProperty("webdriver.chrome.driver", get("driver"));
        driver= new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void CloseBrowser(Scenario scenario){
        if(scenario.isFailed()){
        try {
            File q = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] qdfg = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            File thh= new File("result.png");
            FileUtils.copyFile(q,thh);

            scenario.attach(qdfg,"image/png",scenario.getName());

            // Write additional message to report for failed scenario
            scenario.attach(LocalDate.now()+ Arrays.toString("Screenshot taken for failed scenario.".getBytes()),"text/plain","::");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        driver.quit();
    }
}
