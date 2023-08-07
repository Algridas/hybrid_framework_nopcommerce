package com.facebook.register;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.jQuery.uploadFile.HomePageObject;

import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Element_Undisplayed extends BaseTest{
	//Declare
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver =getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
	}


	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
		
	}
	
	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		//Nếu như mình mong đợi 1 cái hàm vừa verify displayed/ undisplayed thì ko dc kết hợp wait
		//isElementDisplayed
		
		//Verify True - mong đợi Confirm Email displayed (true)
		loginPage.enterToEmailAddressTextbox("Lee123@gmail.com");
		loginPage.sleepInSecond(2);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		//Verify False - mong đợi Confirm Email Undisplayed (false)
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(2);
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
	}

	@Test
	public void TC_03_Verify_Element_Displayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		//Verify False - mong đợi Confirm Email Undispalyed (false)
		// isDisplayed: bản chất ko kiểm tra 1 element ko có trong DOM được
		loginPage.sleepInSecond(2);
		//Khi close cái form Register đi thì Confirm Email ko còn trong DOM nữa
		//Nên hàm findElement sẽ bị fail vì ko tìm thấy element
		//1 - Nó sẽ chờ hết timeout của implicit: 30s
		//2 - Nó sẽ đánh fail testcase tại đúng step này luôn
		//3 - Ko có chạy các step còn lại nữa
		
//		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
