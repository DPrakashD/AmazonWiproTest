package Runner;

import java.io.IOException;
import java.text.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import Utilities.LogFileGenerator;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions ( features = "src/test/resources/Features",
glue={"StepDefinitions"},
plugin   = {"pretty:STDOUT","html:Reports/cucumber-pretty","junit:target/surefire-reports/TEST-TestSuite.xml"},  
tags= {"@AmazonShopping"},

monochrome=true ,dryRun = false)

 public class TestRunner {
	@BeforeClass 
	 public static void intialSetup() throws IOException
	 {
		 
		 LogFileGenerator.toStartWriteLogFile(); 
	 }
	 @AfterClass
	 public static void reportsetup() throws IOException, ParseException
	 {
		 LogFileGenerator.toStopWriteLogFile();
		 	 
	 }
}

