package test;

import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreateDrill;
import pageObject.CreatePlan;
import pageObject.CreateSession;
import pageObject.CreateTeam;
import pageObject.DeleteDrill;
import pageObject.LogIn;
import pageObject.Navigations;
import pageObject.Trash;

public class TrashTest extends BaseClassRgy {

	@Test
	public void trash() throws InterruptedException {
		
	
        LogIn loginPage = new LogIn(driver);
        loginPage.performLogin(getProperty("email"), getProperty("password"));
        
        // Navigate to session and delete the drill first
        CreateTeam teamPage = new CreateTeam(driver);
        teamPage.navigateToMyTeams();
        Thread.sleep(2000);
        
        CreatePlan planPage = new CreatePlan(driver);
        planPage.selectTeam("Pro10");
        Thread.sleep(2000);
        
        CreateSession sessionPage = new CreateSession(driver);
        sessionPage.selectPlan("Pre Rugby Plan");
        Thread.sleep(2000);
        
        CreateDrill drill = new CreateDrill(driver);
        drill.selectSession("Pre Rugby Session");
        Thread.sleep(3000);
        
        // Delete the drill
        DeleteDrill deleteDrill = new DeleteDrill(driver);
        deleteDrill.clickOnThreeDotByName("My Video");
        Thread.sleep(3000);
        deleteDrill.clickDeleteDrillOption("My Video");
        Thread.sleep(3000);
        deleteDrill.removeDrill();
        Thread.sleep(4000);
        
        // Now go to trash to restore it
        Navigations navigation = new Navigations(driver);
        navigation.clickTrash();
        
        Trash trash = new Trash(driver);
        
        trash.gotoDrill();
        Thread.sleep(3000);
        trash.clickRestoreByName("My Video");
        Thread.sleep(3000);
        
       trash.clickRestoreLink();
        
     /*  trash.emptyTrash();
       Thread.sleep(2500);*/
        
      /*  trash.clickDeleteForeverByName("Mental");
        Thread.sleep(2500);
       
      trash.confirmDeleteForever();*/
        Thread.sleep(2500);
        
        // Navigate back to session to verify restored drill
        teamPage.navigateToMyTeams();
        Thread.sleep(2000);
        
        planPage.selectTeam("Pro10");
        Thread.sleep(2000);
        
        sessionPage.selectPlan("Pre Rugby Plan");
        Thread.sleep(2000);
        
        drill.selectSession("Pre Rugby Session");
        Thread.sleep(3000);
        
        // Verify that restored drill is visible
        DeleteDrill verifyDrill = new DeleteDrill(driver);
        boolean isDrillRestored = verifyDrill.isDrillVisibleInListing("My Video");
        org.testng.Assert.assertTrue(isDrillRestored, "Drill 'My Video' was not found in session after restore");
        
        System.out.println("Drill 'My Video' successfully restored and verified in session!");
        
              
	}
}
