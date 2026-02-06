package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CreatePlan {
    
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "plan_create_btn")
    WebElement createPlanBtn;

    @FindBy(name = "name")
    WebElement planNameInput;

    @FindBy(id = "edit_plan_logo")
    WebElement planLogoUpload;

    @FindBy(xpath = "//form[contains(@class,'create-plan-form')]//button[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "//h2[contains(@class, 'text-gray-900')]")
    List<WebElement> planNamesInListing;

    public CreatePlan(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void selectTeam(String teamName) {
        String xpath = "//h2[contains(text(),'" + teamName + "')]/ancestor::div[contains(@class,'bg-primary-100')]//a";
        WebElement teamLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        teamLink.click();
        System.out.println("Selected team: " + teamName);
    }

    public void clickCreatePlan() {
        wait.until(ExpectedConditions.elementToBeClickable(createPlanBtn)).click();
        System.out.println("Clicked on Create Plan button.");
    }

    public void enterPlanName(String name) {
        wait.until(ExpectedConditions.visibilityOf(planNameInput)).sendKeys(name);
        System.out.println("Entered plan name: " + name);
    }

    public void uploadPlanLogo(String filePath) {
    
        planLogoUpload.sendKeys(filePath);
        System.out.println("Uploaded plan logo from: " + filePath);
    }

    public void clickSubmit() {
        submitBtn.click();
        System.out.println("Submitted the create plan form.");
    }

    public boolean isPlanVisible(String planName) {
      
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        
        for (WebElement element : planNamesInListing) {
            String text = element.getText().trim();
            if (text.equalsIgnoreCase(planName)) {
                return true;
            }
        }
        return false;
    }
}
