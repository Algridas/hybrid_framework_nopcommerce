package pageUIs.wordpress;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "//article//h1[text()='%s']";
	public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "//article//h1[text()='%s']/parent::header//time[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "//article//h1[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "//article//h1[text()='%s']/parent::header//span[@class='author vcard']/a[text()='%s']";
}
