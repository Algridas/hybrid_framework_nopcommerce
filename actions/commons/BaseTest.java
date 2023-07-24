package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
System.out.println("Run on"+ browserName);
		
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");

//			driver = WebDriverManager.firefoxdriver().create();
			
			driver = new FirefoxDriver();
		}else if(browserName.equals("h_firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			//Browser option: selenium 3.xx
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			
		}else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");

//			driver = WebDriverManager.chromedriver().create();
			
			driver = new ChromeDriver();
		}else if(browserName.equals("h_chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath +  "\\browserDrivers\\msedgedriver.exe");
			
//			driver = WebDriverManager.edgedriver().create();
			
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		return driver; 
	}
	
	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}

}
