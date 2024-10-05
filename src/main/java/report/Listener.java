package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.restassured.response.Response;

public class Listener implements ITestListener {

    ExtentReports extent = ExtentReportManager.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        // Start a new test in Extent Reports for each test method
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");

        // Log the throwable (exception) for failures
        extentTest.get().fail(result.getThrowable());

        // Try to get the response object from the test method (if available)
        try {
            Response response = (Response) result.getTestClass().getRealClass().getField("response").get(result.getInstance());

            if (response != null) {
                test.log(Status.INFO, "Response Code: " + response.getStatusCode());
                test.log(Status.INFO, "Response Body: " + response.getBody().asPrettyString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {}

    public void onFinish(ITestContext context) {
        // Flush the report at the end of test execution
        extent.flush();
    }
}
