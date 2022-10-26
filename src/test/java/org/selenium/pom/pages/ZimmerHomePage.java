package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;
import org.apache.log4j.Logger;

/*
 * 
 */
public class ZimmerHomePage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerHomePage.class);

	Functions ptr;
	// Headers section

	@FindBy(xpath = "(//a[contains(text(),'Find a Doctor')])[1]")
	WebElement findADoclink1;

//	@FindBy(xpath = "(//a[contains(text(),'Careers')])[1]")
//	WebElement careers;
	
	/*
	 * String headerLinks = "(//a[.='%s'])[1]";
	 * String.format(headerLinks, linkName)
	 * here format function will replace the %s with input string i.e. linkName
	 */

	String headerLinks = "(//a[.='%s'])[1]";
	String careerHeader = "div[class*='cmp-container'] div[class*='title']:first-child div h4[class*='white']";

	@FindBy(xpath = "(//a[contains(text(),'Investors')])[1]")
	WebElement Investors;

	@FindBy(xpath = "(//a[contains(text(),'ç Us')])[1]")
	WebElement contact_Us;

	@FindBy(xpath = "(//a[contains(text(),'Patients')])[1]")
	WebElement patients;

	@FindBy(xpath = "(//a[contains(text(),'Find a Rep')])[1]")
	WebElement findARep;

	@FindBy(xpath = "(//a[contains(text(),'US')])[1]")
	WebElement US;

	// (//a[contains(text(),'US')])[1]

	public ZimmerHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		ptr = new Functions(driver);

	}

	public ZimmerHomePage load() {
		load("/");
		return this;
	}

	public void verifyPageTitle(String title) {
		try {
			Assert.assertEquals(driver.getTitle(), title, "Faild : page title not matched");
			log.info("Title is verifed : " + title);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void verifyHeaderLink(String linkName) {
		try {
			Assert.assertEquals(ptr.waitForElement(By.xpath(String.format(headerLinks, linkName))).isDisplayed(), true,
					"Failed : " + linkName + " is not displayed");
			log.info("Link is displayed : " + linkName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
	public String getColorName(String eleLocator) {
		String hex = Color.fromString(ptr.waitForElement(By.cssSelector(eleLocator)).getCssValue("color")).asHex();
	    String name = "";
	    switch (hex.toLowerCase()) {
	        case "#ff0000":
	            name = "Red";
	            break;
	        case "#00ff00":
	            name = "Green";
	            break;
	        case "#0000ff":
	            name = "Blue";
	            break;
	        case "#ffffff":
	            name = "White";
	            break;
	        case "#000000":
	            name = "Black";
	            break;
//	        case "#000000":
//	            name = "Black";
//	            break;
	        default:
	            name = "Unknown";
	        break;
	    }
	    return name;
	}

	public void navigateAndVerifyHeaderLinkTitle(String linkName) throws InterruptedException {
		try {
			ptr.click(By.xpath(String.format(headerLinks, linkName)));
			ptr.waitforTitlepresent(linkName, 5);
			log.info("Title is verifed : " + linkName);
			Assert.assertEquals(ptr.getVisibleText(By.cssSelector(careerHeader)), linkName,
					"Faild : page header not matched");
			log.info("Header is displayed : " + linkName);
			Assert.assertEquals(getColorName(careerHeader),"White","Failed: font color not matched");
			log.info("Font color is "+getColorName(careerHeader));
		
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
