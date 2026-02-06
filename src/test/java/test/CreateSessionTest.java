package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreatePlan;
import pageObject.CreateSession;
import pageObject.CreateTeam;
import pageObject.LogIn;

public class CreateSessionTest extends BaseClassRgy {

	  @Test(priority = 1, description = "Verify successful session creation")
	    public void verifyCreateSession() {
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
        
            // 5. Click on Create Session Button
            sessionPage.clickCreateSession();
        
             // 6. Add Session Name
             sessionPage.enterSessionName("Pre Session");
        
              // 7. Submit Session
              sessionPage.submitSession();
        
              // 8. Verify
             boolean isVisible = sessionPage.isSessionVisible("Pre Session");
             Assert.assertTrue(isVisible, "Session 'Pre Session' should be visible in the listing after creation.");
        
        System.out.println("Session creation successful and verified!");
    }
}
