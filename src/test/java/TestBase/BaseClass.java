package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import PageObjectModel.MyAccountPage;
import PageObjectModel.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public HomePage hm;
	public RegisterPage rp;
	public Properties p;
	public LoginPage lp;
	public MyAccountPage mp;

	@Parameters({ "Os", "Browser" })
	@BeforeClass(groups= {"Regression","Master","Smoke"})
	public void Setup(String os, String Browser) throws IOException {

		FileReader file = new FileReader("./src/main/java/config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		if(p.getProperty("Execution_Environment").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap =new DesiredCapabilities();
			if(os.equalsIgnoreCase("Windows")) {
				cap.setPlatform(Platform.WIN11);
			}
		    if(os.equalsIgnoreCase("Mac")) {
				cap.setPlatform(Platform.MAC);
			}else {
				System.out.println("Invalid Operating System");
				return;
			}
			switch (Browser.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "safari":
				cap.setBrowserName("Safari");
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
			
			driver =new RemoteWebDriver(new URL("http://10.152.62.249:4444/wd/hub"),cap);	
		}
		if(p.getProperty("Execution_Environment").equalsIgnoreCase("local")) {
		switch (Browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;

		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
	}

	public String RandomString() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;

	}

	public String RandomNumber() {
		@SuppressWarnings("deprecation")
		String generatedNumber = RandomStringUtils.randomNumeric(9);
		return generatedNumber;

	}

	@SuppressWarnings("deprecation")
	public String RandomAlphaNumeric() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + generatedNumber);

	}

    @AfterClass(groups= {"Regression","Master","Smoke"})
	public void TearDown() {
		driver.quit();

	}
    public  String captureScreen(String testcaseName) throws IOException{
    	String TimeStamp=new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss").format(new Date());
    	TakesScreenshot ts= (TakesScreenshot)driver;
    	File sourceFile=ts.getScreenshotAs(OutputType.FILE);
    	String targetFilePath=System.getProperty("user.dir")+"/screenShots/"+testcaseName+"_"+TimeStamp;
    	File targetFile=new File(targetFilePath);
    	sourceFile.renameTo(targetFile);
    	return targetFilePath;
    }

//	@AfterSuite
//	public void sentreport() {
//		SendEmail.sendMail(System.getProperty("user.dir") + "/target/surefire-reports/emailable-report.html");
//	}
}
