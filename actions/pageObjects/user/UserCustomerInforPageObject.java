package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.CustomerInforPageUI;
import pageUIs.user.HomePageUI;

public class UserCustomerInforPageObject extends BasePage {

	private WebDriver driver;
	
	   public UserCustomerInforPageObject(WebDriver driver) {
			//Biến local
			this.driver = driver;
		}


	public boolean isCustomerInforPageDisplayed() {
		waitForElementVisible(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
		return isElementDisplayed(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}




}
