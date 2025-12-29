package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String reportName;
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test_Report" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/" + reportName);
		sparkReporter.config().setDocumentTitle("e_Commerce");
		sparkReporter.config().setReportName("eCommerce User_Module");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "eCommerce");
		extent.setSystemInfo("Env.", "QA");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("user", System.getProperty("user.name"));
	}
	public void onTestSuccess(ITestResult result){
		test = extent.createTest(result.getName());
		// test.log(Status.PASS,result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Passed");
	}
	public void onTestFailure(ITestResult result){
		test = extent.createTest(result.getName());
		// test.log(Status.PASS,result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL,"Test Failed");

		try {
			String screenshotPath = new BaseTest().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public void onTestSkipped(ITestResult result){
		test = extent.createTest(result.getName());
		// test.log(Status.PASS,result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP,"Test Skipped");
	}
	public void onFinish(ITestContext testContext){
		extent.flush();
//		String pathOfReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
//		File extentReport = new File(pathOfReport);
//		try {
//		Desktop.getDesktop().browse(extentReport.toURI());
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
	}
}