package Chapter7;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreatingReports {
    public static void main(String[] args) {
        //report document
        ExtentReports report = new ExtentReports();
        ExtentSparkReporter rptSpark = new ExtentSparkReporter("src/main/resources/Reports/Rpt Spark.html");
        rptSpark.config().setReportName("Test Summary Report");
        rptSpark.config().setDocumentTitle("The Spark Report");

        report.attachReporter(rptSpark);

        //create tests
        ExtentTest test1 = report.createTest("TC1 - Test 1");
        test1.pass("TC1 has passed");

        ExtentTest test2 = report.createTest("TC2 - Test 2");
        test2.fail("TC2 has failed");

        ExtentTest test3 = report.createTest("TC3 - Test 3");
        test3.info("TC3 contains general test info");

        ExtentTest test4 = report.createTest("TC4 - Test 4");
        test4.warning("TC4 contains a warning");

        ExtentTest test5 = report.createTest("TC5 - Test 5");
        test5.log(Status.SKIP, "TC5 has been skipped");

        //write the contents of the test(s) onto the report
        report.flush();

    }
}
