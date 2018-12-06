package glue.steps;

import cucumber.runner.TestContext;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.ShopPage;

public class MyStepdefs {

    private TestContext testContext;

    public MyStepdefs(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("^browser \"([^\"]*)\"$")
    public void browser(String arg0) throws Throwable {
       this.testContext.getWebDriverManager().openWebDriver(arg0);
    }

    @And("^website loaded this address: \"([^\"]*)\"$")
    public void websiteLoadedThisAddress(String arg0) throws Throwable {
        this.testContext.getWebDriverManager().openURL(arg0);
    }

    @When("^click on Shop Menu$")
    public void clickOnShopMenu() throws Throwable {
//        HomePage homePage = this.testContext.getPageObjectManager().getHomePage();
        HomePage homePage = new HomePage(this.testContext.getWebDriverManager().getDriver());
        homePage.openShopMenu();
    }

    @And("^click on Home menu button$")
    public void clickOnHomeMenuButton() throws Throwable {
//        ShopPage shopPage = this.testContext.getPageObjectManager().getShopPage();
        ShopPage shopPage = new ShopPage(this.testContext.getWebDriverManager().getDriver());
        shopPage.clickHome();
    }

    @Then("^the Home page must contains only three Arrivals$")
    public void theHomePageMustContainsOnlyThreeArrivals() throws Throwable {
//        HomePage homePage = this.testContext.getPageObjectManager().getHomePage();
        HomePage homePage = new HomePage(this.testContext.getWebDriverManager().getDriver());
        homePage.checkCountArrivals(3);
    }

    @After
    public void tearDown() {
        this.testContext.getWebDriverManager().closeDriver();
    }

}
