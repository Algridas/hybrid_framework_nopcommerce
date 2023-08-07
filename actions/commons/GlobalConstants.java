package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/ ";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/ ";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	//Window/ MAC/ Linux
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "upload_file" + File.separator;
	//Trỏ về 1 thư mục mặc định của User
	//Window: Downloads
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML = PROJECT_PATH + File.separator + "dragDropHTML5";
	
	//Database Account/ User/ Pass/ Port
	public static final String DB_DEV_URL = "32.18.252.185:9860"; //ví dụ
	public static final String DB_DEV_USER = "Gagarin";
	public static final String DB_DEV_PASS = "p@$$w0rD123456";
	
	public static final String DB_TEST_URL = "32.18.252.185:9860"; //ví dụ
	public static final String DB_TEST_USER = "Gagarin";
	public static final String DB_TEST_PASS = "p@$$w0rD123456";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;
	
	
	
}
