package MUFrameworkUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
	
    private static ExtentReports extent;
    static ExtentTest test;

    public static void initReports() {
        if (extent == null) {
        	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static void createTest(String testCaseName) {
        test = extent.createTest(testCaseName);
        ExtentManager.setTest(test);
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}

