package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openShopMenu() {
        WebElement shopElem = driver.findElement(By.linkText("Shop"));

        SoftAssert shopIsAvalable = new SoftAssert();
        shopIsAvalable.assertTrue(shopElem.isEnabled());
        shopIsAvalable.assertTrue(shopElem.isDisplayed());
        shopIsAvalable.assertAll();
        SoftAssert softAssert;
        shopElem.click();
    }

    public void checkCountArrivals(int count) {
        // Намира два елемента:
        // първия е нещо под тях с поделементи от клас "col4-1 first sub_column sub_column_1-0-1-0 sub_column_post_22"
        // втория е списъка с new arrivals
        List<WebElement> divs = driver.findElements(By.cssSelector("div[class^=\"themify_builder_sub_row\"]"));

        for (WebElement div : divs) {
            List<WebElement> arrivals = div.findElements(By.cssSelector("div[class^=\"col3-1\"]"));
            if (arrivals.size() != 0) {
                int arravalsCount = arrivals.size();
                Assert.assertEquals(arravalsCount, count);
                break;
            }
        }
    }
}
