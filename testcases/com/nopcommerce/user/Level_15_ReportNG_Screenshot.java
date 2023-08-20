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

public class Level_15_ReportNG_Screenshot extends BaseTest{
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
		
		//click Logo thì business sẽ ra trang Home
//		 new HomePageObject(driver);
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage =homePage.clickToRegisterLink();
		
		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTextbox(validPassword);
		
		log.info("Register - Step 06: Enter to Confirm Password with value is '" + validPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validPassword);
	
		log.info("Register - Step 07: Click to 'Register' button ");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 08: Verify Register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.......");
		
		log.info("Register - Step 09: Click to the 'Logo'");
		homePage =registerPage.clickToTheLogo();

	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		loginPage =homePage.openLoginPage();
		
		log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTextbox(validPassword);

		log.info("Login - Step 04: Click to 'Login' button ");
		homePage =loginPage.clickToLoginButton();
		
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertFalse(homePage.isMyAccountLinkDisplay());

		log.info("Login - Step 06: Navigate to 'My Account' page");
        customerInforPage=homePage.openMyAccountPage();
        
        log.info("Login - Step 07: Verify 'Customer Infor' page is displayed");
        Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
