package managers;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ShopPage;

public class PageObjectManager {

    private WebDriver webDriver;

    public PageObjectManager(WebDriver driver) {
        this.webDriver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(this.webDriver);
    }

    public ShopPage getShopPage() {
        return new ShopPage(this.webDriver);
    }
}
