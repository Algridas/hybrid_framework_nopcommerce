package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_02_Login{
	//Declare
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	
	//Declare+init
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage ;
	private RegisterPageObject registerPage;

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
//		registerPage = new RegisterPageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "ruslanchagaev123@yahoo.com";
		
		
		
		System.out.println("Precondition - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		System.out.println("Precondition - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		System.out.println("Precondition - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		System.out.println("Precondition - Step 04:Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		
//		waitForAllElementClickable(driver, "//a[@class='ico-login']");
//		clickToElement(driver, "//a[@class='ico-login']");
		System.out.println("Precondition - Step 05:Click to Login link");
		registerPage.clickToLoginLink();
		
		
	}

	@Test
	public void Login_01() {
	}
	


	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}

}
