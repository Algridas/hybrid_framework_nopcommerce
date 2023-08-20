package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserRewardPointPageObject;

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

public class Level_18_Pattern_Object extends BaseTest{
	//Declare
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	
	//Declare+init
	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage ;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	private String date, month, year;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver =getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
//		homePage = new HomePageObject(driver);
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		validPassword = "123456";
		date = "21";
		month = "February";
		year = "1997";
		
		
		
		//click Logo thì business sẽ ra trang Home
//		 new HomePageObject(driver);
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage =homePage.clickToRegisterLink();
		
		registerPage.clickToRadioButtonByLabel(driver, "Female");
		
		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
//		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
//		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		
		registerPage.selectToDropwdownByName(driver, "DateOfBirthDay", date);
		registerPage.selectToDropwdownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropwdownByName(driver, "DateOfBirthYear", year);
		
		
		log.info("Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
//		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		registerPage.clickToCheckboxByLabel(driver, "Newsletter");
		
		log.info("Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
//		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToTextboxByID(driver, "Password", validPassword);
		
		
		log.info("Register - Step 06: Enter to Confirm Password with value is '" + validPassword + "'");
//		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", validPassword);
		
		registerPage.sleepInSecond(10);
	
		log.info("Register - Step 07: Click to 'Register' button ");
//		registerPage.clickToRegisterButton();
		registerPage.clickToButtonByText(driver, "Register");
		
		
		log.info("Register - Step 08: Verify Register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Register - Step 09: Click to the 'Logo'");
		homePage =registerPage.clickToTheLogo();

	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		loginPage =homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
//		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		
		log.info("Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
//		loginPage.inputToPasswordTextbox(validPassword);
		loginPage.inputToTextboxByID(driver, "Password", validPassword);
		
		log.info("Login - Step 04: Click to 'Login' button ");
//		homePage =loginPage.clickToLoginButton();
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());

		
	}
	
	@Test
	public void User_03_My_Account() {
		log.info("MyAccount - Step 01: Navigate to 'My Account' page");
        customerInforPage=homePage.openMyAccountPage();
        
        log.info("MyAccount - Step 02: Verify 'Customer Infor' page is displayed");
        Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
        log.info("MyAccount - Step 03: Verify 'First Name' value is correctly");
        Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), firstName);

        log.info("MyAccount - Step 04: Verify 'Last Name' value is correctly");
        Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "LastName"), lastName);
        
        log.info("MyAccount - Step 05: Verify 'Email' value is correctly");
        Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
	


}
