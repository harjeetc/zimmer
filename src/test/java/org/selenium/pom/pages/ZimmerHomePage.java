package org.selenium.pom.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;


/*
 * 
 */
public class ZimmerHomePage extends BasePage {
	
	//Headers section 
   
	@FindBy(xpath="(//a[contains(text(),'Find a Doctor')])[1]")
	WebElement findADoclink1;
	
	@FindBy(xpath="(//a[contains(text(),'Careers')])[1]")
	WebElement careers;
	
	@FindBy(xpath="(//a[contains(text(),'Investors')])[1]")
	WebElement Investors;
	
	@FindBy(xpath="(//a[contains(text(),'ç Us')])[1]")
	WebElement contact_Us;
	
	@FindBy(xpath="(//a[contains(text(),'Patients')])[1]")
	WebElement patients;
	
	@FindBy(xpath="(//a[contains(text(),'Find a Rep')])[1]")
	WebElement findARep;
	
	@FindBy(xpath="(//a[contains(text(),'US')])[1]")
	WebElement US;
	
	
	
	//(//a[contains(text(),'US')])[1]
	
	
	
    public ZimmerHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    
    }

  
    public ZimmerHomePage load(){
        load("/");
        return this;
    }

    
   public void clickFindDoc() {
	   findADoclink1.click();
   }
}
