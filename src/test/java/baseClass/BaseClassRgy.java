package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassRgy {
    
    public static WebDriver driver;
    protected static Properties prop;
    
    public static void loadConfig() {
    	
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
 
    public static String getProperty(String key) {
        if (prop == null) {
            loadConfig();
        }
        return prop.getProperty(key);
    }
    
    
    @BeforeClass
    public void setUp() {
    	
        loadConfig();
        
        String browser = prop.getProperty("browser", "chrome").toLowerCase();
        
        switch (browser) {
            case "chrome":
            	
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                break;
                
            case "firefox":
            	
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
                
            case "edge":
            	
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
                
            default:
            	
                System.out.println("Browser not supported. Using Chrome as default.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
        }
        
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait", "10"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        
        int pageLoadTimeout = Integer.parseInt(prop.getProperty("pageLoadTimeout", "30"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        
        String url = prop.getProperty("url");
        driver.get(url);
        
        System.out.println("Browser launched and navigated to: " + url);
    }
    
 
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
    
   
    public WebDriver getDriver() {
        return driver;
    }
}
