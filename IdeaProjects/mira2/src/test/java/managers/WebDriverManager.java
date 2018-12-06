package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.WebDriverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverManager {

    private WebDriver driver;
    private static Properties prop;

    public WebDriver getDriver() {
        return driver;
    }

    public void openURL(String url) {
        driver.navigate().to(url);
    }

    public void openWebDriver(String browser) {

        WebDriverFactory wdfChrome;
        if (browser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", "browsers\\chromedriver.exe");
            driver = new ChromeDriver();


        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "browsers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    private void setWebDriver(String url, String browser, String browserPath) {
        System.setProperty("webdriver.chrome.driver", browserPath + browser + "driver.exe");
        if (browser.equalsIgnoreCase("Chrome")) {
            this.driver = new ChromeDriver();
        }
        this.driver.navigate().to(url);
    }

    private void getServerProperties() {
        prop = new Properties();
        InputStream in;
        FileInputStream input = null;

        try {
            input = new FileInputStream("src\\test\\resources\\server.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
