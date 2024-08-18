package TestComponents;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import annotations.TestDescription;
import utilities.ReportGeneration;

public class TestListener extends BaseTest implements ITestListener {

    ExtentReports extent = ReportGeneration.getReportObject(); // Manage report generation
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // ThreadLocal for thread safety
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getCustomTestName(result); // Get the test name
        test = extent.createTest(testName); // Create a new test report entry
        extentTest.set(test); // Set the test for the current thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachScreenshot(result); // Attach screenshot on success
        extentTest.get().log(Status.PASS, "Test Passed"); // Log success
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result); // Attach screenshot on failure
        extentTest.get().fail(result.getThrowable()); // Log failure and exception
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Finalize and generate the report
    }

    private void attachScreenshot(ITestResult result) {
        try {
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String filePath = getScreenShot(result.getMethod().getMethodName(), driver); // Capture screenshot
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // Attach screenshot
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCustomTestName(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        TestDescription annotation = method.getAnnotation(TestDescription.class);
        return annotation != null ? annotation.value() : result.getMethod().getMethodName(); // Get custom test name
    }
}