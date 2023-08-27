package pageUIs.wordpress;

public class AdminPostAddNewPageUI {
	public static final String TITLE_TEXTBOX = "//h1 [contains(@class,'wp-block-post-title')]";
	public static final String BODY_TEXTBOX = "//textarea [contains(@class,'editor-post-text-editor')]";
	public static final String PUBLISH_BUTTON = "//button [contains(@class,'publish-button')]";
	public static final String PUBLISHED_MESSAGE = "//div[@class='components-snackbar__content' and text()='%s']";
}
