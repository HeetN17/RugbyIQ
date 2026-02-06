package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSession {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "session_create_btn")
    WebElement btnCreateSession;

    @FindBy(name = "name")
    WebElement txtSessionName;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    WebElement btnCreate;

    @FindBy(xpath = "//h2[contains(@class,'text-gray-900')]")
    List<WebElement> sessionNamesInListing;
   
    public CreateSession(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void selectPlan(String planName) {
        String xpath = "//h2[contains(text(),'" + planName + "')]/ancestor::div[contains(@class,'bg-primary-100')]//a";
        WebElement planLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        planLink.click();
        System.out.println("Selected plan: " + planName);
    }

    public void clickCreateSession() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCreateSession)).click();
        System.out.println("Clicked on Create Session button.");
    }

    public void enterSessionName(String session) {
        wait.until(ExpectedConditions.visibilityOf(txtSessionName)).sendKeys(session);
        System.out.println("Entered session name: " + session);
    }

    public void submitSession() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCreate)).click();
        System.out.println("Submitted the create session form.");
    }

    public boolean isSessionVisible(String sessionName) {
    	
        try { Thread.sleep(3000); }
        
        catch (InterruptedException e) {}
            
        for (WebElement element : sessionNamesInListing) {
            String text = element.getText().trim();
            if (text.equalsIgnoreCase(sessionName)) {
                return true;
            }
        }
        return false;
    }
}
