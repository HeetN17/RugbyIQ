package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreateDrill;
import pageObject.CreatePlan;
import pageObject.CreateSession;
import pageObject.CreateTeam;
import pageObject.LogIn;

public class CreateDrillTest extends BaseClassRgy {

	  @Test(priority = 1, description = "Verify successful session creation")
	    public void verifyCreateDrill() {
	        System.out.println("=== Test: Verify Successful Session Creation ===");
	        
	        // 1. Login
	        LogIn loginPage = new LogIn(driver);
	        loginPage.performLogin(getProperty("email"), getProperty("password"));
	        
	        // 2. Navigate to My Teams
	        CreateTeam teamPage = new CreateTeam(driver);
	        teamPage.navigateToMyTeams();
	        
	        // 3. Select Pro30 team
	        CreatePlan planPage = new CreatePlan(driver);
	        planPage.selectTeam("Pro30");
	        
	        // 4. Select Pre plan
           CreateSession sessionPage = new CreateSession(driver);
           sessionPage.selectPlan("Pre plan");
      
           CreateDrill drill =  new CreateDrill(driver);
           
           //5. Select Pre Session
           drill.selectSession("Pre Session");
           
           drill.clickAddDrill();
           
           drill.addDrillName("Football");
           
           Select select = new Select(driver.findElement(By.xpath("//select[@name='session_drill_type']")));
           select.selectByVisibleText("Upload a url");
       
           WebElement url = driver.findElement(By.xpath(" //input[@placeholder='Enter video url']"));
           url.sendKeys("https://www.youtube.com/embed/7vq_8aR8thY");
             
           drill.addDuration("5");
           
           drill.addNotes("Notes for Pre Session Drill has been addded");
           
           drill.createDrill();
          
  }
}
