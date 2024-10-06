package utilities.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.Properties;

import org.openqa.selenium.support.PageFactory;

import drivermanager.DriverManager;
import pages.WarriorsHomePage;

public class ConfigReader {

	public static Properties prop;
	public static boolean attach_webpage_extracted = false;
	
	private static String browserType = null;
	
	public static synchronized boolean getAttachWebpageExtractedFlag() {
		return attach_webpage_extracted;
    } 
	
	public static synchronized boolean setAttachWebpageExtractedFlag(boolean flag) {
		return attach_webpage_extracted=flag;
    } 
	
	public static String getBrowserType() {
		
		if(browserType!=null)
			return browserType;
		else
			throw new RuntimeException("browser not specified in testng.xml");
	}

	public static void setBrowserType(String browser) {
		browserType = browser;
	}
	
	public Properties loadProperties() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties"));
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	
}
