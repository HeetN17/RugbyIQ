package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CreateTeam {
	
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[text()='My Teams']")
    WebElement myTeamsMenu;

    @FindBy(id = "create_team_btn")
    WebElement createTeamBtn;

    @FindBy(name = "name")
    WebElement teamNameInput;

    @FindBy(name = "coach_name")
    WebElement coachNameInput;

    @FindBy(name = "age_group")
    WebElement ageGroupInput;

    @FindBy(xpath = "//form[@id='create-team-form']//button[@type='submit']")
    WebElement finalCreateBtn;
    
    @FindBy(xpath = "//h2[contains(@class, 'text-gray-900')]")
    List<WebElement> teamNamesInListing;

    public CreateTeam(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToMyTeams() {
        wait.until(ExpectedConditions.elementToBeClickable(myTeamsMenu)).click();
        System.out.println("Navigated to My Teams page.");
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createTeamBtn)).click();
        System.out.println("Clicked on Create Team button.");
    }

    public void enterTeamDetails(String name, String coach, String age) {
        wait.until(ExpectedConditions.visibilityOf(teamNameInput)).sendKeys(name);
        coachNameInput.sendKeys(coach);
        ageGroupInput.sendKeys(age);
        System.out.println("Entered team details: " + name + ", " + coach + ", " + age);
    }

    public void submitCreateForm() {
        finalCreateBtn.click();
        System.out.println("Submitted the create team form.");
    }

    public boolean isTeamVisibleInListing(String teamName) {
       
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        
        for (WebElement element : teamNamesInListing) {
            if (element.getText().trim().equalsIgnoreCase(teamName)) {
                return true;
            }
        }
        return false;
    }
}
