package stepdefinition;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

import drivermanager.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.WarriorsHomePage;
import pages.WarriorsMenPage;
import pages.WarriorsNewsPage;
import utilities.api.ApiUtility;
import utilities.common.ConfigReader;
import utilities.common.FileUtility;

public class CoreProductStepDef {

	WarriorsHomePage warriorshomepage;
	WarriorsMenPage warriorsmenpage;
	WarriorsNewsPage warriorsnewspage;
	ApiUtility apiutility;
	FileUtility fileutility;
	
	private static Logger logger = LogManager.getLogger(WarriorsHomePage.class);
	
	public CoreProductStepDef() {	
		warriorshomepage = new WarriorsHomePage(DriverManager.getDriver());
		warriorsmenpage = new WarriorsMenPage(DriverManager.getDriver());
		warriorsnewspage = new WarriorsNewsPage(DriverManager.getDriver());
		apiutility = new ApiUtility();
		fileutility = new FileUtility();
		
	}
	
	@Given("User invokes the {string} url")
	public void user_invokes_the_url(String string) {
		String url = ConfigReader.prop.getProperty("nba.warriors.url").trim();
		DriverManager.getDriver().get(url);
		logger.info("Navigated to "+url);
		warriorshomepage.closeAnyDialogIfFound();
		warriorshomepage.acceptOneTrustBannerIfFound();
	}

	@When("User navigates to {string} -> {string}")
	public void user_navigates_to(String mainMenuSelection, String subMenuSelection) {
		warriorshomepage.navigationToSubMenu(mainMenuSelection, subMenuSelection);
	}

	@Given("User selects Jackets from All Departments")
	public void user_selects_jackets_from_all_departments() {
		warriorshomepage.switchToOpenedWindow(1);
		warriorsmenpage.selectJacketFromAllDepartments();
	}

	@Then("User stores Price, Title and Top Seller message from each products to a text file and attach to report")
	public void user_stores_price_title_and_top_seller_message_from_each_products_to_a_text_file_and_attach_to_report() {
				
		ConfigReader.setAttachWebpageExtractedFlag(true);
		
		File textFile = new File(fileutility.getWebpageExtractedFilePath());
		StringBuffer dataToWrite = new StringBuffer();
		try {
			textFile.createNewFile();
			logger.info("Created .txt file : "+textFile);
			
			FileWriter fr = new FileWriter(textFile);
			String currentURL = DriverManager.getDriver().getCurrentUrl();
		    String currentPageHostname = currentURL.split("/")[2];
		    
		    Map<String,Object> headers = new HashMap<String,Object>();
		    headers.putAll(ApiUtility.getReqHeaders(currentPageHostname));
		    headers.put("cookie", apiutility.getCookiesForUrl(currentURL, currentPageHostname));
		    
		    //warriorsmenpage.getProductGridJson();
		    
		    String totalPageCount = warriorsmenpage.getTotalPageCount();
		    logger.info("Total pages count : "+totalPageCount);
		    int productCounter = 0;
		    for(int i=1;i<=Integer.parseInt(totalPageCount);i++)
		    {
		    	logger.info("Iterating through each page....");
		    	currentURL = (i==1)?currentURL:(currentURL.split("\\?")[0]+"?pageNumber="+i);
		    	String response = apiutility.getResponseFromGetApi(currentURL, headers);
			    String responseJson = "{\"browse-data\":"+response.split("\"browse-data\":")[1].split("</script>")[0];
			   
		        JSONObject json = new JSONObject(responseJson);   
		        JSONObject browseData = (JSONObject) json.get("browse-data");
		        JSONArray productsArray = browseData.getJSONArray("products");  
		        for (int j = 0; j < productsArray.length(); j++) {  
		              
		        	logger.info("Iterating through each product within each page....");
		        	productCounter++;
		        	String topSellerValue = "";
		        	String priceRangeMin = "";
		        	String priceRangeMax = "";
		        	String price = "";
		        	
		        	String title = JsonPath.read(responseJson, "$.browse-data.products["+j+"].title").toString();
		        	String currencyCode = JsonPath.read(responseJson, "$.browse-data.products["+j+"].price.discountPrice.money.userCC").toString();
		        	
		        	Object topSeller = JsonPath.read(responseJson, "$.browse-data.products["+j+"].topSeller");
		        	if(topSeller!=null)
		        	{
		        		topSellerValue = JsonPath.read(responseJson, "$.browse-data.products["+j+"].topSeller.value").toString();
		        	}
		        	
		        	Object userCurrencyValueRange = JsonPath.read(responseJson, "$.browse-data.products["+j+"].price.discountPrice.money.userCurrencyValueRange");
		        	if(userCurrencyValueRange!=null)
		        	{
		        		priceRangeMin = JsonPath.read(responseJson, "$.browse-data.products["+j+"].price.discountPrice.money.userCurrencyValueRange.min").toString();
		        		priceRangeMax = JsonPath.read(responseJson, "$.browse-data.products["+j+"].price.discountPrice.money.userCurrencyValueRange.max").toString();
		        	}
		        	else
		        	{
		        		price = JsonPath.read(responseJson, "$.browse-data.products["+j+"].price.discountPrice.money.userCurrencyValue").toString();
		        	}
		        	 dataToWrite.append("Product count: "+productCounter+"\n");
		        	 dataToWrite.append("Product title: "+title+"\n");
		        	 dataToWrite.append("Product price: "+currencyCode+" "+((price.isEmpty())?(priceRangeMin+" - "+priceRangeMax):price)+"\n");
		        	 String topSelletText = (topSellerValue.isEmpty())?("Product topSeller: "+topSellerValue):("Product topSeller: Most Popular in "+topSellerValue);
		        	 dataToWrite.append(topSelletText+"\n\n\n");
		        }      
		    }
		    fr.write(dataToWrite.toString());
		    logger.info("All product specific details stored in .txt file : "+textFile);
		    fr.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	}

	
	@When("User navigates to ... -> News And Features")
	public void user_navigates_to_news_and_features() {
		warriorshomepage.navigateToNewsAndFeatures();
	}

	@Then("User verifies the count of total video feeds shown")
	public void user_verifies_the_count_of_total_video_feeds_shown() {
		warriorsnewspage.getCountOfAllVideoFeeds();
	}

	@Then("User verifies the count of video feeds those are present in the page greater than or equal to {string}d")
	public void user_verifies_the_count_of_video_feeds_those_are_present_in_the_page_greater_than_or_equal_to_3d(String howmanydays) {
		warriorsnewspage.getCountOfAllVideoFeedsGreaterThanOrEqualTo(howmanydays);
	}
	
	
	
	
	
	
	
}
