package com.testC1.pages;

import com.beust.ah.A;
import com.testC1.utils.MainUtils;
import io.cucumber.java.sl.In;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.sikuli.script.Screen;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.math.BigDecimal;
import java.security.Key;
import java.util.*;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.testC1.hooks.myHooks.driver;

public class dashPage extends MainUtils {

    WebDriverWait wait;
    @FindBy(xpath = "*//label[contains(text(),'Username:')]/following-sibling::input[1]")
    private WebElement userName;
    @FindBy(xpath = "*//label[contains(text(),'Password:')]/following-sibling::input[1]")
    private WebElement password;
    @FindBy(xpath = "*//label[contains(text(),'Email:')]/following-sibling::input[1]")
    private WebElement email;
    @FindBy(xpath = "*//label[contains(text(),'Comm')]/following-sibling::textarea")
    private WebElement comments;
    @FindBy(xpath = "*//label[contains(text(),'Disa')]/following-sibling::input")
    private WebElement disableEle;
    @FindBy(xpath = "*//label[contains(text(),'Fil')]/following-sibling::input")
    private WebElement uploadFile;
    @FindBy(xpath = "*//label[contains(text(),'Male')]/preceding-sibling::input")
    private WebElement radioMale;
    @FindBy(xpath = "(//label[text()='Female']/preceding-sibling::input)[last()]")
    //[last()] gives us the one directly before the Female label (which is the actual Female input).
    private WebElement radioFemale;
    @FindBy(xpath = "(//label[contains(text(),'Date of Birth:')]/following-sibling::input)[1]")
    private WebElement date;
    @FindBy(xpath = "*//label[contains(text(),'Java')]/preceding-sibling::input[1]")
    private WebElement skill1;
    @FindBy(xpath = "*//label[contains(text(),'Python')]/preceding-sibling::input[1]")
    private WebElement skill2;
    @FindBy(xpath = "*//label[contains(text(),'Country')]/following-sibling::select[1]")
    private WebElement country;
    @FindBy(xpath = "*//label[contains(text(),'Multi-select:')]/following-sibling::select[1]")
    private WebElement multiselect;
    @FindBy(xpath = "*//th[contains(text(),'Name')]/ancestor::table")
    private WebElement resultTable;
    @FindBy(xpath = "*//th[contains(text(),'Name')]/ancestor::table/tbody/tr")
    private List<WebElement> rows;
    @FindBy(xpath = "*//button[contains(text(),'Show Alert')]")
    private WebElement buttonshowAlert;
    @FindBy(xpath = "*//button[contains(text(),'Show Modal')]")
    private WebElement buttonshowModal;
    @FindBy(xpath = "*//button[contains(text(),'Open Tab')]")
    private WebElement buttonopentab;
    @FindBy(xpath = "*//button[contains(text(),'Open Window')]")
    private WebElement buttonopenWindow;
    @FindBy(xpath = "*//button[contains(text(),'Open Frame Window')]")
    private WebElement buttonopenFrameWindow;

    @FindBy(xpath = "*//button[contains(text(),'1D')]")
    private WebElement dynRate1D;
    @FindBy(xpath = "*//button[contains(text(),'1M')]")
    private WebElement dynRate1M;
    @FindBy(xpath = "*//button[contains(text(),'1Y')]")
    private WebElement dynRate1Y;
    @FindBy(xpath = "*//button[contains(text(),'1W')]")
    private WebElement dynRate1W;
    @FindBy(xpath = "*//p[contains(text(),'Returns')]/child::span")
    private WebElement dynarate;


    public dashPage() {
        PageFactory.initElements(driver(), this);
        wait = new WebDriverWait(driver(), Duration.ofSeconds(50));

    }

    public void openURl() throws InterruptedException {
        driver().navigate().to(get("url"));
        driver().get(get("url"));
    }

    public void getPageTitleHeader(String header, String title) {
        Assert.assertTrue((title.equals(driver().getTitle()) &&
                        header.equals(driver().findElement(By.xpath("*//h1")).getText())),
                "Title & Header not matching with the requirement");
        LOGGER.info("header:Expected :" + title + " Actual : " + driver().getTitle());
    }


    public void entersRequiredValues(String Filepath) {
        userName.sendKeys(CSVData.get("username"));
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(18)); // implicit wait
        password.sendKeys(CSVData.get("password"));
        email.sendKeys("ett@dty.com");
        WebElement sq = driver().findElement(By.xpath("*//label[contains(text(),'Comm')]/following-sibling::textarea"));
        this.wait.until(ExpectedConditions.elementToBeClickable(sq)); // explicit wait
        comments.sendKeys("This is just a test !\n I repeat this is just a test");
        Assert.assertFalse(disableEle.isEnabled(), "Element is enabled");
        LOGGER.info(disableEle + "::Element is enabled");
        uploadFile.sendKeys(new File(Filepath).getAbsolutePath());
//        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);
//       WebElement element= wait.until(driver1 -> ExpectedConditions.elementToBeClickable(uploadFile).apply(driver1));

    }

    public void validateSel(List<List<String>> table) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", radioMale);
        radioMale.click();
        radioFemale.click();
        radioMale.click();
        date.sendKeys("12/09/1977");
        skill2.click();
        Actions actions = new Actions(driver());
        actions.click(radioFemale).build().perform();
        Assert.assertTrue(radioFemale.isSelected());
        Select select = new Select(country);
        select.selectByVisibleText("USA");
        select = new Select(multiselect);
        select.selectByVisibleText("Python");
        select.selectByVisibleText("Java");
//        List<String> as= rows.stream().map(WebElement::getText).collect(Collectors.toList());
//        System.out.println(as);
        System.out.println(rows.stream().map(WebElement::getText).filter(
                a -> a.contains(table.get(0).get(0)) && a.contains(table.get(0).get(1))
        ).findFirst().orElse("Not Found"));


    }

    public void validateButtonsAndActions() {
        buttonshowAlert.click();
        Alert alert = driver().switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        buttonshowAlert.click();
        alert.dismiss();
        buttonshowModal.click();
        this.wait.until(ExpectedConditions.textToBePresentInElement(driver().findElement(By.xpath("*//h3[contains(text(),'This is a Modal')]/parent::div")), "This is a Modal"));
        Assert.assertTrue(driver().findElement(By.xpath("*//h3[contains(text(),'This is a Modal')]/parent::div")).getText().contains("This is a Modal"), "Not Found");
        WebElement click = driver().findElement(By.xpath("*//button[text()='Close']"));
        click.click();
        List<String> windows = new ArrayList<>();
        buttonopentab.click();

        windows = driver().getWindowHandles().stream().toList();
        driver().switchTo().window(windows.get(1));
        System.out.println(driver().getCurrentUrl() + "\n" + driver().getTitle());
        driver().switchTo().window(windows.get(1)).close();
        driver().switchTo().window(windows.get(0));
        System.out.println(driver().getCurrentUrl() + "\n" + driver().getTitle());
        buttonopenWindow.click();
        windows = driver().getWindowHandles().stream().toList();  // driver().getWindowHandles():::to take the windows value
        driver().switchTo().window(windows.get(1));
        System.out.println(driver().getCurrentUrl() + "\n" + driver().getTitle());
        driver().switchTo().window(windows.get(1)).close();
        driver().switchTo().window(windows.get(0));


        buttonopenFrameWindow.click();
        windows = driver().getWindowHandles().stream().toList();
        driver().switchTo().window(windows.get(1));
        this.wait.until(ExpectedConditions.elementToBeClickable(driver().findElement(By.tagName("iframe"))));
        driver().switchTo().frame(driver().findElement(By.tagName("iframe")));
        System.out.println(driver().findElement(By.tagName("p")).getText());
        driver().switchTo().defaultContent();   // is used to get out from the frame


    }

    public void validateValueOfDynamic() {
    Actions actions = new Actions(driver());
    actions.scrollToElement(dynRate1D);
        dynRate1D.click();
        System.out.println(new BigDecimal(dynarate.getText().replace("%","")));
        dynRate1M.click();
        System.out.println(new BigDecimal(dynarate.getText().replace("%","")));
        dynRate1W.click();
        System.out.println(dynarate.getText());
        dynRate1Y.click();
        System.out.println(dynarate.getText());

    }
}
