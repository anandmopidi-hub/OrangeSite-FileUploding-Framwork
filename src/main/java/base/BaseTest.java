package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.GoogleSheetUtils;
import utils.SendMail;

public class BaseTest {
	 protected Playwright playwright;
	    protected Browser browser;
	    protected BrowserContext context;
	    protected Page page;

	    protected static long startTime;

	    @BeforeSuite
	    public void setupGoogleSheet() throws Exception {

	        startTime = System.currentTimeMillis();

	        GoogleSheetUtils.createHeaders();

	        System.out.println("Automation Execution Started");
	    }

	    @BeforeMethod
	    public void setup() {

	        playwright = Playwright.create();

	        browser = playwright.chromium().launch(
	                new BrowserType.LaunchOptions()
	                        .setHeadless(false)
	                        .setSlowMo(100));

	        context = browser.newContext();

	        page = context.newPage();
	    }

	    @AfterMethod
	    public void tearDown() {

	        if (context != null)
	            context.close();

	        if (browser != null)
	            browser.close();

	        if (playwright != null)
	            playwright.close();
	    }

	    @AfterSuite
	    public void sendExecutionReport() {

	        long endTime = System.currentTimeMillis();

	        long totalTime = (endTime - startTime) / 1000;

	        System.out.println("Execution Time : " + totalTime + " Seconds");

	        System.out.println("Preparing Automation Report...");

	        try {

	            SendMail.sendReport(totalTime);

	            System.out.println("Email Sent Successfully");

	        } catch (Exception e) {

	            System.out.println("Failed to Send Email");

	            e.printStackTrace();
	        }
	    }


}
