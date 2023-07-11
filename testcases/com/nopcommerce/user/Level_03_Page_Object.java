package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object extends BasePage	{
	
	WebDriver driver;
	String emailAddress;
	
	//Declare (Khai báo)
//	BasePage basePage;
	//BasePage: Class
	//basePage: Object
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Driver có ID
		
		// Initial (Khởi tạo)
//		basePage = new BasePage();
		
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		
		waitForAllElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");
		
		waitForAllElementClickable(driver, "//button[@class='button-1 register-next-step-button']");
		clickToElement(driver, "//button[@class='button-1 register-next-step-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");	
		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		sendKeyToElement(driver, "//input[@id='Email']", "12345@&I&*^");
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		waitForAllElementClickable(driver, "//button[@id='register-button']");	
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		
		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
		clickToElement(driver, "//a[@class='ico-register']");
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		waitForAllElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

		waitForAllElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		
		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
		clickToElement(driver, "//a[@class='ico-register']");	
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		waitForAllElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		
		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
		clickToElement(driver, "//a[@class='ico-register']");	
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeyToElement(driver, "//input[@id='Password']", "12345");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		
		waitForAllElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		waitForAllElementClickable(driver, "//a[@class='ico-register']");	
		clickToElement(driver, "//a[@class='ico-register']");	
		
		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "FC");
		sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		
		waitForAllElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}

}
