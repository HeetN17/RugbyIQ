package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreateMyDrill;
import pageObject.LogIn;

public class CreateMyDrillTest extends BaseClassRgy {

	@Test(priority=1,description="Create My Drill")
	public void createMyDrill() throws InterruptedException {
		
        LogIn loginPage = new LogIn(driver);
        loginPage.performLogin(getProperty("email"), getProperty("password"));
        
        CreateMyDrill drill = new CreateMyDrill(driver);
        Thread.sleep(2000);
        drill.clickMyDrillOption();
        
        Thread.sleep(2000);
        drill.clickCreate();
        
        Thread.sleep(3000);
        drill.enterDrillName(getProperty("myDrillName"));
        
        Select selectDrop = new Select(driver.findElement(By.xpath("//select[@name='session_drill_type']")));  
        Thread.sleep(3000);
        selectDrop.selectByVisibleText("Upload a url");
        
        drill.enterURL(getProperty("drillURL"));
        drill.enterNotes(getProperty("drillNoes"));
        Thread.sleep(3000);
        
        drill.clickCreateBtn();
        Thread.sleep(3000);
        
        drill.isMyDrillVisible(getProperty("myDrillName"));      
        
	}
}
