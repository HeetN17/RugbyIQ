package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LogOut {
    
    WebDriver driver;
    WebDriverWait wait;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "dropdownDefaultButton")
    private WebElement profileDropdown;

    @FindBy(xpath = "//a[text()='Log Out']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[@id='popup-logout']//a[contains(text(), \"Yes, I'm sure\")]")
    private WebElement confirmLogoutButton;

    @FindBy(id = "toast-success")
    private WebElement successToast;

    public void performLogout() {
       
        try {
            wait.until(ExpectedConditions.invisibilityOf(successToast));
        } catch (Exception e) {
            System.out.println("Toast did not disappear within timeout.");
        }

       
        try {
            wait.until(ExpectedConditions.elementToBeClickable(profileDropdown));
            profileDropdown.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("Click intercepted by toast, trying JavaScript click.");
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", profileDropdown);
        }
        System.out.println("Clicked on profile dropdown.");

        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
        System.out.println("Clicked on Log Out link.");

        wait.until(ExpectedConditions.elementToBeClickable(confirmLogoutButton));
        confirmLogoutButton.click();
        System.out.println("Confirmed logout.");
    }
}
