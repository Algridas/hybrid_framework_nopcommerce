package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import pageObjects.user.UserHomePageObject;

public class PageGeneratorManager {

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
	
	public static AdminPostSearchPageObject getAdminPostSearchPage(WebDriver driver) {
		return new AdminPostSearchPageObject(driver);
	}
	
	public static AdminPostAddNewPageObject getAdminPostAddNewPage(WebDriver driver) {
		return new AdminPostAddNewPageObject(driver);
	}
}
