package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPageObject extends BasePage {
	WebDriver driver;

	public AdminPostSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPageObject clickToAddNewButton() {
		waitForAllElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		sendKeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickToSearchPostsButton() {
		waitForAllElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		
	}

	public boolean isPostSearchTabelDisplayed(String headerID, String cellValue) {
		int headerIndex =getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) +1 ;
		waitForElementVisible(driver,AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		return isElementDisplayed(driver,AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
	}

	public AdminPostAddNewPageObject clickToPostTitleLink(String postTitle) {
		waitForAllElementClickable(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		clickToElement(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void selectPostCheckboxByTitle(String editPostTitle) {
		// TODO Auto-generated method stub
		
	}

	public void selectTextItemInActionDropdown(String dropdownItem) {
		waitForAllElementClickable(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
		selectItemInDefaultDropdown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, dropdownItem);
	}

	public void clickToAppyButton() {
		// TODO Auto-generated method stub
		
	}

	public boolean isMoveToTrashMessageDisplayed(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isNoPostFoundMessageDisplayed(String string) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
