package pages;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.common.ConfigReader;
import utilities.ui.UiUtility;

public class WarriorsHomePage {
	
	UiUtility uiutility = new UiUtility();
	private static Logger logger = LogManager.getLogger(WarriorsHomePage.class);
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='onetrust-banner-sdk']")
	WebElement banner_oneTrust;
	
	@FindBy(xpath="//div[@id='onetrust-button-group']/button[@id='onetrust-accept-btn-handler']")
	WebElement btn_accept_oneTrustBanner;
	
	@FindBy(xpath="//div[@role='dialog']")
	WebElement popup_wantPreSaleTicket;
	
	@FindBy(xpath="//div[@role='dialog']//div[text()='x']")
	WebElement btn_close_wantPreSaleTicketPopup;
	
	@FindBy(xpath="//nav[@aria-label='header-secondary-menu']//span[text()='...']")
	WebElement navbar_secMenu;
	
	@FindBy(xpath="//nav[@aria-label='header-secondary-menu']//nav[@aria-label='submenu']//li[*]/a[text()='News & Features']")
	WebElement submenuitem_newsAndFeatures_secMenu;
	
	
	public WarriorsHomePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void closeAnyDialogIfFound() {
		try
		{
			if(uiutility.isElementDisplayed(popup_wantPreSaleTicket, 5))
			{
				if(ConfigReader.getBrowserType().equalsIgnoreCase("firefox"))
				{
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", popup_wantPreSaleTicket);
				}
				else
					uiutility.clickOnElement(btn_close_wantPreSaleTicketPopup);
				
				logger.info("Closed dialog seen during browser launch");
				//btn_close_wantPreSaleTicketPopup.click();
			}	
		}
		catch(Exception e) {
			logger.info("No dialog seen on browser launch");
		}
	}
	
	public void acceptOneTrustBannerIfFound() {
		try
		{
			if(uiutility.isElementDisplayed(banner_oneTrust, 5))
			{
				logger.info("OneTrust Banner was seen during browser launch");
				uiutility.clickOnElement(btn_accept_oneTrustBanner);
				//btn_accept_oneTrustBanner.click();
				logger.info("Accepted OneTrust Banner");
			}
		}
		catch(Exception e) {
			logger.info("OneTrust Banner was not seen during browser launch");
		}
	}
	
	// Example for dynamic xpath handling avoiding multiple element declaration for future usecases (note: outside of @FindBy)
	public void navigationToSubMenu(String mainMenuSelection, String subMenuSelection) {
		try
		{
			logger.info("Driver in window: "+driver.getTitle());
			
			Actions actions = new Actions(driver);
			
			String prMenu = "//nav[@aria-label='Golden State Warriors navigation']//nav[@aria-label='header-primary-menu']";
			
			String prMenuSelection = prMenu+"//ul[@role='menubar']/li[*]/a/span[text()='"+mainMenuSelection+"']";
			
			WebElement dynamicMainMenuSelection = driver.findElement(By.xpath(prMenuSelection));
			actions.moveToElement(dynamicMainMenuSelection).build().perform();
			
			String subMenu = prMenuSelection+"/parent::a/following-sibling::nav";
			WebElement e_SubMenu = driver.findElement(By.xpath(subMenu));
			if(uiutility.isElementDisplayed(e_SubMenu, 5))
			{
				String cMenuSelection = prMenuSelection+"/parent::a/following-sibling::nav//li/a[text()=\""+subMenuSelection+"\"]";
				WebElement dynamicSubMenuSelection = driver.findElement(By.xpath(cMenuSelection));
				uiutility.clickOnElement(dynamicSubMenuSelection);
				//dynamicSubMenuSelection.click();
			}
			
			//Thread.sleep(10000);
			logger.info("Navigated to : "+mainMenuSelection+" -> "+subMenuSelection);
			
		}
		catch(Exception e) {
			logger.info("Unable to navigate to : "+mainMenuSelection+" -> "+subMenuSelection);
			e.printStackTrace();
		}
	}
	
	public void switchToOpenedWindow(int windowIndex) {

		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(windowIndex));
		logger.info("Driver switched to window: "+driver.getTitle());
	}
	
	public void navigateToNewsAndFeatures() {
		
		Actions actions = new Actions(driver).moveToElement(navbar_secMenu).click(submenuitem_newsAndFeatures_secMenu);
		actions.build().perform();
		logger.info("Navigated to 'News & Features'..");
	}
	
	
}
