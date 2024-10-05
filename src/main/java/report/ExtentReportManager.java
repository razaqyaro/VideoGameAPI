package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager
{

    public static ExtentReports getReportObject()
    {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("API Automation Results");
        reporter.config().setDocumentTitle("Video Game API Automation Report");

        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Tester", "Abdul-Razak Hussein");

        return extentReport;
    }


}
