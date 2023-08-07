package com.jquery;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.jQuery.dataTable.HomePageObject;
import pageObject.jQuery.dataTable.PageGeneratorManager;
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

public class Level_10_DataTable_DataGrid extends BaseTest{
	//Declare
	private WebDriver driver;
	HomePageObject homePage;

	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver =getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}

//	@Test
	public void Table_01_Paging() {
		
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		
		homePage.openPagingByPageNumber("18");
		homePage.sleepInSecond(3);
		Assert.assertTrue(homePage.isPageNumberActived("18"));
	}
	
//	@Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Algeria");
		homePage.enterToHeaderTextboxByLabel("Females", "283821");
		homePage.enterToHeaderTextboxByLabel("Males", "295140");
		homePage.enterToHeaderTextboxByLabel("Total", "578961");
		homePage.sleepInSecond(3);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Albania");
		homePage.enterToHeaderTextboxByLabel("Females", "24128");
		homePage.enterToHeaderTextboxByLabel("Males", "25266");
		homePage.enterToHeaderTextboxByLabel("Total", "49397");
		homePage.sleepInSecond(3);
		
	}
	
//	@Test
	public void Table_03_Enter_To_Header() {
		//Đọc dữ liệu của file country.txt ra
		//Lưu vào 1 List<String> = Expected Value = expectedAllCountryValues
		
		
		//Actual Value
		actualAllCountryValues=	homePage.getValueEachRowAtAllPage();
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
		
	}
	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		//Value de nhap lieu - tham so 1
		//Row number: tai row nào
		//ex: nhập vào textbox tại dòng số 3/5/2
		//Column name: Company/ Contact Person/ Order Placed
		homePage.clickToLoadButton();
		homePage.sleepInSecond(3);
		homePage.enterToTextboxByColumnNameAtRowNumber("Company", "1", "Innocak");
		homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "2", "Linh dog");
		homePage.enterToTextboxByColumnNameAtRowNumber("Order Placed", "3", "69");
		homePage.enterToTextboxByColumnNameAtRowNumber("Member Since", "3", "02211997");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Hong Kong");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Malaysia");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "United Kingdom");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "2", "Malaysia");
		homePage.sleepInSecond(3);
		homePage.selectDropdownByColumnNameAtRowNumber("Country", "3", "Hong Kong");
		homePage.sleepInSecond(3);
		
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "1");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "4");
		homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "5");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "2");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "3");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "6");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "7");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "8");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByRowNumber("1","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1","Insert Row Above");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("3","Move Up");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("8","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("7","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("6","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("5","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("4","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("3","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("2","Remove Current Row");
		homePage.sleepInSecond(2);
		homePage.clickToIconByRowNumber("1","Remove Current Row");
		homePage.sleepInSecond(2);
	}
	


	@AfterClass
	public void afterClass() {
		driver.close();
	}
	


}
