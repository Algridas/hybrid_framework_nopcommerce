package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
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

public class Level_08_Switch_Role extends BaseTest{
	//Declare
	private WebDriver driver;
	private String firstName, lastName, userEmailAddress, userPassword, adminEmailAddress, adminPassword;
	
	//Declare+init
	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject userHomePage ;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private UserCustomerInforPageObject userCustomerInfoPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver =getBrowserDriver(browserName);
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
//		homePage = new HomePageObject(driver);
		
		userEmailAddress = "lee123@yahoo.com";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
	    adminPassword = "admin";
		//click Logo thì business sẽ ra trang Home
//		 new HomePageObject(driver);
	}

	@Test
	public void Role_01_User_To_Admin() {
        userLoginPage =userHomePage.openLoginPage();
        
        //Login as User role
        userHomePage =userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
		
		//Home Page -> Customer info
		userCustomerInfoPage = userHomePage.openMyAccountPage();
		
		//Customer Info -> click Logout -> Home Page
		userHomePage=userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		
		//User Home Page -> Open Admin page -> Login Page (Admin)
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
//		adminLoginPage.inputToUsernameTextbox(emailAddress);
//		adminLoginPage.inputToPasswordTextbox(password);
//		adminLoginPage.clickToLoginButton();
		
		//Login as admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		
		//Dashboard Page -> Click Logout -> Login Page(Admin)
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
	}
	
	
	@Test
	public void Role_02_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
        //Home Page -> Login Page
		userLoginPage =userHomePage.openLoginPage();
		
	      //Login as User role
        userHomePage =userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
	}
	
	


	@AfterClass
	public void afterClass() {
//		driver.close();
	}
	


}
