package test;

import org.testng.annotations.Test;

import baseClass.BaseClassRgy;
import pageObject.CreatePlayBook;
import pageObject.DeletePlayBook;
import pageObject.EditPlayBook;
import pageObject.LogIn;

public class DeletePlayBookTest extends BaseClassRgy{

	@Test(priority=1,description="Remove Playbook")
	public void removePlayBook() throws InterruptedException {
		
	    LogIn loginPage = new LogIn(driver);
	    loginPage.performLogin(getProperty("email"), getProperty("password"));
	    
	    CreatePlayBook create = new CreatePlayBook(driver);
	    create.clickPlayBookOption();
	    
	    EditPlayBook edit = new EditPlayBook(driver);
	    edit.clickThreeDot(getProperty("editedPlayBook"));
	    Thread.sleep(4000);
	    
	   DeletePlayBook delete = new DeletePlayBook(driver);
	   delete.clickDeleteOption(getProperty("editedPlayBook"));
	   Thread.sleep(3000);
	   
	   delete.confirmRemove();
	   	    
	}
}
