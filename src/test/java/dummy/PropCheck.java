package dummy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropCheck {

	public static Properties prop = new Properties();
	
	public static void main(String[] args) {
		System.out.println("file: "+System.getProperty("user.dir")+"\\src\\main\\resources\\config\\config.properties");
		getProps();
		//System.out.println(prop.getProperty("nba.warriors.url"));
	}

	public static Properties getProps() {
		
		try {
			
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\config\\config.properties"));
			prop.load(fis);
			System.out.println(prop.getProperty("nba.warriors.url"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
