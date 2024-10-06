package testrunner;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.common.ConfigReader;


@CucumberOptions(
		features="src/test/resources/features",
		glue={"stepdefinition","hooks"},
		tags = "@cp",
		monochrome = true,
		plugin = { "pretty","json:target/cucumber-reports/Cucumber.json", "html:target/cucumber-reports/report.html"})


public class TestRunner extends AbstractTestNGCucumberTests{

	@BeforeSuite
	public void propLoader() {
		ConfigReader configReader = new ConfigReader();
		configReader.loadProperties();
	}
	
	@BeforeTest
	@Parameters({"browser"})
	public void defineBrowser(String browser) {
		ConfigReader.setBrowserType(browser);
		System.out.println(browser);
	}

	@Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	
}
