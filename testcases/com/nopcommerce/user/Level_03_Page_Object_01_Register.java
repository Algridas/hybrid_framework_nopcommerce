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

public class Level_03_Page_Object_01_Register{
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
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		//Step này đại điện cho clickToRegisterLink();
//		waitForAllElementClickable(driver, "//a[@class='ico-register']");
//		clickToElement(driver, "//a[@class='ico-register']");       
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
//		waitForAllElementClickable(driver, "//button[@class='button-1 register-next-step-button']");
//		clickToElement(driver, "//button[@class='button-1 register-next-step-button']");
		System.out.println("Register Page - Step 02:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
//		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
//		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
//		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
//		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		System.out.println("Register Page - Step 03:Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
		
		

		

		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
//		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
//		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");	
//		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
//		sendKeyToElement(driver, "//input[@id='Email']", "12345@&I&*^");
//		sendKeyToElement(driver, "//input[@id='Password']", "123456");
//		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		System.out.println("Register Page - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("12345@&I&*^");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		
//		waitForAllElementClickable(driver, "//button[@id='register-button']");	
//		clickToElement(driver, "//button[@id='register-button']");
		System.out.println("Register Page - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
		System.out.println("Register Page - Step 04:Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}
	
	@Test
	public void TC_03_Register_Success() {
		
//		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
		
		
//		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
//		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
//		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
//		sendKeyToElement(driver, "//input[@id='Password']", "123456");
//		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		System.out.println("Register Page - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		
//		waitForAllElementClickable(driver, "//button[@id='register-button']");
//		clickToElement(driver, "//button[@id='register-button']");
		System.out.println("Register Page - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		System.out.println("Register Page - Step 04:Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		
//		waitForAllElementClickable(driver, "//a[@class='ico-login']");
//		clickToElement(driver, "//a[@class='ico-login']");
		System.out.println("Register Page - Step 05:Click to Login link");
		registerPage.clickToLoginLink();
		
		
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		
//		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
//		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
//		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
//		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
//		sendKeyToElement(driver, "//input[@id='Password']", "123456");
//		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		System.out.println("Register Page - Step 02:Input to required fields");
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		
		
//		waitForAllElementClickable(driver, "//button[@id='register-button']");
//		clickToElement(driver, "//button[@id='register-button']");
		System.out.println("Register Page - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
		System.out.println("Register Page - Step 04:Verify the specified email already exists");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
		
		
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register Page - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");
		
		System.out.println("Register Page - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
		System.out.println("Register Page - Step 04:Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		System.out.println("Home Page - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register Page - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("12345");
		

		System.out.println("Register Page - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		

		System.out.println("Register Page - Step 04:Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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
