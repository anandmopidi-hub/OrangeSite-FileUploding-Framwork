package pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class RecruitmentPage {
	 private Page page;

	    public RecruitmentPage(Page page) {
	        this.page = page;
	    }

	    public void navigateToRecruitment() {
	    	page.getByRole(AriaRole.LINK,
	    			new Page.GetByRoleOptions().setName("Recruitment"))
	    			.click();
	    }

	    public void addCandidate(String fname, String lname, String email, String phone, String filePath) {

	        // FIXED ADD BUTTON
	        page.locator("button:has-text('Add')").click();

	        page.fill("input[name='firstName']", fname);
	        page.fill("input[name='lastName']", lname);

	        // vacancy
	        page.locator(".oxd-select-text").first().click();
	        page.locator("text=Payroll Administrator").click();

	        page.locator("input[placeholder='Type here']")
	        .first()
	        .fill(email);

	page.locator("input[placeholder='Type here']")
	        .nth(1)
	        .fill(phone);

	        // FILE UPLOAD FIXED
	    
	      
	        page.locator("input[type='file']")
	        .setInputFiles(Paths.get(filePath));

	        page.locator("button:has-text('Save')").click();
	    }
}
