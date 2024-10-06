package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ui.UiUtility;

public class WarriorsNewsPage {
	
	UiUtility uiutility = new UiUtility();
	private static Logger logger = LogManager.getLogger(WarriorsNewsPage.class);
	
	WebDriver driver;
	
	@FindBy(xpath="//main/div[2]//div[@data-testid='content-grid']//li[*]/div[@data-testid='tile-featured-article']//a")
	WebElement link_featured_video_feed;
	
	@FindBy(xpath="//main/div[2]//div[@data-testid='content-grid']//li[*]/div[@data-testid!='display-ad']/div[2]/a")
	List<WebElement> link_all_video_feed_with_no_featured;
	
	@FindBy(xpath="//main/div[2]//div[@data-testid='content-grid']//li[*]/div[@data-testid!='display-ad']//div[@data-testid='tile-meta']//span")
	List<WebElement> txt_uploaded_when_all_video_feed;
	
	@FindBy(xpath="//div[@role='dialog']")
	WebElement popup_wantPreSaleTicket;
	
	@FindBy(xpath="//div[@role='dialog']//div[text()='x']")
	WebElement popup_close;
	
	public WarriorsNewsPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getCountOfAllVideoFeeds() {
		int featured_count = 0;
		
		if(uiutility.isElementDisplayed(link_featured_video_feed, 3))
			featured_count = 1;
		System.out.println("Total video feeds count: "+(featured_count+link_all_video_feed_with_no_featured.size()));
		logger.info("Total video feeds count : "+featured_count+link_all_video_feed_with_no_featured.size());
	}
	
	public void getCountOfAllVideoFeedsGreaterThanOrEqualTo(String howmanydays) {
		
		List<String> duration = new ArrayList<String>();
		ArrayList<Integer> day = new ArrayList<Integer>();
		ArrayList<Integer> year = new ArrayList<Integer>();
		
		for(WebElement element : txt_uploaded_when_all_video_feed) {
			duration.add(element.getText().trim());
		}
		for(String item:duration)
		{
			if(item.contains("d"))
				day.add(Integer.parseInt(item.replace("d", "")));
			else if(item.contains("y"))
				year.add(Integer.parseInt(item.replace("y", "")));
		}
		Collections.sort(day);
		Collections.sort(year);
		System.out.println(day);
		System.out.println(year);
		
		day.removeIf(n -> n < Integer.parseInt(howmanydays)); 
		System.out.println(day);
		System.out.println("Total video feeds count >= "+howmanydays+"d : "+day.size());
		logger.info("Total video feeds count >= "+howmanydays+"d : "+day.size());
	}
	
	
}
