package utils;

import org.testng.annotations.DataProvider;

public class DataUtil {
	 @DataProvider(name = "candidateData")
	    public Object[][] getCandidateData() {

	        return new Object[][] {

	            {
	                "Mopidi",
	                "Anand",
	                "anand1@test.com",
	                "9867672761",
	                System.getProperty("user.dir") + "/test-data/Playwright_Java_Complete_Notes.pdf"
	            },

	            {
	                "John",
	                "Doe",
	                "john@test.com",
	                "9867672762",
	                System.getProperty("user.dir") + "/test-data/Playwright_Java_Complete_Notes.pdf"
	            },

	            {
	                "Jane",
	                "Smith",
	                "jane@test.com",
	                "9867672763",
	                System.getProperty("user.dir") + "/test-data/Playwright_Java_Complete_Notes.pdf"
	            },

	            {
	                "Amit",
	                "Kumar",
	                "amit@test.com",
	                "9867672764",
	                System.getProperty("user.dir") + "/test-data/Playwright_Java_Complete_Notes.pdf"
	            },

	            {
	                "Sara",
	                "Khan",
	                "sara@test.com",
	                "9867672765",
	                System.getProperty("user.dir") + "/test-data/Playwright_Java_Complete_Notes.pdf"
	            }
	        };
	    }

}
