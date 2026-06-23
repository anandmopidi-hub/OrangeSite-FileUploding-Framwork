package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class LoginPage {



	 private Page page;

	    public LoginPage(Page page) {
	        this.page = page;
	    }

	    public void login(String user, String pass) {

	        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	        page.getByRole(AriaRole.TEXTBOX,
	                new Page.GetByRoleOptions().setName("Username"))
	                .fill(user);

	        page.getByRole(AriaRole.TEXTBOX,
	                new Page.GetByRoleOptions().setName("Password"))
	                .fill(pass);

	        page.getByRole(AriaRole.BUTTON,
	                new Page.GetByRoleOptions().setName("Login"))
	                .click();

	        // Wait for Dashboard Heading
	        page.getByRole(AriaRole.HEADING,
	                new Page.GetByRoleOptions().setName("Dashboard"))
	                .waitFor();
	    }
}
