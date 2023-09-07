package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.Color;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.DismissAlert;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.UserAddressPageObject;
import pageObjects.user.UserCustomerInforPageObject;
import pageObjects.user.UserHomePageObject;

import pageObjects.user.UserMyProductReviewPageObject;
import pageObjects.user.UserRewardPointPageObject;
import pageObjects.wordpress.AdminDashboardPageObject;
import pageObjects.wordpress.UserHomePO;
import pageUIs.jQuery.upload.BasePageJQueryUI;
import pageUIs.jQuery.upload.HomePageUI;
import pageUIs.user.BasePageNopCommerceUI;
import pageUIs.user.CustomerInforPageUI;

//Common class
public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	//Nhiệm vụ mở 1 Url bất kì ra
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	//openPageUrl("https://demo.nopcommerce.com/");
	//openPageUrl("https://demo.nopcommerce.com/wishlist");
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {

			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		Alert alert =waitForAlertPresence(driver);
		alert.accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	//Dùng được cho duy nhất 2 ID (Window/Tab)
	public void switchToWindowByID(WebDriver driver,String otherID) {
		//Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
//				sleepInSecond(2);
			}
		}
	}
	
	//Dùng được cho  2 ID trở lên (Window/Tab)
	public void switchToWindowByPageTitle(WebDriver driver,String expectedPageTitle) {
		//Lấy hết tất cả các ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			//Switch từng ID trước
			driver.switchTo().window(id);
			
			//lấy ra title của page này
			String actualPageTitle = driver.getTitle();
			
			if(actualPageTitle.equals(expectedPageTitle)) {
//				sleepInSecond(2);
				break;
			}
		}
	}
	
	
	public void closeAllWindowWithoutParent(WebDriver driver,String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Sau đó dùng vòng lặp duyệt qua và kiểm tra
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
//				sleepInSecond(2);
			}
		}
		
		driver.switchTo().window(parentID);
	}
	
	
	public By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	public String getDynamicXpath(String xpathLocator, String...dynamicValues ) {
		xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
		return xpathLocator;
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	
	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator, String...dynamicValues ) {
		getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )).click();
	}
	
	
	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void clearValueInElementByPressKey(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	
	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues ));
		
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}
	
	public String getElementText(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String...dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator ) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropDownMultiple(WebDriver driver, String xpathLocator ) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInDropdown(WebDriver driver,String parentXpath, String allItemXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		//Locator phải lấy để đại diện cho tất cả các locator
		//Và phải lấy đến thẻ chứa text
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(allItemXpath)));
		
		List<WebElement> speedDropdownItems=  driver.findElements(By.xpath(allItemXpath));
		
		//3 tìm xem có đúng cái đang cần hay ko
		for (WebElement tempItem : speedDropdownItems) {
			
			String itemText = tempItem.getText();
			System.out.println(itemText);
			
			//4 - KIểm tra cái text của item đúng với cái mình mong muốn
			if(itemText.trim().equals(expectedTextItem)) {
				//5 - Click vào item đó
//				System.out.println("Click vào item");
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", tempItem);
				tempItem.click(); 
				break;
			} 
		}
	}
	
	

	public void uploadMultipleFiles(WebDriver driver, String...fileNames) {
		//Đường dẫn của thư mục uploadFiles: Windows/ MAC/Linux
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		
		//Đường dẫn của tất cả các file
		
		//1 file: Java.png
		//nhiều file: String[] fileNames = {"cry_lol.png", "Lee.png", "One-Direction"}
		String fullFileName = "";
		for(String file : fileNames) {
			fullFileName = fullFileName + filePath +file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	

	
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )).getAttribute(attributeName);
	}
	
	
	public String getCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )).size();
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
		
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues ));
		if (!element.isSelected()) {
			element.click();
		}
		
	}
	
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
		
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues ));
		if (element.isSelected()) {
			element.click();
		}
		
	}
	
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			//Tìm thấy element:
			//Case 1: Displayed - trả về true
			//Case 2: Undisplayed - trả về false
			return getWebElement(driver, xpathLocator).isDisplayed();
			
		} catch (NoSuchElementException e) {
			//Case 3: Undisplayed - trả về false
			return false;
		}
		
	}
	
//	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
//		boolean status = true;
//		
//		if(getWebElement(driver, xpathLocator).isDisplayed()) {
//			status = false;
//		}
//		
//		return status;
//	}
//	
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator,dynamicValues )).isDisplayed();
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}
	
	//Case 2 + 3
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator ) {
		System.out.println("Start time = " + new Date().toString());
		overrideImplicitTimeout(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		
		//Nếu như mình gán = 5 apply cho tất cả các step về sau đó: findElement/ findElements
		overrideImplicitTimeout(driver, longTimeOut);
		
		if (elements.size()== 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time= " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	
	
	
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).build().perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	}
	

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}



	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}
	
	public String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return $(document.evaluate(\""+ xpathLocator +"\", document, null, XPathResult.FIRST_ORDER_NODE_TYPE, null).singleNodeValue).val()");
	}


	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return status;
	}
	
	
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpath(xpathLocator,dynamicValues ))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicXpath(xpathLocator,dynamicValues ))));
	}
	
	public void waitForElementInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	/* 
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideImplicitTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		overrideImplicitTimeout(driver, shortTimeOut);
	}
	
	public void waitForAllElementInVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}
	
	public void waitForAllElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	public void waitForAllElementClickable(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpath(xpathLocator,dynamicValues ))));
	}
	
	
	//Tối ưu ở bài học Level_07_Switch_Page
	public UserCustomerInforPageObject openCustomerInforPage(WebDriver driver) {
		waitForAllElementVisible(driver, BasePageNopCommerceUI.CUSTOMER_INFOR_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMER_INFOR_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	
	public UserAddressPageObject openAddresspage(WebDriver driver) {
		waitForAllElementVisible(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		
		waitForAllElementVisible(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForAllElementVisible(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	//Tối ưu ở bài học Level_09_Dynamoc_Locator
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForAllElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
		
		}
	
	//Pattern Object
	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForAllElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		}
	
	/** 
	 * Enter to dynamic Textbox by ID
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	
	/** 
	 * Click to dynamic Button by text
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	
	/** 
	 * Select item in dropdown by Name attribute
	 * 
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void selectToDropwdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForAllElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
		
	}
	
	/** 
	 * Click to dynamic radio button by label name
	 * @param driver
	 * @param radioButtonLabelName
	 */
	
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForAllElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		
	}
	

	/** 
	 * Click to dynamic radio button by label name
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForAllElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		
	}
	

	/**
	 * Get value in textbox by textboxID
	 * 
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
	
	//Level_8_switch_role
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForAllElementVisible(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForAllElementVisible(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public UserHomePO openEndUserSite(WebDriver driver, String endUserUrl) {
		openPageUrl(driver, endUserUrl);
		return pageObjects.wordpress.PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminDashboardPageObject openAdminSite(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return pageObjects.wordpress.PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
