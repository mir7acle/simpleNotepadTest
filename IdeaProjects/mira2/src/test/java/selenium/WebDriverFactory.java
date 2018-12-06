package selenium;


import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * <p>
 * Create, setup and initialize a Selenium WebDriver in one line. The class is
 * intended to encapsulate the current best practices for initializing the
 * requested webdriver. For a list of supported drivers, please see the
 * {@link DRIVERS} enum. Also please note that the stability and the level of
 * support varies between the drivers. This is a limitation of the
 * drivers/browsers themselves, and is not dependent on this class.
 * </p>
 *
 * <p>
 * As part of the process any external binaries that might be required for
 * managing the requested browser (e.g. chromedriver.exe) are updated and used.
 * All these external binaries are handled via the WebDriverManager -
 * <a href="https://github.com/bonigarcia/webdrivermanager">
 * https://github.com/bonigarcia/webdrivermanager </a>
 * </p>
 *
 * <p>
 * A best effort is made to setup the requested browser to support self-signed
 * certificates. As noted above - support varies between drivers. Please consult
 * the notes on the specific element of the {@link DRIVERS} enum.
 * </p>
 */
public class WebDriverFactory {
    /**
     * List of all known browsers. Support varies (sometimes wildly) between the
     * drivers. Please consult the notes for each element.
     */
    public static enum DRIVERS {
        /**
         * <p>
         * Good stability. In order to work with self-signed certificates:
         * create a separate "automation profile" and manually add exceptions
         * for all tested sites in that profile. Pass the profile name as
         * parameter when initializing.
         * </p>
         * <p>
         * Based on the Marionette Driver. Compatible with Firefox 50+.
         * </p>
         */
        FIREFOX,
        /**
         * Good stability. Has issues with self-signed certificates. No known
         * workaroind ATM.
         */
        FIREFOX_REMOTE,
        /**
         * Good stability. Works with self-signed certificates.
         */
        CHROME,
        /**
         * Good stability. Works with self-signed certificates.
         */
        CHROME_REMOTE,
        /**
         * Extremely poor stability. Not recommended. Compatibility only with
         * versions based on the chromium core (i.e. Opera 15+).
         */
        OPERA,
        /**
         * Poor stability. Not recommended.
         */
        IE,
        /**
         * Extremely poor stability. Not recommended.
         */
        EDGE,
        ANDROID,
        CHROME_EMU;

        /**
         * Return the proper enum value for a given string. Intended to parse
         * configurations.
         *
         * @param param
         *            - name of the driver as string e.g. from configuration
         * @return The enum value corresponding to the given string or NULL
         */
        public static DRIVERS parse(String param) {
            param = param.toLowerCase();

            if (param.equals("ie")) {
                return IE;
            }
            if (param.equals("edge")) {
                return EDGE;
            }
            if (param.equals("android")) {
                return ANDROID;
            }
            if (param.equals("opera")) {
                return OPERA;
            }
            if (param.equals("chrome_mobile")) {
                return CHROME_EMU;
            }
            if (param.equals("chrome")) {
                return CHROME;
            } else if (param.equals("firefox")) {
                return FIREFOX;
            } else if (param.contains("remote")) {
                if (param.contains("chrome")) {
                    return CHROME_REMOTE;
                } else if (param.contains("firefox")) {
                    return FIREFOX_REMOTE;
                }
            }

            return null;
        }
    }


    /**
     * Initialize a specified local driver with the default configuration. For
     * remote drivers see e.g. {@link #getDriver(String, String)} or
     * {@link #getDriver(DRIVERS, String)}.
     *
     * @param driverName
     *            - Name of the driver
     * @return Instance of the specified WebDriver
     */
    public static WebDriver getDriver(String driverName) {
        return WebDriverFactory.getDriver(DRIVERS.parse(driverName), null, null, null);
    }

    /**
     * Initialize a specified local driver with the default configuration. For
     * remote drivers see e.g. {@link #getDriver(String, String)} or
     * {@link #getDriver(DRIVERS, String)}.
     *
     * @param driver
     *            - WebDriver to use
     * @return Instance of the specified WebDriver
     */
    public static WebDriver getDriver(DRIVERS driver) {
        return WebDriverFactory.getDriver(driver, null, null, null);
    }

    /**
     * Initialize the specified driver using the specified resource. For remote
     * drivers the specified resource should be the address of a selenium hub.
     * For local Firefox - the name of a profile which to use as base (for use
     * with self-signed certificates). In all other cases the resource parameter
     * will be ignored.
     *
     * @param driverName
     *            - Name of the driver
     * @param resource
     *            - address of the selenium grid to connect to (for remote
     *            browsers) or name of a local firefox profile to use as
     *            template (self-signed certs configuration)
     * @return Instance of the specified WebDriver.
     */
    public static WebDriver getDriver(String driverName, String resource) {
        return WebDriverFactory.getDriver(DRIVERS.parse(driverName), resource, null, null);
    }

    /**
     * Initialize the specified driver using the specified resource. For remote
     * drivers the specified resource should be the address of a selenium hub.
     * For local Firefox - the name of a profile which to use as base (for use
     * with self-signed certificates). In all other cases the resource parameter
     * will be ignored.
     *
     * @param driver
     *            - WebDriver to use
     * @param resource
     *            - address of the selenium grid to connect to (for remote
     *            browsers) or name of a local firefox profile to use as
     *            template (self-signed certs configuration)
     * @return Instance of the specified WebDriver.
     */
    public static WebDriver getDriver(DRIVERS driver, String resource) {
        return WebDriverFactory.getDriver(driver, resource, null, null);
    }

    /**
     * Initialize the specified driver using the specified resource. For remote
     * drivers the specified resource should be the address of a selenium hub.
     * For local Firefox - the name of a profile which to use as base (for use
     * with self-signed certificates). In all other cases the resource parameter
     * will be ignored.
     *
     * @param driverName
     *            - Name of the driver
     * @param resource
     *            - address of the selenium grid to connect to (for remote
     *            browsers) or name of a local firefox profile to use as
     *            template (self-signed certs configuration)
     * @param eventListener
     *            - {@link WebDriverEventListener} to register with the
     *            webdriver
     * @return Instance of the specified WebDriver. For remote drivers - on the
     *         specified selenium hub.
     */
    public static WebDriver getDriver(String driverName, String resource, WebDriverEventListener eventListener) {
        return WebDriverFactory.getDriver(DRIVERS.parse(driverName), resource, eventListener, null);
    }

    /**
     * Initialize the specified driver using the specified resource. For remote
     * drivers the specified resource should be the address of a selenium hub.
     * For local Firefox - the name of a profile which to use as base (for use
     * with self-signed certificates). In all other cases the resource parameter
     * will be ignored.
     *
     * @param driver
     *            - WebDriver to use
     * @param resource
     *            - address of the selenium grid to connect to (for remote
     *            browsers) or name of a local firefox profile to use as
     *            template (self-signed certs configuration)
     * @param eventListener
     *            - {@link WebDriverEventListener} to register with the
     *            webdriver
     * @param mobileEmulation
     *            - optional. Custom capabilities for chrome.
     * @return Instance of the specified WebDriver. For remote drivers - on the
     *         specified selenium hub.
     */
    public static WebDriver getDriver(DRIVERS driver, String resource, WebDriverEventListener eventListener,
                                      HashMap<String, Object> mobileEmulation) {
        if (driver == null) {
            throw new IllegalArgumentException(
                    "Driver is mandatory. Probably a string was not parsed to a supported driver");
        }
        if (driver.toString().contains("REMOTE") && (resource == null)) {
            throw new IllegalArgumentException("Remote address is required for remote drivers");
        }

        // GitHub token for WebDriverManager. For explanation see:
        // https://github.com/bonigarcia/webdrivermanager#known-issues
        System.setProperty("wdm.gitHubTokenName", "webdrivermanager");
        System.setProperty("wdm.gitHubTokenSecret", "ed14acb733d33b2b044890c5e407f8288aa00fdc");

        int attempts = 0;
        RuntimeException err = null;
        WebDriver browser = null;
        while ((browser == null) && (attempts++ < 5)) {
            if (attempts > 1) {
            }

            try {
                switch (driver) {
                    case IE:
                        browser = getIE();
                        break;
                    case EDGE:
                        browser = getEdge();
                        break;
                    case OPERA:
                        browser = getOpera();
                        break;
                    case FIREFOX_REMOTE:
                        browser = getRemoteFirefoxDriver(new URL(resource));
                        break;
                    case FIREFOX:
                        browser = getFirefoxDriver();
                        break;
                    case CHROME_REMOTE:
                        browser = getRemoteChromeDriver(new URL(resource));
                        break;
                    case CHROME_EMU:
                        browser = getChromeEmu(new HashMap<String, Object>());
                        break;
                    case CHROME:
                    default:
                        browser = getChromeDriver();
                        break;
                }
            } catch (MalformedURLException e) {
                err = new InvalidParameterException("Remote address is malformed");
                break;
            } catch (RuntimeException e) {
                err = e;
            }
        }
        if ((browser == null) && (err != null)) {
            throw err;
        }

        if (eventListener != null) {
            browser = new EventFiringWebDriver(browser).register(eventListener);
        }
        if (mobileEmulation != null && eventListener != null) {
            browser = getChromeEmu(mobileEmulation);
            browser = new EventFiringWebDriver(browser).register(eventListener);
        }

        return browser;
    }

    /**
     * Internet Explorer specific initialization.
     *
     * @return WebDriver instance commanding a local Internet Explroer
     */
    private static WebDriver getIE() {
        InternetExplorerDriverManager.iedriver().setup();
//        InternetExplorerDriverManager.getInstance().version("3.3.0").setup();
//        InternetExplorerDriverManager.getInstance().setup();

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        return new InternetExplorerDriver(capabilities);
    }

    /**
     * Microsoft Edge specific initialization.
     *
     * @return WebDriver instance commanding a local Edge
     */
    private static WebDriver getEdge() {
        EdgeDriverManager.edgedriver().setup();
//        EdgeDriverManager.getInstance().setup();

        DesiredCapabilities capabilities = DesiredCapabilities.edge();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        return new EdgeDriver(capabilities);
    }

    /**
     * Opera specific initialization.
     *
     * @return WebDriver instance commanding a local Opera
     */
    private static WebDriver getOpera() {
        OperaDriverManager.operadriver().setup();
//        OperaDriverManager.getInstance().setup();

        DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        return new OperaDriver();
    }


    /**
     * Firefox specific initialization.
     *
     * If a profileName is provided the specified profile will be used as a base
     * when instantiating the browser. This is intended for supporting test
     * servers with self-signed certificates until the fix for this feature is
     * released.
     *
     * If a specific non-default Firefox binary must be used, path to that
     * binary may be specified in the "firefoxBinary" system property.
     *
     * @return - WebDriver instance commanding a local Firefox
     */
    private static WebDriver getFirefoxDriver() {
        FirefoxDriverManager.firefoxdriver().setup();
//        FirefoxDriverManager.getInstance().version("0.17.0").setup();
//        FirefoxDriverManager.getInstance().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(Level.OFF);

        String firefoxBinary = System.getProperty("firefoxBinary");
        if (firefoxBinary != null) {
            options.setBinary(firefoxBinary);
        }

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, getLoggingPreferences());
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("moz:firefoxOptions", options);

        return new FirefoxDriver(capabilities);
    }

    private static LoggingPreferences getLoggingPreferences() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.OFF);
        logPrefs.enable(LogType.CLIENT, Level.OFF);
        logPrefs.enable(LogType.DRIVER, Level.OFF);
        logPrefs.enable(LogType.PERFORMANCE, Level.OFF);
        logPrefs.enable(LogType.PROFILER, Level.OFF);
        logPrefs.enable(LogType.SERVER, Level.OFF);
        return logPrefs;
    }

    /**
     * Remote Firefox specific initialization
     *
     * @return - WebDriver instance commanding a remote Firefox
     */
    private static WebDriver getRemoteFirefoxDriver(URL remoteAddress) {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability("acceptInsecureCerts", true);
        return new RemoteWebDriver(remoteAddress, capabilities);
    }

    /**
     * Chrome specific initialization
     *
     * @return WebDriver instance commanding a local Chrome
     */
    private static WebDriver getChromeDriver() {
        ChromeDriverManager.chromedriver().setup();
//        ChromeDriverManager.getInstance().setup();

        LoggingPreferences logPrefs = getLoggingPreferences();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeDriver chrome = new ChromeDriver(capabilities);

        return chrome;
    }

    /**
     * Chrome specific initialization
     *
     * @return WebDriver instance commanding a local Chrome with custom
     *         capabilities
     */
    private static WebDriver getChromeEmu(HashMap<String, Object> mobileEmulation) {
        ChromeDriverManager.chromedriver().setup();
//        ChromeDriverManager.getInstance().setup();

        LoggingPreferences logPrefs = getLoggingPreferences();

        HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        ChromeDriver chrome = new ChromeDriver(capabilities);

        return chrome;
    }

    private static WebDriver getRemoteChromeDriver(URL remoteAddress) {

        DesiredCapabilities capability = DesiredCapabilities.chrome();
        WebDriver chrome = new RemoteWebDriver(remoteAddress, capability);
        return chrome;
    }

}
