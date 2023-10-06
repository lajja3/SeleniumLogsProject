//This project is from Things To Do List Topic 5 - Frameworks - Page Object Model Framework 
//Part 8 - https://www.youtube.com/watch?v=C7TEuhgVDYo&list=PLFGoYjJG_fqq6cHeqfsDes3pdVh3kpl74&index=8

//Log4j API libraries used to generate logs in Selenium

package crm.com.logtest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrmLoginLogsTest {

	    //What is log? : capturing info/activities at the time of program execution. 
		// types of logs: OR levels of logs
			//1. info
			//2. warn
			//3. debug
			//4. fatal -error
			
		//how to generate the logs? : use Apache log4j API (log4j jar)
		//How it works? : it reads log 4j configuration from log4j.properties file
		//where to create: create inside resources folder src/main/resources
		
		WebDriver driver;
		
		//Logger class is used to write your own console logs available in apache.log4j libraries
		Logger log = Logger.getLogger(CrmLoginLogsTest.class);//classname.class
		
		
		@BeforeMethod
		public void setup(){
			log.info("****************************** Starting test cases execution  *****************************************");

			System.setProperty("webdriver.gecko.driver", "C:/Users/Lajja Patel/OneDrive/Desktop/MySelenium57/Cucumber/CucumberWorkspace/geckodriver.exe");
			driver = new FirefoxDriver(); 
			log.info("******* launching FF broswer *******"); //log.info is same system.printn
			//here we are declaring all the properties in before method only , no separate class created for same
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //changed selenium version from 3.5.3 to 4.13.0 latest stable version 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  //thats why this warning appeared
			
			driver.get("https://classic.freecrm.com/");
			
			log.info("entering Free CRM application url"); //info log
			log.warn("Hey this is just a WARNING message"); //warning log
			log.fatal("Hey this is just fatal ERROR message"); //error log
			log.debug("Hey this is DEBUG message"); //debug log
		}
		
		@Test(priority=1)
		public void freeCrmTitleLogsTest() throws InterruptedException{
			log.info("****************************** starting test case *****************************************");
			log.info("****************************** freeCrmTitleLogsTest *****************************************");
			String title = driver.getTitle();
			System.out.println(title);
			log.info("Free CRM website login page title is--->"+title);
			Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
			Thread.sleep(3000);
			log.info("****************************** ending test case *****************************************");
			log.info("****************************** freeCrmTitleLogsTest *****************************************");

		}
		
		@Test(priority=2)
		public void freemCRMLogoLogsTest() throws InterruptedException{
			log.info("****************************** starting test case *****************************************");
			log.info("****************************** freemCRMLogoLogsTest *****************************************");

			boolean b = driver.findElement(By.xpath("//img[@class='img-responsive']")).isDisplayed();
			Assert.assertTrue(b);
			Thread.sleep(3000);
			log.info("****************************** ending test case *****************************************");
			log.info("****************************** freemCRMLogoLogsTest *****************************************");

		}
		
		

		@AfterMethod
		public void tearDown() throws InterruptedException{
			Thread.sleep(1000);
			driver.quit();
			log.info("****************************** Browser is closed *****************************************");
		}
		
}

//After running this code u will see all ur logs in console as well as in o/p file  FreeCRM_application_LogsFile.log
//if u want to see it in Terminal window then open cmd prompt and RUN below cmds
//>cd (path of ur project)
//>mvn clean install
//In pom.xml I have changed selenium-java version to 4.13.0 from 3.5.3 because version 3.5.3 was giving error in @aftermethod
//error was "org.openqa.selenium.remote.UnreachableBrowserException: Error communicating with the remote browser. It may have died. "
//after changing version to 4.13.0 the error was resolved