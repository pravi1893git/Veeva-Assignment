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
	
	@SuppressWarnings("deprecation")
	@Before
	public void beforeScenario() {
		System.out.println("inside hooks");
		System.out.println("launch thread id: "+Thread.currentThread().getId());
		System.out.println("url:::::::::::::::::: "+ConfigReader.prop.getProperty("nba.warriors.url"));
		String browser = ConfigReader.getBrowserType();
		DriverManager.launchBrowser(browser);
		
		
		
		
	}
	
	
	@After
	public void afterScenario(Scenario scenario) {
		System.out.println("close thread id: "+Thread.currentThread().getId());
		
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
					scenario.attach(data, ".txt", "webpage-extracted");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ConfigReader.setAttachWebpageExtractedFlag(false);
		}
		
		DriverManager.closeBrowser();
	}
	
}
