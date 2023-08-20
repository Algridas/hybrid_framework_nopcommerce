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

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_17_Custom_Close_Driver extends BaseTest{
	//Declare
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	
	//Declare+init
//	private String projectPath = System.getProperty("user.dir");
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
		
		//Pre-Condition
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		validPassword = "123456";
		
		log.info("Pre-Condition - Step 01: Navigate to 'Register' page");
		registerPage =homePage.clickToRegisterLink();
		
		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTextbox(validPassword);
		
		log.info("Pre-Condition - Step 06: Enter to Confirm Password with value is '" + validPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		driver = null;
		log.info("Pre-Condition - Step 07: Click to 'Register' button ");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Condition - Step 08: Verify Register success message is displayed");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.......");
		
		log.info("Pre-Condition - Step 09: Click to the 'Logo'");
		homePage =registerPage.clickToTheLogo();
		
		log.info("Pre-Condition - Step 10: Navigate to Login page");
		loginPage =homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTextbox(validPassword);

		log.info("Pre-Condition - Step 13: Click to 'Login' button ");
		homePage =loginPage.clickToLoginButton();
	}

	@Test
	public void Search_01_Name() {}
	
	@Test
	public void Search_02_Address() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
	


}
