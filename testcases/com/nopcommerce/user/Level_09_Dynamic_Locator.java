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

public class Level_09_Dynamic_Locator extends BaseTest{
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
	public void User_01_Register_Login() {
		registerPage =homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
	
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage =registerPage.clickToTheLogo();
		loginPage =homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);

		homePage =loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());

        customerInforPage=homePage.openMyAccountPage();
        Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}
	
	@Test
	public void User_02_Switch_Page() {
		//Customer Infor -> Address
		addressPage = customerInforPage.openAddresspage(driver);
		//Address -> My Product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		//My Product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddresspage(driver); 
		//Address -> Reward Point
		rewardPointPage=addressPage.openRewardPointPage(driver);
		//Reward Point -> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		
	}
	
	@Test
	public void User_03_Dynamic_Page_01() {

		//My Product Review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");
		//Reward Point -> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "Addresses");
		//Address -> Reward Point
		rewardPointPage=(UserRewardPointPageObject) addressPage.openPageAtMyAccountByName(driver, "Reward points");
		//Reward Point -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "My product reviews");
		//My product Review -> Customer info
		customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
	}
	
	
	@Test
	public void User_03_Dynamic_Page_02() {
		//Customer info -> My Product review
		customerInforPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage=PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		
		//My Product Review -> Reward Point
		 myProductReviewPage.openPageAtMyAccountByPageName(driver, "Reward points");
		 rewardPointPage=PageGeneratorManager.getUserRewardPointPage(driver);
		 
		//Reward Point -> Address
		rewardPointPage.openPageAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		
		//Address -> Reward Point
		addressPage.openPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage=PageGeneratorManager.getUserRewardPointPage(driver);
		
		//Reward Point -> My Product Review
		rewardPointPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		//My product Review -> Customer info
		myProductReviewPage.openPageAtMyAccountByPageName(driver, "Customer info");
		customerInforPage = PageGeneratorManager.getUserCustomerInforPage(driver);
		
	}
	
	
	@Test
	public void User_04_Switch_Role() {
		//Role User -> Role Admin
		//Role Admin -> Role User
	}


	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
