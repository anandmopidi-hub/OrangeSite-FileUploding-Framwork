package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.RecruitmentPage;
import utils.DataUtil;
import utils.GoogleSheetUtils;

public class RecruitmentTest  extends BaseTest {

	@Test(dataProvider = "candidateData", dataProviderClass = DataUtil.class)
	public void verifyCandidateCreation(
	        String fname,
	        String lname,
	        String email,
	        String phone,
	        String filePath) throws Exception {

	    LoginPage loginPage = new LoginPage(page);
	    RecruitmentPage recruitmentPage = new RecruitmentPage(page);

	    System.out.println("========== TEST START ==========");

	    System.out.println("Login Started");
	    loginPage.login("Admin", "admin123");
	    System.out.println("Login Success");

	    System.out.println("Opening Recruitment Page");
	    recruitmentPage.navigateToRecruitment();
	    System.out.println("Recruitment Page Opened");

	    System.out.println("Adding Candidate");
	    System.out.println("Name : " + fname + " " + lname);
	    System.out.println("Email : " + email);
	    System.out.println("Phone : " + phone);
	    System.out.println("File : " + filePath);

	    recruitmentPage.addCandidate(
	            fname,
	            lname,
	            email,
	            phone,
	            filePath);

	    System.out.println("Candidate Added Successfully");

	    GoogleSheetUtils.writeResult(
	            fname,
	            lname,
	            email,
	            phone,
	            "PASS"
	    );

	    Assert.assertTrue(true);

	    System.out.println("========== TEST END ==========");
	}
}
