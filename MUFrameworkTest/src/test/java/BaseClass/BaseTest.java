package BaseClass;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import MUFrameworkUtilities.ExtentReportUtil;

public class BaseTest {
	
	public WebDriver driver  = null;

	@BeforeSuite(alwaysRun = true)
	public void startReport() {
		ExtentReportUtil.initReports();
	}

	@Parameters({"testCaseName", "browserName"})
	@BeforeMethod
	public void setUp(@Optional("Default Test") String testCaseName, 
			@Optional("chromeDriver") String browserName) {
		
		ExtentReportUtil.createTest(testCaseName);
		
		WebDriver localDriver = null;

		String projectPath = System.getProperty("user.dir");

		if (browserName.toLowerCase().contains("chrome") || testCaseName.toLowerCase().contains("chrome")) {
			localDriver = new ChromeDriver();
			localDriver.manage().window().maximize();
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.setProperty("webdriver.chrome.driver", projectPath + "/Drivers/ChromeDriver/chromedriver.exe");
		} else if (browserName.toLowerCase().contains("firefox") || browserName.toLowerCase().contains("gecko") || testCaseName.toLowerCase().contains("firefox")) {

			FirefoxOptions cap = new FirefoxOptions();
			cap.setAcceptInsecureCerts(true);
			localDriver = new FirefoxDriver(cap);
			localDriver.manage().window().maximize();
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.setProperty("webdriver.gecko.driver", projectPath + "/Drivers/geckoDriver/geckodriver.exe");
		}
		this.driver = localDriver;
		this.driver.get("https://www.testmuai.com/");
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}

	@AfterMethod
	public void tearDown() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void saveReport() {
		ExtentReportUtil.flushReports();
	}
}
