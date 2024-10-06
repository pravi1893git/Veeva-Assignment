package utilities.common;

import java.io.File;

public class FileUtility {

	public String getWebpageExtractedFilePath() {
		
		String strFolder = System.getProperty("user.dir")+"\\target\\"+ConfigReader.getBrowserType();
		File folder = new File(strFolder);
		if(folder.exists())
		{
			folder.delete();
			folder.mkdir();
		}
		else
			folder.mkdir();
		
		String filePath = strFolder+"\\webpage-extracted.txt";
		return filePath;
	}

}
