package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClassRgy;
import pageObject.LogIn;
import pageObject.CreateTeam;

public class CreateTeamTest extends BaseClassRgy {
	
    CreateTeam createTeamPage;

    @Test(priority = 1, description = "Verify successful team creation")
    public void verifyCreateTeam() {
        System.out.println("=== Test: Verify Successful Team Creation ===");
        
        LogIn loginPage = new LogIn(driver);
        loginPage.performLogin(getProperty("email"), getProperty("password"));
        
        createTeamPage = new CreateTeam(driver);
        createTeamPage.navigateToMyTeams();
        
        createTeamPage.clickCreateButton();
        createTeamPage.enterTeamDetails("Pro30", "smith", "15-17");
        
        createTeamPage.submitCreateForm();   
       
        boolean isCreated = createTeamPage.isTeamVisibleInListing("Pro30");
        Assert.assertTrue(isCreated, "Team 'Pro30' should be visible in the listing after creation.");
        
        System.out.println("Team creation successful and verified!");
    }
}
