package hooks;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import drivermanager.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.common.ConfigReader;

public class Hooks {
	
	private static Logger logger = LogManager.getLogger(Hooks.class);
	
	@Before
	public void beforeScenario() {
		
		String browser = ConfigReader.getBrowserType();
		try
		{
			DriverManager.launchBrowser(browser);
			logger.info("Launched "+browser+"...");
		}catch(Exception e) {
			logger.info("Unable to launch "+browser+"...");
		}
		
	}
	
	
	@After
	public void afterScenario(Scenario scenario) {
		
		// 
		if(ConfigReader.getAttachWebpageExtractedFlag())
		{
			String strFolder = System.getProperty("user.dir")+"\\target\\"+ConfigReader.getBrowserType();
			File folder = new File(strFolder);
			String filePath = "";
			
			byte[] data = null;
			try {
				if(folder.exists())
				{
					filePath = System.getProperty("user.dir")+"\\target\\"+ConfigReader.getBrowserType()+"\\webpage-extracted.txt";
					data = Files.readAllBytes(Paths.get(filePath));
					if(data.length!=0)
					{
						scenario.attach(data, ".txt", "webpage-extracted");
						logger.info("Attached webpage-extracted.txt in report");
					}	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ConfigReader.setAttachWebpageExtractedFlag(false);
		}
		
		DriverManager.closeBrowser();
		logger.info("Closed "+ConfigReader.getBrowserType()+" browser...");
	}
	
}
