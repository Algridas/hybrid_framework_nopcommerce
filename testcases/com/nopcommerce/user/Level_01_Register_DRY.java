package com.nopcommerce.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_DRY {
	
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "Password is required.");
		
	}
	
	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("12345@&I&*^");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Wrong email");
	}
	
	@Test
	public void TC_03_Register_Success() {
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'message-error')]//li")).getText(), "The specified email already exists");
		
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("12345");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("12345");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(), "The password and confirmation password do not match.");
		
	}
	

	@AfterClass
	public void afterClass() {
	}
	
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}

}
