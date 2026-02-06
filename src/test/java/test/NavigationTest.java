package test;

import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreateTeam;
import pageObject.LogIn;
import pageObject.LogOut;
import pageObject.Navigations;

public class NavigationTest extends BaseClassRgy{

	 @Test(priority = 1, description = "Verify successful session creation")
	    public void verifyNavigations() {
	        System.out.println("=== Test: Verify Successful Session Creation ===");
	        
	        // 1. Login
	        LogIn loginPage = new LogIn(driver);
	        loginPage.performLogin(getProperty("email"), getProperty("password"));
	        
	        // 2. Navigate to My Teams
	        CreateTeam teamPage = new CreateTeam(driver);
	        teamPage.navigateToMyTeams();
	        
	        Navigations navigation = new Navigations(driver);
	        
	        navigation.clickHome();
	        
	        navigation.clickTeams();
	        
	        navigation.clickPlans();
	        
	        navigation.clickVaultDrill();
	        
	        navigation.clickMyDrill();
	        
	        navigation.clickSubscription();
	        
	        navigation.clickMySubscription();
	        
	        navigation.clickInviteCoaches();
	        
	        navigation.clickPlayBook();
	        
	        navigation.clickTrash();
	        
	        LogOut logout = new LogOut(driver);
	        logout.performLogout();
	        
	        		
	        
	 }
}
