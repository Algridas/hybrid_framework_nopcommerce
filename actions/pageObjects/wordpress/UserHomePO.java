package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
	WebDriver driver;

	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}


	
	public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInforDisplayedWithPostBody(String postTitle,String postBodyValue) {
		waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE,postTitle, postBodyValue);
		return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE,postTitle, postBodyValue);
	}

	public boolean isPostInforDisplayedPostAuthor(String postTitle,String authorName) {
		waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE,postTitle, authorName);
		return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE,postTitle, authorName);
	}

	public boolean isPostInforDisplayedPostCurrentDay(String postTitle,String currentDay) {
		waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle, currentDay);
		return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle, currentDay);
	}

    public UserPostDetailPageObject clickToPostTitle(String postTitle) {
    	waitForAllElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
    	clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}



	public boolean isPostInforUndisplayedWithPostTitle(String editPostTitle) {
		// TODO Auto-generated method stub
		return false;
	}



	public void enterToSearchTextbox(String editPostTitle) {
		// TODO Auto-generated method stub
		
	}



	public UserSearchPostPO clickToSearchButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
