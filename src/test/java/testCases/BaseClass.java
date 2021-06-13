package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void startBrowser(String browser){

        System.out.println("browser value is: " + browser);

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options= new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--disable-notifications",
        "--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
            
//             options.setHeadless(true);
            driver = new ChromeDriver(options);

        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        }else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver =new EdgeDriver();
        }
        else {
            System.out.println("Please pass the correct browser value: " + browser);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
