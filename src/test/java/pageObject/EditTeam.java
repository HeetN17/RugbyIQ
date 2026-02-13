package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class EditTeam {
    
    WebDriver driver;
    WebDriverWait wait;

    public EditTeam(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = " //button[normalize-space()='Update']")
    WebElement teamNameInput;

    @FindBy(id = "update_team_logo")
    WebElement uploadTeamLogoInput;
    
    @FindBy(xpath = " //button[normalize-space()='Update']")
    WebElement updateBtn;

    @FindBy(xpath = "//*[contains(text(), 'Edit')]")
    List<WebElement> editOptions;
    
    @FindBy(css = "input.name[name='name']")
    WebElement txtEditTeam;
   
    public void clickThreeDots(String teamName) {
    	
         String xpath = "//h2[contains(normalize-space(.), '" + teamName + "')]/ancestor::div[contains(@class, 'rounded')]//button";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait for menu to open
        System.out.println("Clicked on three dots for team: " + teamName);
    }

    public void clickEdit() {
        
        System.out.println("Current URL before Edit click: " + driver.getCurrentUrl());
        
        WebElement targetEdit = null;
        for (WebElement opt : editOptions) {
            if (opt.isDisplayed()) {
                targetEdit = opt;
                break;
            }
        }
        
        if (targetEdit == null && !editOptions.isEmpty()) {
            targetEdit = editOptions.get(editOptions.size() - 1); // Try the last one if none are 'visible'
        }
        
        if (targetEdit != null) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(targetEdit)).click();
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JS click.");
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", targetEdit);
            }
        } else {
            System.out.println("No Edit option found in DOM!");
        }
        
        System.out.println("Clicked on Edit option. Current URL after: " + driver.getCurrentUrl());
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    public void editTeamName(String teamName) {

        wait.until(ExpectedConditions.visibilityOf(txtEditTeam));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", txtEditTeam);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(txtEditTeam)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", txtEditTeam);
        }

        // React-safe clear
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].value='';", txtEditTeam);

        txtEditTeam.sendKeys(teamName);

        System.out.println("Team name updated successfully");
    }

    public void clickDeleteIcon() {

        By deleteBtn = By.cssSelector("a.delete_image");

        WebElement deleteIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(deleteBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", deleteIcon);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
        } catch (Exception e) {
            System.out.println("Normal click failed, using JS click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteIcon);
        }

        System.out.println("Clicked delete icon successfully");
    }


    public void clickUpdate() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(updateBtn));
            updateBtn.click();
        } catch (Exception e) {

            System.out.println("Normal click failed, using JS click");

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", updateBtn);
        }

        System.out.println("Clicked Update button successfully");
    }
    
    public void uploadTeamLogo(String filePath) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.height='1px'; arguments[0].style.width='1px';",
                uploadTeamLogoInput);

        uploadTeamLogoInput.sendKeys(filePath);

        System.out.println("Team logo uploaded");
    }
  

}
