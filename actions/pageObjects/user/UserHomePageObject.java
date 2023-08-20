package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.user.HomePageUI;
import pageUIs.user.RegisterPageUI;

public class UserHomePageObject extends BasePage {
	//Biến global
	private WebDriver driver;
	
   public UserHomePageObject(WebDriver driver) {
		//Biến local
		this.driver = driver;
	}
	
   @Step("Navigate to Register page")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForAllElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		//2
//		return new RegisterPageObject(driver);
		
        //3
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
   
   @Step("Navigate to Login page")
	public UserLoginPageObject openLoginPage() {
		waitForAllElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
		
	}

   @Step("Verify 'My Account' link is displayed")
	public boolean isMyAccountLinkDisplay() {
		waitForAllElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);

		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
		// TODO Auto-generated method stub
		
	}

   @Step("Navigate to 'My Account' page")
	public UserCustomerInforPageObject openMyAccountPage() {
		waitForAllElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}

	

}
