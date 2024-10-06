package drivermanager;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import testrunner.TestRunner;

public class DriverManager {
	
	private static final Logger logger = LogManager.getLogger(DriverManager.class);
	
	public static ChromeOptions chromeOptions = new ChromeOptions();
	public static EdgeOptions edgeOptions = new EdgeOptions();
	public static FirefoxOptions firefoxOptions = new FirefoxOptions();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


	public static void launchBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver.set(new ChromeDriver(chromeOptions));
			logger.info(browser+" browser is launched");
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			edgeOptions.addArguments("start-maximized");
			edgeOptions.addArguments("--remote-allow-origins=*");
			driver.set(new EdgeDriver(edgeOptions));
			logger.info(browser+" browser is launched");
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			logger.info(browser+" browser is launched");
		}
		
	}

	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void closeBrowser() {
		driver.get().quit();
	}

}
