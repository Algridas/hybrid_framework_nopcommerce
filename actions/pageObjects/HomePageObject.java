package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	//Biến global
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		//Biến local
		this.driver = driver;
	}
	
	public void clickToRegisterLink() {
		waitForAllElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

}
