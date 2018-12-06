package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class ShopPage {

    private WebDriver driver;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHome() {
        WebElement shopElem = driver.findElement(By.linkText("Home"));

        SoftAssert shopIsAvalable = new SoftAssert();
        shopIsAvalable.assertTrue(shopElem.isEnabled());
        shopIsAvalable.assertTrue(shopElem.isDisplayed());
        shopIsAvalable.assertAll();
        SoftAssert softAssert;
        shopElem.click();
    }
}