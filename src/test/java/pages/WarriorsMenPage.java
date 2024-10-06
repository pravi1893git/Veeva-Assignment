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
import org.testng.Assert;

import utilities.ui.UiUtility;

public class WarriorsMenPage {
	
	UiUtility uiutility = new UiUtility();
	private static Logger logger = LogManager.getLogger(WarriorsMenPage.class);
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@data-trk-id='all-departments']//li[*]/a/span[text()='Jackets']")
	WebElement radiobtn_Jackets;
	
	@FindBy(xpath="//div[@id='onetrust-button-group']/button[@id='onetrust-accept-btn-handler']")
	WebElement oneTrustBannerAccept;
	
	@FindBy(xpath="//div[@role='dialog']")
	WebElement popup_wantPreSaleTicket;
	
	@FindBy(xpath="//div[@role='dialog']//div[text()='x']")
	WebElement popup_close;
	
	@FindBy(xpath="//div[@data-trk-id='json-ld-product-grid']/script")
	WebElement script_product_grid;
	
	@FindBy(xpath="//div[@class='product-grid-top-area']//ul[@aria-label='Page Numbers']//div[@data-talos='pageCount']")
	WebElement txt_pageCount;
	
	
	public WarriorsMenPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectJacketFromAllDepartments() {
		if(uiutility.isElementDisplayed(radiobtn_Jackets, 8))
			uiutility.clickOnElement(radiobtn_Jackets);
			//radiobtn_Jackets.click();
	}
	
	
	public String getProductGridJson() {
		
		String jsonProductGrid = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;", script_product_grid);
		//String jsonProductGrid = script_product_grid.getAttribute("innerHTML"); 

		System.out.println("jsonProductGrid :::::::::\n"+jsonProductGrid);
		return jsonProductGrid;
		
	}
	
	public String getTotalPageCount() {
		String text = null;
		try
		{
			System.out.println("text pag coun: "+txt_pageCount.getAttribute("innerHTML").trim());
			System.out.println(txt_pageCount.getAttribute("innerHTML").trim().split("of")[1].trim());
			text = txt_pageCount.getAttribute("innerHTML").trim().split("of")[1].trim();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return text;
		
	}
	
}
