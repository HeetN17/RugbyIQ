package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseClass.BaseClassRgy;
import pageObject.LogOut;

public class LogOUT extends BaseClassRgy {
    LogOut logoutPage;

    @Test(priority = 1, description = "Verify successful logout")
    public void verifyLogout() {
        System.out.println("=== Test: Verify Successful Logout ===");
        
        pageObject.LogIn loginPage = new pageObject.LogIn(driver);
        loginPage.performLogin(getProperty("email"), getProperty("password"));
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
               
        logoutPage = new LogOut(driver);
        logoutPage.performLogout();
        
     
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), 
            "After logout, URL should contain 'login'. Current URL: " + currentUrl);
        
        System.out.println("Logout successful! Returned to: " + currentUrl);
    }
}
