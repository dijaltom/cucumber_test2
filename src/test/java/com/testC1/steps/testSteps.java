package com.testC1.steps;
import com.testC1.pages.dashPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.bouncycastle.asn1.ocsp.ResponderID;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static com.testC1.utils.MainUtils.loadCSVData;

public class testSteps {


    @When("User Navigates to Base URL")
    public void userNavigatesToBaseURL() throws InterruptedException {
        dashPage page = new dashPage();
        page.openURl();
    }

    @Then("Validate value of {string} and {string}")
    public void validateValueOfAnd(String header, String title) {
        dashPage page = new dashPage();
        page.getPageTitleHeader(header,title);
    }

    @And("User Enters Required Values {string}")
    public void userEntersRequiredValues(String filePath) {
        dashPage page = new dashPage();
        page.entersRequiredValues(filePath);
    }

    @Then("Validate selection part")
    public void validateSelectionPart(List<List<String>> table) {
        dashPage page = new dashPage();
        page.validateSel(table);
    }

    @Then("Validate Buttons and Actions")
    public void validateButtonsAndActions() {
        dashPage page = new dashPage();
        page.validateButtonsAndActions();
    }

    @Then("Validate dynamic items")
    public void validateDynamicItems() {
        dashPage page = new dashPage();
        page.validateValueOfDynamic();
    }

    @Given("load CSV Data from {string} for header row contains {string}")
    public void loadCSVDataFromForHeaderRowContains(String path, String headerVal) throws IOException {
       loadCSVData(path,headerVal);
    }

    @Then("take reponse from {string}  and using {string} method with {string}")
    public void takeReponseFromAndUsingMethod(String url, String method,String path) {

        Response response;

        response = RestAssured.given().baseUri(url).basePath(path).get();
        System.out.println(response);
        System.out.println(response.jsonPath().getString("data"));
        List<Map<String, Object>> dataList = response.jsonPath().getList("data");

    }
}







