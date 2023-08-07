package com.jquery;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.jQuery.uploadFile.HomePageObject;
import pageObject.jQuery.uploadFile.PageGeneratorManager;
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

public class Level_11_Upload_Files extends BaseTest{
	//Declare
	private WebDriver driver;
	private HomePageObject homePage;
	
	String cry_lolFileName = "cry_lol.png";
	String leeFileName = "Lee.jpg";
	String onedirectionFileName = "One-Direction.jpg";
	
	
	String[] multipleFileNames = {cry_lolFileName, leeFileName, onedirectionFileName};
	
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver =getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}


	@Test
	public void Upload_01_One_File_Per_Time() {
		//Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, cry_lolFileName);
		
		//Step 02 - Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(cry_lolFileName));
		
		//Step 03 - Click to Start button
		homePage.clickToStartButton();
		
		//Step 04 - Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(cry_lolFileName));
		
		//Step 05 - Verify sigle file image uploaded success
		Assert.assertTrue(homePage.isFileImageLinkUploadedByName(cry_lolFileName));
	}
	
	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.refreshCurrentPage(driver);
		//Step 01 - Load multiple file
		homePage.uploadMultipleFiles(driver, multipleFileNames);
		
		//Step 02 - Verify single file loaded success
		//cry_lolFileName, leeFileName, onedirectionFileName
		Assert.assertTrue(homePage.isFileLoadedByName(cry_lolFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(leeFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(onedirectionFileName));
		
		//Step 03 - Click to Start button
		homePage.clickToStartButton();
		
		//Step 04 - Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(cry_lolFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(leeFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(onedirectionFileName));
		
		
		//Step 05 - Verify sigle file image uploaded success
		Assert.assertTrue(homePage.isFileImageLinkUploadedByName(cry_lolFileName));
		Assert.assertTrue(homePage.isFileImageLinkUploadedByName(leeFileName));
		Assert.assertTrue(homePage.isFileImageLinkUploadedByName(onedirectionFileName));
		
	}
	


	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
