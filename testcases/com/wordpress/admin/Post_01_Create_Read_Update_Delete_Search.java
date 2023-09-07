package com.wordpress.admin;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRegisterPageObject;
import pageObjects.user.UserRewardPointPageObject;
import pageObjects.wordpress.AdminDashboardPageObject;
import pageObjects.wordpress.AdminLoginPageObject;
import pageObjects.wordpress.AdminPostAddNewPageObject;
import pageObjects.wordpress.AdminPostSearchPageObject;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPageObject;
import pageObjects.wordpress.UserSearchPostPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	//Declare
	private WebDriver driver;
	AdminLoginPageObject adminLoginPage; 
	AdminDashboardPageObject adminDashboardPage;
	AdminPostSearchPageObject adminPostSearchPage;
	AdminPostAddNewPageObject adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPageObject userPostDetailPage;
	UserSearchPostPO userSearchPostPage;
	String adminUsername = "automationfc";
	String adminPassword = "automationfc@6768979906";
	String searchPostUrl;
	int randomNumber = generateFakeNumber();
	String postTitle  = "Live Coding Title " + randomNumber;
	String postBodyValue = "Live Coding Body " + randomNumber;
	String editPostTitle = "Edit Title" + randomNumber;
	String editPostBody = "Edit Body"+ randomNumber;
	String authorName = "automationfc";
	String adminUrl, endUserUrl;
	String currentDay = getCurrentDay();
	
	@Parameters({"browser","urlAdmin", "urlUser"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Pre-Condition - Step 01: Open browser and Admin site");
		this.adminUrl= adminUrl;
		this.endUserUrl= endUserUrl;
		driver =getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
	
		log.info("Pre-Condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Enter to Password textbox: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Post' menu link");
		adminPostSearchPage =adminDashboardPage.clickToPostMenuLink();
		
		log.info("Create_Post - Step 02: Get 'Search Posts' page Url'");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);
		
		log.info("Create_Post - Step 03: Click to 'Add New' button");
		adminPostAddNewPage =adminPostSearchPage.clickToAddNewButton();
		
		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);
		
		log.info("Create_Post - Step 05: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBodyValue);
		
		log.info("Create_Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Create_Post - Step 07: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSeachPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Search_Post - Step 04: Verify Search table contains '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTabelDisplayed("title",postTitle));
		
		log.info("Search_Post - Step 05: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTabelDisplayed("author", authorName));
		
		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle,postBodyValue));
		verifyTrue(userHomePage.isPostInforDisplayedPostAuthor(postTitle,authorName));
		verifyTrue(userHomePage.isPostInforDisplayedPostCurrentDay(postTitle,currentDay));
		
		log.info("Search_Post - Step 08: Click to Post title");
		userPostDetailPage=userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle,postBodyValue));
		verifyTrue(userPostDetailPage.isPostInforDisplayedPostAuthor(postTitle,authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedPostCurrentDay(postTitle,currentDay));
	}
	
	
	@Test
	public void User_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashboardPage =userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 05: Click to Post title link and navigate to Edit Post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);
		
		log.info("Edit_Post - Step 07: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(editPostBody);
		
		log.info("Edit_Post - Step 08: Click to 'Update' button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Edit_Post - Step 09: Verify 'Post updated' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
		
		log.info("Edit_Post - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSeachPostPageUrl(searchPostUrl);
		
		log.info("Edit_Post - Step 11: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Edit_Post - Step 12: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Edit_Post - Step 13: Verify Search table contains '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTabelDisplayed("title",editPostTitle));
		
		log.info("Edit_Post - Step 14: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTabelDisplayed("author", authorName));
		
		log.info("Edit_Post - Step 15: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Edit_Post - Step 16: Verify Post infor displayed at Home Page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitle,editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedPostAuthor(editPostTitle,authorName));
		verifyTrue(userHomePage.isPostInforDisplayedPostCurrentDay(editPostTitle,currentDay));
		
		log.info("Edit_Post - Step 17: Click to Post title");
		userPostDetailPage=userHomePage.clickToPostTitle(editPostTitle);
		
		log.info("Edit_Post - Step 18: Verify Post infor displayed at Post detail Page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitle,editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedPostAuthor(editPostTitle,authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedPostCurrentDay(editPostTitle,currentDay));
	}
	
	@Test
	public void User_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin site");
		adminDashboardPage =userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 04: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 05: Select post detail checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
		
		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectTextItemInActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToAppyButton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash. ' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash. "));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 10: Click to 'Search Posts' button");
		adminPostSearchPage.clickToSearchPostsButton();
		
		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));
		
		log.info("Delete_Post - Step 12: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home Page");
		verifyTrue(userHomePage.isPostInforUndisplayedWithPostTitle(editPostTitle));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Delete_Post - Step 15: Click to 'Search Posts' button");
		userSearchPostPage = userHomePage.clickToSearchButton();
		
		log.info("Delete_Post - Step 16: Verify 'Nothing Found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));
	}
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
