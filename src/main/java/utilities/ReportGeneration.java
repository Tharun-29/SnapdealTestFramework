package utilities;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGeneration {

    // Create and configure the ExtentReports object
    public static ExtentReports getReportObject() {

        // Path for the report
        String path = System.getProperty("user.dir") + "//reports//SnapdealTestReport.html";

        // Initialize ExtentSparkReporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Snapdeal Automation Test Report");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        reporter.config().setCss(".badge-primary { background-color: #1a73e8; }");

        // Initialize ExtentReports and attach reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Set system information
        extent.setSystemInfo("Tester", "Tharun");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);

        return extent;
    }
}