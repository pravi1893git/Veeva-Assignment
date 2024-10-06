package utilities.ui;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import drivermanager.DriverManager;
import pages.WarriorsHomePage;

public class UiUtility {

	private static Logger logger = LogManager.getLogger(UiUtility.class);
	
	public boolean isElementDisplayed(WebElement element, int waitSeconds) {
		
		boolean flag = false;
		try
		{
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(waitSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = true;
			logger.info("Element is displayed");
		}
		catch(NoSuchElementException c) {
			flag = false;
			logger.info("Element is not displayed");
			//c.printStackTrace();
		}
		catch(TimeoutException t) {
			flag = false;
			logger.info("Element is not displayed");
			//t.printStackTrace();
		}
		catch(Exception p) {
			flag = false;
			logger.info("Element is not displayed");
			//p.printStackTrace();
		}
		
		return flag;
	}
	
	public void clickOnElement(WebElement element) {
		element.click();
		logger.info("Element is clicked");
	}
	
}
