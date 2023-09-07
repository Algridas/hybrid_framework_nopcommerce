package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPageObject extends BasePage{
	WebDriver driver;

	public AdminPostAddNewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitleValue) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitleValue);
	}

	public void enterToAddNewPostBody(String postBodyValue) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBodyValue);
		
	}

	public void clickToPublishOrUpdateButton() {
		waitForAllElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
	}

	public boolean isPostPublishMessageDisplayed(String postPublishedMessage) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postPublishedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postPublishedMessage);
	}

	public AdminPostSearchPageObject openSeachPostPageUrl(String searchPostUrl) {
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManager.getAdminPostSearchPage(driver);
	}
}
