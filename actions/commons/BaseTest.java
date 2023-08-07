package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
System.out.println("Run on"+ browserName);
		
		if(browserName.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");

			driver = WebDriverManager.firefoxdriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new FirefoxDriver();
		}else if(browserName.equals("h_firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			//Browser option: selenium 3.xx
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			
		}else if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");

			driver = WebDriverManager.chromedriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new ChromeDriver();
		}else if(browserName.equals("h_chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("edge")) {
//			System.setProperty("webdriver.edge.driver", projectPath +  "\\browserDrivers\\msedgedriver.exe");
			
			driver = WebDriverManager.edgedriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		
		return driver; 
	}
	
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
System.out.println("Run on"+ browserName);
		
		if(browserName.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");

			driver = WebDriverManager.firefoxdriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new FirefoxDriver();
		}else if(browserName.equals("h_firefox")) {
//			WebDriverManager.firefoxdriver().setup();
			//Browser option: selenium 3.xx
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			
		}else if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");

			driver = WebDriverManager.chromedriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new ChromeDriver();
		}else if(browserName.equals("h_chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath +  "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("edge")) {
//			System.setProperty("webdriver.edge.driver", projectPath +  "\\browserDrivers\\msedgedriver.exe");
			
			driver = WebDriverManager.edgedriver().proxy("http://127.0.0.1:3128").create();
			
//			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid.");
		}
		
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
		
		return driver; 
	}
	


	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}



	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
            pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


}
