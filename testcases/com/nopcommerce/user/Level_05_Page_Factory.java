package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_05_Page_Factory extends BaseTest{
	//Declare
	private WebDriver driver;
	private String firstName, lastName, existingEmail, invalidEmail, notFoundEmail, validPassword, incorrectPassword;
	
	//Declare+init
	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver =getBrowserDriver(browserName);
		homePage = new UserHomePageObject(driver);
		
		
		firstName = "Automation";
		lastName = "FC";
		invalidEmail= "machete@machete@hiho";
		notFoundEmail= "afc" + generateFakeNumber() + "@gmail.com";
		existingEmail = "afc" + generateFakeNumber() + "@gmail.vn";
		validPassword = "123456";
		incorrectPassword ="654321";
		
		
		System.out.println("Precondition - Step 01:Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Precondition - Step 02:Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		System.out.println("Precondition - Step 03:Click to Register button");
		registerPage.clickToRegisterButton();
		
//		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		System.out.println("Precondition - Step 04:Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		
//		waitForAllElementClickable(driver, "//a[@class='ico-login']");
//		clickToElement(driver, "//a[@class='ico-login']");
		System.out.println("Precondition - Step 05:Click to the logo");
		registerPage.clickToTheLogo();
		
		//click Logo thì business sẽ ra trang Home
		homePage = new UserHomePageObject(driver);
	}

//	@Test
	public void Login_01_Empty_data() {
		System.out.println("Home Page - Step 01:Click to Login link");
		homePage.openLoginPage();
		
		//Từ trang Home, mình click qua trang Login
		
		loginPage = new UserLoginPageObject(driver);
		
		System.out.println("Login Page - Step 02:Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 03:Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

		
	}
	
	
	@Test
	public void Login_02_Invalid_data() {
		System.out.println("Home Page - Step 01:Click to Login link");
		homePage.openLoginPage();
		
		//Từ trang Home, mình click qua trang Login
		
		loginPage = new UserLoginPageObject(driver);
		
		System.out.println("Login Page - Step 02:Click to Login button");
		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		System.out.println("Login Page - Step 03:Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	
	
	@Test
	public void Login_03_Email_Not_found() {
		System.out.println("Home Page - Step 01:Click to Login link");
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		System.out.println("Login Page - Step 02:Click to Login button");
		loginPage.inputToEmailTextbox(notFoundEmail);
		loginPage.clickToLoginButton();
		System.out.println("Login Page - Step 03:Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(incorrectPassword);

		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}
	
	@Test
	public void Login_06_Valid_Email_Password() {
		
		homePage.openLoginPage();
		
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);

		loginPage.clickToLoginButton();
		
		//Login thành công -> HomePage
		homePage = new UserHomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
		
		
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
