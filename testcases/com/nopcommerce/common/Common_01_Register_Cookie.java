package com.nopcommerce.common;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Common_01_Register_Cookie extends BaseTest{
	//Declare
	private WebDriver driver;
	private String firstName, lastName;
	private String emailAddress, Password;
	public static Set<Cookie> loggedCookies;
	
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
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) {
		
		driver =getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		Password = "123456";
		
		
		log.info("Pre-condition - Step 01: Navigate to 'Register' page");
		registerPage =homePage.clickToRegisterLink();
		
		log.info("Pre-condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-condition - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-condition - Step 05: Enter to Password textbox with value is '" + Password + "'");
		registerPage.inputToPasswordTextbox(Password);
		
		log.info("Pre-condition - Step 06: Enter to Confirm Password with value is '" + Password + "'");
		registerPage.inputToConfirmPasswordTextbox(Password);
	
		log.info("Pre-condition - Step 07: Click to 'Register' button ");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-condition - Step 08: Verify Register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Pre-condition - Step 09: Click to the 'Logo'");
		homePage =registerPage.clickToTheLogo();
		
		log.info("Pre-Condition - Step 10: Navigate to Login page");
		loginPage =homePage.openLoginPage();
		
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Password textbox with value is '" + Password + "'");
		loginPage.inputToPasswordTextbox(Password);

		log.info("Pre-Condition - Step 13: Click to 'Login' button ");
		homePage =loginPage.clickToLoginButton();
		
		loggedCookies = homePage.getAllCookies(driver);


	}
	

	@AfterTest
	public void afterClass() {
//		driver.close();
	}
	


}
