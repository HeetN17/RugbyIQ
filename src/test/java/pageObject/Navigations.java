package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigations {

	WebDriver driver;
	WebDriverWait wait;
	
    public Navigations(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath="//span[normalize-space()='Home']")
    WebElement optionHome;
    
    @FindBy(xpath=" //span[normalize-space()='My Teams']")
    WebElement optionTeam ;
    
    @FindBy(xpath="//span[normalize-space()='My Plans']")
    WebElement optionPlan ;
    
    @FindBy(xpath=" //span[normalize-space()='My Drills']")
    WebElement optionMyDrill;
    
    @FindBy(xpath=" //span[normalize-space()='Vault Drills']")
    WebElement optionVaultDrill;
    
    @FindBy(xpath=" //span[normalize-space()='Invite Coaches']")
    WebElement optionInviteCoaches;
    
    @FindBy(xpath=" //span[normalize-space()='Subscription']")
    WebElement optionSubscription;
    
    @FindBy(xpath=" //span[normalize-space()='My subscription']")
    WebElement optionMySubscription;
    
    @FindBy(xpath="//span[normalize-space()='Playbook']")
    WebElement optionPlayBook;
    
    @FindBy(xpath="  //span[normalize-space()='Trash']")
    WebElement optionTrash;
    
    public void clickHome() {
    	
   	 wait.until(ExpectedConditions.elementToBeClickable(optionHome)).click();
   }
    
    
    public void clickTeams() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionTeam)).click();
      }
    
    
    
    public void clickPlans() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionPlan)).click();
      }
    
    
    
    public void clickMyDrill() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionMyDrill)).click();
      }
    
    
    public void clickVaultDrill() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionVaultDrill)).click();
      }
    
    
    public void clickInviteCoaches() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionInviteCoaches)).click();
      }
    
    
    public void clickSubscription() {
    	
      	 wait.until(ExpectedConditions.elementToBeClickable(optionSubscription)).click();
      }
    
    public void clickMySubscription() {
    	
     	 wait.until(ExpectedConditions.elementToBeClickable(optionMySubscription)).click();
     }
    
    public void clickPlayBook() {
    	
     	wait.until(ExpectedConditions.elementToBeClickable(optionPlayBook)).click();
     }
    
public void clickTrash() {
    	
     	wait.until(ExpectedConditions.elementToBeClickable(optionTrash)).click();
     }


}
