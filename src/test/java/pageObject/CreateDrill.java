package pageObject;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateDrill {

	WebDriver driver;
	WebDriverWait wait;
	
    public CreateDrill(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void selectSession(String sessionName) {
     
        String xpath = "//*[normalize-space()='" + sessionName + "']/ancestor::div[contains(@class,'bg-primary-100')]//a[descendant-or-self::*[normalize-space()='" + sessionName + "']]";
        WebElement sessionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        sessionLink.click();
        System.out.println("Selected session: " + sessionName);
    }
    
    @FindBy(xpath = "//button[@id='drill_create_btn']")
    WebElement btnAddDrill;
    
    @FindBy(xpath = "//label[contains(text(),'Duration')]/..//input")
    WebElement txtDuration;
    
    @FindBy(xpath = "//label[contains(text(),'Notes')]/..//textarea")
    WebElement txtNotes;
    
    @FindBy(xpath = "//label[contains(text(),'Drill Name')]/..//input")
    WebElement txtDrillName;
    
    @FindBy(xpath = "//button[contains(text(),'Create')]")
    WebElement btnCreate;
    
    public void clickAddDrill() {
    	
    	 wait.until(ExpectedConditions.elementToBeClickable(btnAddDrill)).click();
    }
    
    public void addDrillName(String drillName) {
    	
   	 wait.until(ExpectedConditions.visibilityOf(txtDrillName)).sendKeys(drillName);
   }
    
    public void addDuration(String duration) {
    	
    	 wait.until(ExpectedConditions.visibilityOf(txtDuration)).sendKeys(duration);
    }
    
    public void addNotes(String notes) {
    	
    	 wait.until(ExpectedConditions.visibilityOf(txtNotes)).sendKeys(notes);
    }
    
    public void createDrill() {
    	
   	 wait.until(ExpectedConditions.elementToBeClickable(btnCreate)).click();
   	 
   }
    
}
