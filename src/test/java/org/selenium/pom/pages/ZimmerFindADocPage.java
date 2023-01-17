package org.selenium.pom.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.Color;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;
import org.testng.TestException;

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

	String buttons = "//button[.='%s']";

	By globalSearchButton = By.cssSelector(
			"div[class='container container--default grid grid__gap--none grid__breakpoint--nobreak global-header__row-bottom'] button[aria-label='Site search']");

	By images = By.tagName("img");

	By searchCards = By.cssSelector("a[class*='card'] div[class*='link-heading']");
	By patientsTab = By.xpath("//a[.='Patients']");
	By medicalProfessionalsCards = By.xpath("//a[.='Medical Professionals']");
	String productLink = "//a[contains(.,'%s')]";
	By productLinkHeader = By.xpath("(//h1[contains(@class,'heading')])[last()]");
	By closeCookie = By.cssSelector("div[id*='close'] button[aria-label='Close']");
	By doctorTagList = By.cssSelector("div[class*='find-a-doctor__list'] footer");

	By doctorList = By.cssSelector("div[class*='find-a-doctor__list'] header a[class*='doctor']");

	By doctorLanguage = By.xpath("//h6[contains(.,'Languages')]/..//p");

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

	/**
	 * Locators for Filter
	 */

	String filterCheckBox = "//label[contains(.,'%s')]/..";

	String filterChkBox = "//label[contains(.,'%s')]/..//input";
	String filterLabel = "//label[contains(.,'%s')]";
	String filterButton = "//button[contains(.,'%s')]";
	By docterListFooter = By.xpath("//div[contains(@class,'find-a-doctor__list')]//footer");
	String docterListFooterTags = "//span[contains(.,'%s')]";
	String docListFooterTags = "//div[contains(@class,'find-a-doctor__list')]//footer//span[contains(.,'%s')]";
	By docPagination = By.cssSelector(".pagination__count");
	By docFilterSearchCountMessage = By.cssSelector("h4[class*='find-a-doctor__term-count']");

	By findADocTagsDisplayedLabel = By.xpath("(//div[contains(.,'Displaying')])[last()]//span[@class='tag__content']");

	By displayedDoctorLabel = By.cssSelector("div[class*='find-a-doctor__pain-type-label__container']");

	// Clear All Filters

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

			new ZimmerHomePage(driver).verifySearch("global", "", "Knee", "");

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

			ptr.getElements(docTypes).stream().filter(ele -> ele.getText().trim().equalsIgnoreCase(docType)).findFirst()
					.ifPresentOrElse(ele -> {

						ptr.highlighElement(ele);
						ele.click();
						Allure.step(docType + " is selected");
						log.info(docType + " is selected");

					}, () -> {
						log.info("NO doc type found");
						Allure.step("NO doc type found :");
						throw new TestException("No Docter Type Found");

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
			// test
		}
	}

	// System.out.println(Integer.parseInt(str.replaceAll("[\\D]", "")))
	@Step("Verify Find a doc with filter")
	public void filterDoc(String docFilter, String filterType) {
		try {
			ptr.delay(5);
			int tagCount = Integer.parseInt(
					ptr.getVisibleText(ptr.getDynamicLocator("XPATH", docFilter, filterLabel)).replaceAll("[\\D]", ""));

			System.out.println(tagCount);
			ptr.click(ptr.getDynamicLocator("XPATH", docFilter, filterCheckBox), docFilter);
			log.info("Selected a filter type checkbox" + docFilter);
			ptr.click(ptr.getDynamicLocator("XPATH", "Apply Filter", filterButton), "Apply Filter");
			ptr.delay(5);

			log.info("clicked 'Apply Filter' button " + filterButton);
			int searchCountFromMessage = Integer
					.parseInt(ptr.getVisibleText(docFilterSearchCountMessage).replaceAll("[\\D]", ""));
			Assert.assertEquals(searchCountFromMessage, tagCount, "Failed: doctor filter count mismatched");
			log.info("The filter count macthed with the search message count");

			if (filterType.equalsIgnoreCase("procedure")) {
				Assert.assertEquals(ptr.getElements(docterListFooter).size(), tagCount,
						"Failed: doctor filter count mismatched");
				Allure.step("Doctors found with the filter : " + tagCount);
				Assert.assertEquals(ptr.getElementsSize(ptr.getDynamicLocator("XPATH", docFilter, docListFooterTags)),
						tagCount, "Failed: Filter tags count mismatched");
				Allure.step("Doctors found with the filter : " + docFilter + " - " + tagCount);
				IntStream.range(0, ptr.getElements(docterListFooter).size()).forEach(i -> {

					ptr.scrollPage(
							ptr.getElements(ptr.getDynamicLocator("XPATH", docFilter, docListFooterTags)).get(i));
					ptr.highlighElement(
							ptr.getElements(ptr.getDynamicLocator("XPATH", docFilter, docListFooterTags)).get(i));

				});
			} else {

				ptr.scrollPage(ptr.getElement(docPagination));
				int paginationCount = Integer.valueOf(ptr.getElement(docPagination).getText()
						.substring(ptr.getElement(docPagination).getText().indexOf("of") + 2).trim());

				Assert.assertEquals(paginationCount, tagCount, "Failed: doctor filter count mismatched");
				log.info("The filter count macthed with the pagination count");

				Allure.step("Doctors found with the filter : " + docFilter + " - " + tagCount);
				ptr.scrollPage(ptr.getElements(doctorList).get(0));
				ptr.getElements(doctorList).get(0).click();

				ptr.delay(5);

				Assert.assertEquals(ptr.getVisibleText(doctorLanguage).trim(), docFilter,
						"Failed: doctor language filter mismatched");
				log.info("The filter language is visible on doc link ");

				ptr.highlighElement(doctorLanguage);

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
			// test
		}

	}

	By termsAndConditionsLink = By.linkText("Terms and Conditions");
	By termsAndconditionsHeader = By.cssSelector("h1[class*='heading']");

	@Step("Navigate and verify term and conditions link")
	public void clickAndVerifyFindDocLink(String linkName) {
		try {
			ptr.click(findADoc, "Find a Doctor Tab");
			log.info("clicked Find a Doctor Tab Link in header");
			ptr.delay(5);
			ptr.scrollPage(termsAndConditionsLink);
			ptr.click(termsAndConditionsLink, linkName);

			ptr.switchWindowAndNavigateTo(ConfigLoader.getInstance().getTermsUrl());
			Assert.assertEquals(ptr.getVisibleText(termsAndconditionsHeader), linkName,
					"Failed: to verify the link header");

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
			// test
		}

	}

	@Step("Verify the find a doc clear all filters")
	public void verifyClearFilter(String docFilter, String filterType) {
		try {
			ptr.delay(5);

			ptr.click(ptr.getDynamicLocator("XPATH", docFilter, filterCheckBox), docFilter);
			log.info("Selected a filter type checkbox" + docFilter);
			ptr.click(ptr.getDynamicLocator("XPATH", "Apply Filter", filterButton), "Apply Filter");
			ptr.delay(5);
			log.info("clicked 'Apply Filter' button " + filterButton);

			if (filterType.equalsIgnoreCase("procedure")) {

				Assert.assertEquals(
						ptr.getElement(ptr.getDynamicLocator("XPATH", docFilter, filterChkBox)).isSelected(), true,
						"Failed: filter is not checked");

				ptr.scrollPage(ptr.getElement(displayedDoctorLabel));
				Assert.assertTrue(ptr.getElements(findADocTagsDisplayedLabel).size() > 0, "Failed: filter count is 0");
				IntStream.range(0, ptr.getElements(findADocTagsDisplayedLabel).size()).forEach(i -> {

					Assert.assertEquals(ptr.getElements(findADocTagsDisplayedLabel).get(i).getText().trim(), docFilter,
							"Failed: filter is not displayed on the label");
					ptr.highlighElement(ptr.getElements(findADocTagsDisplayedLabel).get(i));

				});
				ptr.scrollPage(ptr.getElement(ptr.getDynamicLocator("XPATH", "Clear All Filters", filterButton)));
				ptr.click(ptr.getDynamicLocator("XPATH", "Clear All Filters", filterButton), "Clear All Filters");
				ptr.delay(2);

				Assert.assertEquals(
						ptr.getElement(ptr.getDynamicLocator("XPATH", docFilter, filterChkBox)).isSelected(), false,
						"Failed: filter is checked");


				ptr.scrollPage(ptr.getElement(docFilterSearchCountMessage));
				Assert.assertFalse(driver.findElement(displayedDoctorLabel).isDisplayed(),
						"Failed: the doctor filter label is displayed");
				log.info("The doctor filter label is not displayed");
				IntStream.range(0, ptr.getElements(findADocTagsDisplayedLabel).size()).forEach(i -> {

					Assert.assertNotEquals(ptr.getElements(findADocTagsDisplayedLabel).get(i).getText().trim(),
							docFilter, "Failed: filter is displayed on the label");

				});

			} else {

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
			// test
		}

	}

}
