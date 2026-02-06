package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClassRgy;
import pageObject.LogIn;
import pageObject.CreateTeam;
import pageObject.CreatePlan;

public class CreatePlanTest extends BaseClassRgy {
    
    @Test(priority = 1, description = "Verify successful plan creation within a team")
    public void verifyCreatePlan() {
        System.out.println("=== Test: Verify Successful Plan Creation ===");
        
        // 1. Login
        LogIn loginPage = new LogIn(driver);
        loginPage.performLogin(getProperty("email"), getProperty("password"));
        
        // 2. Navigate to My Teams
        CreateTeam teamPage = new CreateTeam(driver);
        teamPage.navigateToMyTeams();
        
        // 3. Select Pro30 team
        CreatePlan planPage = new CreatePlan(driver);
        planPage.selectTeam("Pro30");
        
        // 4. Click Create Plan
        planPage.clickCreatePlan();
        
        // 5. Fill Plan Details
        planPage.enterPlanName("Pre plan");
        planPage.uploadPlanLogo("C:\\Users\\INX\\Downloads\\rugby.jpg");
        
        // 6. Submit
        planPage.clickSubmit();
        
        // 7. Verify
        boolean isVisible = planPage.isPlanVisible("Pre plan");
        Assert.assertTrue(isVisible, "Plan 'Pre plan' should be visible in the listing after creation.");
        
        System.out.println("Plan creation successful and verified!");
    }
}
