package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserRewardPointPageObject;
import reportConfig.ExtentTestManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_15_Allure extends BaseTest{
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

	@Description("Register to system")
	@Severity(SeverityLevel.NORMAL) //optional
	@Test
	public void User_01_Register() {
		
		registerPage =homePage.clickToRegisterLink();
		
		registerPage.inputToFirstnameTextbox(firstName);
		
		registerPage.inputToLastnameTextbox(lastName);
		
		registerPage.inputToEmailTextbox(emailAddress);
		
		registerPage.inputToPasswordTextbox(validPassword);
		
		registerPage.inputToConfirmPasswordTextbox(validPassword);
	
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
//		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09: Click to the 'Logo'");
//		homePage =registerPage.clickToTheLogo();

	}
	
	@Description("Login to system")
	@Severity(SeverityLevel.NORMAL) 
	@Test
	public void User_02_Login() {
		homePage =registerPage.clickToTheLogo();
		loginPage =homePage.openLoginPage();
	
		loginPage.inputToEmailTextbox(emailAddress);
		
		loginPage.inputToPasswordTextbox(validPassword);

		homePage =loginPage.clickToLoginButton();
		
		Assert.assertFalse(homePage.isMyAccountLinkDisplay());

        customerInforPage=homePage.openMyAccountPage();
        
        Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
