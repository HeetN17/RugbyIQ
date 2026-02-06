package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.LogIn;

public class LogIN extends BaseClassRgy {
    
    private LogIn loginPage;
    
    @BeforeMethod
    public void initializePage() {
        loginPage = new LogIn(driver);
    }
    
    @Test(priority = 1, description = "Verify login page is displayed")
    public void verifyLoginPageDisplayed() {
        System.out.println("=== Test: Verify Login Page Displayed ===");
        
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), 
            "Login button should be displayed on the login page");
        
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), 
            "URL should contain 'login'. Actual URL: " + currentUrl);
        
        System.out.println("Login page verification passed!");
    }
    
    @Test(priority = 2, description = "Verify successful login with valid credentials")
    public void verifySuccessfulLogin() {
        System.out.println("=== Test: Verify Successful Login ===");
        
       
        String email = getProperty("email");
        String password = getProperty("password");
        
      
        loginPage.performLogin(email, password);
        
      
        try {
            Thread.sleep(3000); // Allow time for redirect
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = loginPage.getCurrentUrl();
        
        Assert.assertFalse(currentUrl.contains("login"), 
            "After successful login, URL should not contain 'login'. Current URL: " + currentUrl);
        
        System.out.println("Login successful! Redirected to: " + currentUrl);
    }
    
    @Test(priority = 3, description = "Verify login fails with invalid credentials", enabled = false)
    public void verifyLoginWithInvalidCredentials() {
        System.out.println("=== Test: Verify Login with Invalid Credentials ===");
             
        String invalidEmail = "invalid@test.com";
        String invalidPassword = "WrongPassword123";
        
        loginPage.performLogin(invalidEmail, invalidPassword);
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), 
            "With invalid credentials, should remain on login page. Current URL: " + currentUrl);
        
        System.out.println("Invalid login test passed - remained on login page.");
    }
    
    @Test(priority = 4, description = "Verify login with empty credentials", enabled = false)
    public void verifyLoginWithEmptyCredentials() {
    	
        System.out.println("=== Test: Verify Login with Empty Credentials ===");
        
   
        loginPage.performLogin("", "");
        
      
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
       
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), 
            "With empty credentials, should remain on login page. Current URL: " + currentUrl);
        
        System.out.println("Empty credentials test passed - remained on login page.");
    }
}
