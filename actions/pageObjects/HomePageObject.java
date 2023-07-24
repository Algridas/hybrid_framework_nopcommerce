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
	
	public RegisterPageObject clickToRegisterLink() {
		waitForAllElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		//2
//		return new RegisterPageObject(driver);
		
        //3
		return PageGeneratorManager.getRegisterPage(driver);
	}
	public LoginPageObject clickToLoginLink() {
		waitForAllElementVisible(driver, RegisterPageUI.LOGIN_LINK);
		clickToElement(driver, RegisterPageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
		
	}

	public boolean isMyAccountLinkDisplay() {
		waitForAllElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);

		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
		// TODO Auto-generated method stub
		
	}

	public MyAccountPageObject clickToMyAccountLink() {
		waitForAllElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	

}
