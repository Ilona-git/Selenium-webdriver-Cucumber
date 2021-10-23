// Created by Viacheslav (Slava) Skryabin 04/01/2011
package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static WebDriver driver;

    private static Map<String, Object> testData = new HashMap<>();

    private static String timestamp;

    public static void setTestData(String key, Object value) {
        testData.put(key, value);
    }

    public static Map<String, Object> getTestDataMap(String key) {
        return (Map<String, Object>) testData.get(key);
    }

    public static Integer getTestDataInteger(String key) {
        return (Integer) testData.get(key);
    }

    public static String getTestDataString(String key) {
        return (String) testData.get(key);
    }

    public static void setTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("+yyyy-MM-dd-h-mm-sss");
        timestamp = dateFormat.format(new Date());
    }

    public static String getTimestamp() {
        return timestamp;
    }


    public static Actions getActions() {
        return new Actions(driver);
    }

    public static WebDriverWait getWait() {
        return getWait(getConfig().explicitTimeout);
    }

    public static WebDriverWait getWait(int timeout) {
        return new WebDriverWait(driver, timeout);
    }

    public static JavascriptExecutor getExecutor() {

        return (JavascriptExecutor) driver;
    }

    public static Config getConfig() {
        try {
            String configPath = System.getProperty("user.dir") + "/src/test/resources/data/config.yml";
            return new Yaml().load(new FileInputStream(new File(configPath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static Map<String, String> getPosition(String fileName) {
        Map<String, String> position = getData(fileName);
        String updatedTitle = position.get("title");
        if (updatedTitle != null) {
            updatedTitle += timestamp;
            position.put("title", updatedTitle);
        }
        return position;
    }

    public static Map<String, String> getCandidate(String fileName) {
        Map<String, String> candidate = getData(fileName);
        String updatedTitle = candidate.get("title");
        if (updatedTitle != null) {
            updatedTitle += timestamp;
            candidate.put("title", updatedTitle);
        }
        return candidate;
    }



    public static Map<String, String> getData(String fileName) {
        try {
            String path = System.getProperty("user.dir") + "/src/test/resources/data/" + fileName + ".yml";
            File file = new File(path);
            InputStream stream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            return yaml.load(stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initialize() {
        initialize(getConfig().browser, "local", getConfig().isHeadless);
    }

    public static void teardown() {
        driver.quit();
    }

    public static void initialize(String browser, String testEnv, boolean isHeadless) {
        Dimension size = new Dimension(1920, 1080);
        Point position = new Point(0, 0);
        if (testEnv.equals("local")) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    Map<String, Object> chromePreferences = new HashMap<>();
                    chromePreferences.put("profile.default_content_settings.geolocation", 2);
                    chromePreferences.put("download.prompt_for_download", false);
                    chromePreferences.put("download.directory_upgrade", true);
                    chromePreferences.put("credentials_enable_service", false);
                    chromePreferences.put("password_manager_enabled", false);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.setExperimentalOption("prefs", chromePreferences);
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                    if (isHeadless) {
                        chromeOptions.setHeadless(true);
                        chromeOptions.addArguments("--window-size=" + size.getWidth() + "," + size.getWidth());
                        chromeOptions.addArguments("--disable-gpu");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        FirefoxBinary firefoxBinary = new FirefoxBinary();
                        firefoxBinary.addCommandLineOptions("--headless");
                        firefoxOptions.setBinary(firefoxBinary);
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "safari":
                    driver = new SafariDriver();
                    driver.manage().window().setPosition(position);
                    driver.manage().window().setSize(size);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    throw new RuntimeException("Driver is not implemented for: " + browser);
            }
        } else if (testEnv.equals("grid")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.ANY);
            try {
                URL hubUrl = new URL("http://localhost:4444/wd/hub");
                driver = new RemoteWebDriver(hubUrl, capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("Unsupported test environment: " + testEnv);
        }
    }
}