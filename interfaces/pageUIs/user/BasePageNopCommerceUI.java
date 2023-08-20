package pageUIs.user;

public class BasePageNopCommerceUI {
	public static final String CUSTOMER_INFOR_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
	public static final String ADDRESS_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW_LINK = "//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
	public static final String REWARD_POINTS_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	
	public static final String LOGOUT_LINK_AT_USER = "//a[@class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "//a[text()='Logout']";
	
	//Pattern Object
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "//div[contains(@class,'account-navigation')]//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT= "//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME= "//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL= "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL= "//label[contains(text(),'%s')]/following-sibling::input";
}
