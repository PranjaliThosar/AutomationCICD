package SeleniumDemoT.TestComponants;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumDemoT.resources.ExtentReporterNG;

public class Listener extends BaseTests implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();//Thread safe

	@Override
	public void onTestStart(ITestResult result) {

		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id(ErrorValidationTest)->Test


	  }

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test Passed");
	  }

	@Override
	public void onTestFailure(ITestResult result) {
		
		

		extentTest.get().fail(result.getThrowable());

		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());

		//screenshot, attach to result



	  }


	@Override
	public void onFinish(ITestContext context) {
	     extent.flush();
	  }


}
