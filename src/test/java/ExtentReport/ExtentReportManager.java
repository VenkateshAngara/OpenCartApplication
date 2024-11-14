package ExtentReport;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	ExtentSparkReporter sparkreport;
	ExtentReports report;
	ExtentTest test;
	String reportName;

	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";
		sparkreport = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);
		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setDocumentTitle("OpenCart Automation Testing");
		sparkreport.config().setReportName("OpenCartFunctional Testing");

		report = new ExtentReports();
		report.attachReporter(sparkreport);
		report.setSystemInfo("Application", "OpenCart");
		report.setSystemInfo("Module Name", "Admin");
		report.setSystemInfo("Sub Module Name", "Customer");
		report.setSystemInfo("Tester Name", System.getProperty("user.name"));
		report.setSystemInfo("Environment Info", "QA");
		String browser = context.getCurrentXmlTest().getParameter("Browser");
		report.setSystemInfo("Browser Name", browser);
		String os = context.getCurrentXmlTest().getParameter("Os");
		report.setSystemInfo("Operating System ", os);
		List<String> includegroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includegroups.isEmpty()) {
			report.setSystemInfo("Groups", includegroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got Successfully Executed");
	}

	public void onTestFailure(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imagePath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		report.flush();

		String pathOfReport = System.getProperty("user.dir") + "/reports/" + reportName;
		File extentReport = new File(pathOfReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + reportName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("venkateshangara666@gmail.com", "gkih pcvu mclt bjyq"));
			email.setSSLOnConnect(true);
			email.setFrom("venkateshangara666@gmail.com");
			email.setSubject("Test Reports of OpenCart Application");
			email.setMsg("Please Find Atttach Report....");
			email.addTo("venkateshangara769@gmail.com");
			email.attach(url, "extent Report", "Please check Report...");

			email.send();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
