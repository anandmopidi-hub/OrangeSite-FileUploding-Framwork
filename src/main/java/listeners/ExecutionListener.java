package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExecutionListener implements ITestListener {

	   public static int totalTests;
	    public static int passedTests;
	    public static int failedTests;

	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("STARTED : " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        passedTests++;
	        System.out.println("PASSED : " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {

	        failedTests++;

	        System.out.println("FAILED : " + result.getName());

	        if(result.getThrowable() != null) {
	            result.getThrowable().printStackTrace();
	        }
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        totalTests = passedTests + failedTests;
	    }
}
