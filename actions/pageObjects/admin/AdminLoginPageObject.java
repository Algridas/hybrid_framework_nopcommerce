package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageUIs.admin.AdminLoginPageUI;
import pageUIs.user.LoginPageUI;

public class AdminLoginPageObject extends BasePage {

	private WebDriver driver;
	
	   public AdminLoginPageObject(WebDriver driver) {
			//Biến local
			this.driver = driver;
		}

	public void inputToUsernameTextbox(String emailAddress) {
		
		waitForAllElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitForAllElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public AdminDashboardPageObject clickToLoginButton() {
		waitForAllElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
		inputToUsernameTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}

}
