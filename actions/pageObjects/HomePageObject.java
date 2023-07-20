package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

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
	public void clickToLoginLink() {
		waitForAllElementVisible(driver, RegisterPageUI.LOGIN_LINK);
		clickToElement(driver, RegisterPageUI.LOGIN_LINK);
		
	}

	public boolean isMyAccountLinkDisplay() {
		waitForAllElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);

		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
		// TODO Auto-generated method stub
		
	}

	

}
