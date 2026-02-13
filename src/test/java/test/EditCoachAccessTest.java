package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.EditCoachAccess;
import pageObject.LogIn;
import pageObject.Navigations;

public class EditCoachAccessTest extends BaseClassRgy {

	@Test
	public void editCoachAccess() throws InterruptedException {
		
		    LogIn loginPage = new LogIn(driver);
	        loginPage.performLogin(getProperty("email"), getProperty("password"));
	        
	        Navigations navigation = new Navigations(driver);
	        
	        navigation.clickInviteCoaches();
	        
	        EditCoachAccess access = new EditCoachAccess(driver);
	        Thread.sleep(3000);
	        access.clickTeamByName(driver, "Pro10");
	        
	        Select roleDropdown = new Select(driver.findElement(By.id("role"))); 
	        Thread.sleep(3000);
            roleDropdown.selectByVisibleText("Add Video Only");
            
            access.editRole();
            

	}
}
