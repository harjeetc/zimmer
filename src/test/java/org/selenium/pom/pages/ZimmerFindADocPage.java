package org.selenium.pom.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

/*
 * 
 */
public class ZimmerFindADocPage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerFindADocPage.class);

	Functions ptr;

	public ZimmerFindADocPage(WebDriver driver) {

		super(driver);
		ptr = new Functions(driver);
		// TODO Auto-generated constructor stub
	}

	By findADoc = By.xpath("//a[@class='link link--blank '][normalize-space()='Find a Doctor']");
	By locationTextBox = By.cssSelector("#location");
	By radiusTextBox = By.cssSelector("#radius");
	By docTypes = By.xpath("//ul[@justify='content-between']/li");
	By findADocter = By.xpath("//span[.='Find a Doctor']/..");

	/*
	 * String headerLinks = "(//a[.='%s'])[1]"; String.format(headerLinks, linkName)
	 * here format function will replace the %s with input string i.e. linkName
	 */

	String headerLinks = "//div[contains(@class,'header')]//a[contains(.,'%s')]";
	String footerLinks = "//div[contains(@class,'footer')]//a[contains(.,'%s')]";

	By careerHeader = By
			.cssSelector("div[class*='cmp-container'] div[class*='title']:first-child div h1[class*='white']");
	String privacyPolicyHeader = "div[class*='cmp-container'] h1";
	String legalNoticeHeader = "div[class*='cmp-container'] h2";
	String navLinkHeader = "//span[contains(.,'%s')]/..";
	String buttons = "//button[.='%s']";
	By closePopup = By.cssSelector("div[aria-label='Company Logo']+button[class*='close']");
	By openPreferencesButton = By.cssSelector("button[aria-label='Open Preferences']");
	By headerLinkes = By.cssSelector("nav[class*='navigation--utility'] a");
	By footerLinkes = By.cssSelector("div[class*='global-footer--primary-container'] ul[label='footer'] a");
	By socialMediaLinkes = By.cssSelector("div[class*='global-footer--primary-container'] ul[class*='social-media'] a");
	By globalSearchTextBox = By.id("global-search");
	By globalSearchButton = By.cssSelector(
			"div[class='container container--default grid grid__gap--none grid__breakpoint--nobreak global-header__row-bottom'] button[aria-label='Site search']");

	/*
	 * Search elements
	 */
	By searchResultTerm = By.cssSelector("label[for='site-search__term']");
	//// h3[normalize-space()='No search term provided.']
	By noSearchResults = By.xpath("//h3[normalize-space()='No search term provided.']");

	By images = By.tagName("img");
	String playButton = "//*[.='%s']/../../../..//button";
	String playLink = "//*[.='%s']/../../../../..//a[@aria-label='Opens video in modal']";

	String closeVideoPlayer = "//div[contains(@id,'%s')  and contains(@class,'card')]/../../../../..//button[contains(@class,'modal-close')]";
	String videoPlayer = "div[id*='%s'][class*='card'] video";
	By siteLink = By.cssSelector("nav[class*='navigation--utility'] a[href*='en/site']");
	String countryButtons = "(//div[.='%s'])[1]";
	By differentCountryPopup = By.xpath("//*[.='We noticed that you are visiting from a different country']/..");
	By differentCountryPopupMessage = By.xpath("//*[.='We noticed that you are visiting from a different country']");

	By searchCards = By.cssSelector("a[class*='card'] div[class*='link-heading']");
	By patientsTab = By.xpath("//a[.='Patients']");
	By medicalProfessionalsCards = By.xpath("//a[.='Medical Professionals']");
	String productLink = "//a[contains(.,'%s')]";
	By productLinkHeader = By.xpath("(//h1[contains(@class,'heading')])[last()]");
	By closeCookie = By.cssSelector("div[id*='close'] button[aria-label='Close']");
	By doctorTagList = By.cssSelector("div[class*='find-a-doctor__list'] footer");
	By findDoctorSpinner = By
			.cssSelector("div[class*='results aem']> div[class*='find-a-doctor find-a-doctor--results patients']");
	By noResult = By.cssSelector("div[class*='find-a-doctor__no-results-message']");
	By findDoctorTypeError = By.cssSelector("span[color='error']");
	By locationError = By.xpath("//input[@id='location']/..");

	By previousResults = By.cssSelector("button[aria-label='previous results'] > *");
	By nextResultsButton = By.cssSelector("button[aria-label='next results']");
	By paginationCount = By.cssSelector(".pagination__count");
	By activePage = By.cssSelector("div[class*='pagination__navigation'] a[class*='active']");
	By allPage = By.cssSelector("div[class*='pagination__navigation'] a");
	By backLink = By.cssSelector("a[class*='back']");

	// Please choose a treatment type.
	public ZimmerFindADocPage load() {
		load(" ");
		return this;
	}

	public ZimmerFindADocPage loadSwitch() {
		load("switch");
		return this;
	}
	/*
	 * Step to validate the find a doc flow with radius and location
	 */

	private void waitForFindDoctorLoader() {

		while (ptr.getAttribute(findDoctorSpinner, "class").contains("loading")) {
			log.info("loading");
			ptr.delay(1);

		}
	}

	@Step("Verify Pagination")
	public void verifyPagination(int defaultPageNumber) {
		try {
			List<String> expPaginations = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

			
			new  ZimmerHomePage(driver).verifySearch("global", "", "Knee", "");

			ptr.scrollPage(nextResultsButton);
			Assert.assertTrue(ptr.getVisibleText(paginationCount).contains("1-10 of"));
			Allure.step("Pagination count contains 1-10 of");
			// Assert.assertFalse(ptr.getElement(previousResults).isEnabled());
			Assert.assertTrue(ptr.getElement(nextResultsButton).isEnabled());
			Allure.step("Next button is enabled");
			Assert.assertEquals((int) Integer.valueOf(ptr.getVisibleText(activePage)), defaultPageNumber);
			Allure.step("Default pagination number is 1");
			/*
			 * collectors is a class and list is a static function .. takeing all the
			 * elements storeing in a list
			 */

			List<String> actPaginations = ptr.getElements(allPage).stream().map(ele -> ele.getText())
					.collect(Collectors.toList());
			Assert.assertEquals(actPaginations, expPaginations);
			Allure.step("Default pagination numbers are " + actPaginations);

			ptr.click(nextResultsButton, "Next Results Button");
			ptr.delay(5);
			ptr.scrollPage(nextResultsButton);
			// taking the active paginations number after next is clicked ( which would be
			// 2)
			int activePageNumber = Integer.valueOf(ptr.getVisibleText(activePage));
			// then subtracting with the number should be 1
			Assert.assertEquals(activePageNumber - defaultPageNumber, 1);
			Allure.step("Moved to next page");
			Assert.assertTrue(ptr.getElement(previousResults).isEnabled());
			Allure.step("Previous button is enabled");

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verify No Doctor type selected error")
	public void verifyNoDoctorTypeError(String errorMessage) {
		try {

			ptr.click(findADoc, "Find a Doctor Tab");
			ptr.click(findADocter, "Find a Doctor Button");
			Assert.assertEquals(ptr.getVisibleText(findDoctorTypeError), errorMessage);
			ptr.highlighElement(findDoctorTypeError);
			Allure.step("Error message captured : " + errorMessage);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * /What does this script variable do?
	 * 
	 */

	@Step("Verify No Location entred error")
	public void verifyNoLocationError(String errorCode) {
		try {

			ptr.click(findADoc, "Find a Doctor Tab");
			// ptr.clear(locationTextBox);
			ptr.type(locationTextBox, "", "");
			// ptr.pressTabAndEnter(locationTextBox);
			ptr.click(findADocter, "Find a Doctor Button");
			ptr.delay(2);
			Assert.assertTrue(ptr.getAttribute(locationError, "class").contains("error"));

			Allure.step("Error is visible for blank location");
			ptr.highlighElement(locationTextBox);
			// Assert.assertEquals(getColorName(locationError, "color"), errorColor,
			// "Failed: font color not matched");
			String script = "return window.getComputedStyle(document.querySelector('.input.field.input-search.input--default.field__error'),':before').getPropertyValue('color')";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String content = (String) js.executeScript(script);
			if (Color.fromString(content).asHex().equals("#dd3f64"))
				content = "error";
			Assert.assertEquals(content, errorCode, "Failed: font color not matched");
			log.info("Error color is " + content);
			Allure.step("Error color is " + content);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verify find a doc Types in location and radius")
	public void findADoctor(String docType, String location, String radius) {
		try {

			ptr.click(findADoc, "Find a Doctor Tab");
			log.info("clicked Find a Doctor Tab Link in header");

			ptr.getElements(docTypes).stream().forEach(ele -> {

				if (ele.getText().trim().equalsIgnoreCase(docType)) {
					ptr.highlighElement(ele);
					ele.click();
					Allure.step(docType + " is selected");
					log.info(docType + " is selected");
				} else {
					log.info("NO doc type found");
					Allure.step("NO doc type found :");
				}

			});

			ptr.type(locationTextBox, location, "Location");
			ptr.type(radiusTextBox, radius, "Radius");
			ptr.click(findADocter, "Find a Doctor");

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verify doctors list should be more than zero")
	public void varifyDoctorList() {
		try {
			waitForFindDoctorLoader();
			Assert.assertTrue(ptr.getElementsSize(doctorTagList) > 0);
			Allure.step("Docters found : " + ptr.getElementsSize(doctorTagList));
			log.info("Doctors found :  " + ptr.getElementsSize(doctorTagList));

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
			//test
		}
	}

}
