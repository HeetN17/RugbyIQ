package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteDrill {

	WebDriver driver;
    WebDriverWait wait;
    
    public DeleteDrill(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath=" //a[normalize-space()='Yes Remove']")
    WebElement removeDrill;
    
    @FindBy(xpath = "//h3[contains(@class, 'text-gray-900')]")
    List<WebElement> DrillNamesInListing;
    
    public void clickOnThreeDotByName(String drillName) {

        String xpath = "//h3[normalize-space()='" + drillName + "']" +
                       "/ancestor::div[contains(@class,'border')]" +
                       "//button[contains(@class,'session-drill-options')]";

        WebElement threeDot = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", threeDot);

        try {
            threeDot.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", threeDot);
        }

        System.out.println("Clicked on three-dot menu for drill: " + drillName);
    }
    
    public void clickDeleteDrillOption(String drillName) {
    	
        WebElement deleteBtn = driver.findElement(
            By.xpath("//h3[contains(text(),'" + drillName + "')]/ancestor::div[contains(@class,'sortable-item')]//a[contains(@class,'delete_drill')]"));
        
        deleteBtn.click();
    }
    
    public void removeDrill() {
    	
    	wait.until(ExpectedConditions.visibilityOf(removeDrill)).click();
    }
    
    public boolean isDrillNotVisibleInListing(String drillName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.visibilityOfAllElements(DrillNamesInListing));

        for (WebElement element : DrillNamesInListing) {
            if (element.getText().trim().equalsIgnoreCase(drillName)) {
                return false;   
            }
        }
        return true;
    }
    
    public boolean isDrillVisibleInListing(String drillName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Try specific locator first
            try {
                wait.until(ExpectedConditions.visibilityOfAllElements(DrillNamesInListing));
                for (WebElement element : DrillNamesInListing) {
                    if (element.getText().trim().equalsIgnoreCase(drillName)) {
                        return true;   
                    }
                }
            } catch (Exception e) {
                // If specific locator fails, try a direct text search
                By directTextLocator = By.xpath("//*[contains(text(),'" + drillName + "')]");
                List<WebElement> elements = driver.findElements(directTextLocator);
                for (WebElement element : elements) {
                    if (element.isDisplayed()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
