package pageUIs.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "//div[@class='qgrd-header-text' and text()='%s']/parent::div[@class='qgrd-header-text-wrap']/following-sibling::input[@class='qgrd-header-filter']";
	public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String TOTAL_PAGINATION = "//ul[@class='qgrd-pagination-ul']/li";
	public static final String PAGINATION_PAGE_BY_INDEX = "//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]/a";
	public static final String ALL_ROW_EACH_PAGE = "//tbody/tr";
	public static final String ALL_ROW_COUNTRY_EACH_PAGE = "//tbody/tr/td[@data-key='country']";
	
	
	//Index của cái côt mà mình cần enter/ click/ select vào
	public static final String COLUMN_INDEX_BY_NAME = "//table[@id='tblAppendGrid']//th[text()='%s']/preceding-sibling::th";
	public static final String ROW_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]/input";
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]//select";
	public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]//input[@type='checkbox']";
	public static final String ICON_NAME_BY_ROW_NUMBER = "//tbody/tr[%s]//button[@title='%s']";
	
	public static final String LOAD_DATA = "//button[@id='load']";
	
}
