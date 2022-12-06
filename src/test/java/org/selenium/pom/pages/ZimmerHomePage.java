package org.selenium.pom.pages;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.internal.AllureStorage;
import io.qameta.allure.model.Status;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

/*
 * 
 */
public class ZimmerHomePage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerHomePage.class);

	Functions ptr;

	public ZimmerHomePage(WebDriver driver) {

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

	String headerLinks = "(//a[.='%s'])[1]";
	String footerLinks = "(//a[contains(.,'%s')])[1]";
	By careerHeader = By
			.cssSelector("div[class*='cmp-container'] div[class*='title']:first-child div h4[class*='white']");
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
	By productLinkHeader = By.cssSelector("h1[class*='heading']");
	By closeCookie = By.cssSelector("div[id*='close'] button[aria-label='Close']");
	By doctorTagList = By.cssSelector("div[class*='find-a-doctor__list'] footer");
	By findDoctorSpinner = By
			.cssSelector("div[class*='results aem']> div[class*='find-a-doctor find-a-doctor--results patients']");
	By noResult = By.cssSelector("div[class*='find-a-doctor__no-results-message']");
	By findDoctorTypeError = By.cssSelector("span[color='error']");
	By locationError = By.xpath("//input[@id='location']/..");

	// Please choose a treatment type.

	public ZimmerHomePage load() {
		load("/");
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
		}
	}

	@Step("Verify Page Title")
	public void verifyPageTitle(String title) {
		try {

			Assert.assertEquals(driver.getTitle(), title, "Faild : page title is matchmatched");
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

	@Step("Verify Embedded Video Player [ {0} ] with Action [ {1} ]")
	public void verifyVideoPlayer(String videoName, String action) {
		try {
			ptr.scrollPage(ptr.getDynamicLocator("XPATH", videoName, playButton));
			String playerId = ptr.getAttribute(ptr.getDynamicLocator("XPATH", videoName, playButton), "data-modal");
			ptr.click(ptr.getDynamicLocator("XPATH", videoName, playButton), videoName);
			Assert.assertTrue(ptr.waitForElement(ptr.getDynamicLocator("CSS", playerId, videoPlayer)).isDisplayed(),
					"Faild : Video Player is not found with name " + videoName);
			ptr.highlighElement(ptr.getDynamicLocator("CSS", playerId, videoPlayer));
			log.info("Video player [ " + videoName + " ] is opened ");

			if (action.equalsIgnoreCase("Close")) {
				ptr.click(ptr.getDynamicLocator("XPATH", playerId, closeVideoPlayer), "close video player");
				Assert.assertEquals(ptr.getElementsSize(ptr.getDynamicLocator("XPATH", playerId, videoPlayer)), 0,
						"Failed : " + videoName + " is displayed");
				log.info("Video player [ " + videoName + " ] is closed ");
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verify Header Link")
	public void verifyHeaderLink(String linkName) {
		try {
			ptr.scrollPage(By.xpath(String.format(headerLinks, linkName)));
			Assert.assertEquals(ptr.waitForElement(By.xpath(String.format(headerLinks, linkName))).isDisplayed(), true,
					"Failed : " + linkName + " is not displayed");
			log.info("Link is displayed : " + linkName);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void verifyFooterLink(String linkName) {
		try {
			ptr.scrollPage(By.xpath(String.format(footerLinks, linkName)));
			Assert.assertEquals(ptr.waitForElement(By.xpath(String.format(footerLinks, linkName))).isDisplayed(), true,
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

	@Step("Verify Switch Country PopUp Message (We noticed that you are visiting from a different country)")
	public void verifySwitchCountry(String countryName) {
		try {
			if (countryName.length() > 1) {
				ptr.click(siteLink, "EN Site Link");
				ptr.click(ptr.getDynamicLocator("XPATH", countryName, countryButtons), countryName);
			} else {
				ptr.navigateTo(ConfigLoader.getInstance().getSwitchUrl());
			}
			Assert.assertEquals(ptr.waitForElement(differentCountryPopup).isDisplayed(), true,
					"Failed : popup is not displayed");
			ptr.highlighElement(differentCountryPopup);
			Assert.assertEquals(ptr.waitForElement(differentCountryPopupMessage).isDisplayed(), true,
					"Failed : popup is not displayed");
			ptr.highlighElement(differentCountryPopupMessage);
			log.info("We noticed that you are visiting from a different country : is displayed");
		} catch (Exception e) {
			e.printStackTrace();
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}

	}

	// @Step("This will add the color to element locator {0}")
	public String getColorName(By eleLocator, String attribute) {
		String hex = Color.fromString(ptr.waitForElement(eleLocator).getCssValue(attribute)).asHex();
		log.info("Hex code is : " + hex);
		String name = "";
		switch (hex.toLowerCase()) {
		case "#ff0000":
			name = "Red";
			break;
		case "#0080000":
			name = "Green";
			break;
		case "#0000fa":
			name = "Blue";
			break;
		case "#ffffff":
			name = "White";
			break;
		case "#000000":
			name = "Black";
			break;
		case "#ffff00":
			name = "Yellow";
			break;
		case "#007575":
			name = "Teal";
			break;
		case "#f9f9f9":
			name = "off-White";
			break;
		case "#e4e6e9":
			name = "gray-white";
			break;
		case "#dcdcdc":
			name = "gray-light";
			break;
		case "#E3E3E4":
			name = "gray-Dull";
			break;
		case "#8c8c8c":
			name = "gray";
			break;
		case "#4c4c4c":
			name = "gray-dark";
			break;
		case "#2f4f4f":
			name = "Slate-Gray";
			break;
		case "#dd3f64":
			name = "error";
			break;
		case "#fccfcf":
			name = "Error-light";
			break;
		case "#fff9fe":
			name = "Error-White";
			break;
		case "#fad241":
			name = "caution";
			break;
		case "#fffdd0":
			name = "caution-light";
			break;
		case "#a6d3a6":
			name = "success";
			break;
		case "#ddf5dd":
			name = "Success-light";
			break;
		case "#3871E2":
			name = "Error-White";
			break;
		case "#2B2B80":
			name = "blue-DARK";
			break;
		case "#78AEE0":
			name = "BLUE-MEDIUM";
			break;
		case "#EBF7FD":
			name = "BLUE-LIGHT";
			break;
		case "#F2F4F7":
			name = "BLUE-NEUTRAL";
			break;
		case "#F2F9FD":
			name = "BLUE-EXTRA-LIGHT";
			break;
		case "#0A8381":
			name = "TEAL-PRIMARY";
			break;
		case "#045A79":
			name = "TEAL-DARK";
			break;
		case "#D0ECE3":
			name = "TEAL-LIGHT";
			break;
		case "#F1F8F6":
			name = "TEAL-NEAUTRAL";
			break;
		case "#960877":
			name = "MAGENTA-PRIMARY";
			break;
		case "#582273":
			name = "MAGENTA-DARK";
			break;
		case "#EEBDE2":
			name = "MAGENTA-LIGHT";
			break;
		case "#F3EAF8":
			name = "MANGENTA-NEUTRAL";
			break;
		case "#FBFEFF":
			name = "BALANCE-LIGHT";
			break;
		case "#718CC1":
			name = "BALANCE-MID";
			break;
		case "938F9C":
			name = "NEUTRAL-PRIMARY";
			break;
		case "#1C1137":
			name = "NEUTRAL-DARK";
			break;
		case "#BAB9C3":
			name = "NEUTRAL-MEDIUM";
			break;
		case "#FAFAFA":
			name = "NEUTRAL-LIGHT";
			break;
		case "#796E87":
			name = "NEUTRAL-GREY";
			break;
		case "#FFD642":
			name = "YELLOW-PRIMARY";
			break;
		case "#FEE793":
			name = "YELLOW-LIGHT";
			break;
		case "#FDF2C9":
			name = "YELLOW-NEUTRAL";
			break;
		case "#FADA6A":
			name = "YELLOW-VISION";
			break;
		case "#413DC9":
			name = "PURPLE-PRIMARY";
			break;
		case "#5050A8":
			name = "PURPLE-DARK";
			break;
		case "#B5B2FF":
			name = "PURPLE-LIGHT";
			break;
		case "#F0F0FF":
			name = "PURPLE-NEUTRAL";
			break;
		case "#89bd3d":
			name = "SUSHI";
			break;
		case "#0081c9":
			name = "lochmara";
			break;
		case "#392B5F":
			name = "Paris-dark";
			break;
		case "#111137":
			name = "FIND-A-DOC";
		default:
			name = "Unknown";
			break;
		}
		return name;
	}
	/*
	 * This function is to to check if page title matches the text and color
	 */

	public void navigateAndVerifyHeaderLinkTitle(String linkName) throws InterruptedException {
		try {
			ptr.click(By.xpath(String.format(headerLinks, linkName)));
			log.info("Header is clicked : " + linkName);
			ptr.waitforTitlepresent(linkName, 5);
			log.info("Title is verifed : " + linkName);
			if (linkName.equalsIgnoreCase("Careers")) {
				Assert.assertEquals(ptr.getVisibleText(careerHeader), linkName, "Faild : page header not matched");
				log.info("Header is displayed : " + linkName);
				Assert.assertEquals(getColorName(careerHeader, "color"), "White", "Failed: font color not matched");
				log.info("Font color is " + getColorName(careerHeader, "color"));
			}

		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void navigateAndVerifyFooterLinkTitle(String linkName) throws InterruptedException {
		try {
			ptr.click(By.xpath(String.format(footerLinks, linkName)));
			log.info("Footer is clicked : " + linkName);
//			ptr.waitforTitlepresent(linkName, 5);
//			log.info("Title is verifed : " + linkName);

			if (linkName.equalsIgnoreCase("Privacy")) {
				Assert.assertTrue(ptr.getVisibleText(By.cssSelector(privacyPolicyHeader)).contains(linkName),
						"Faild : page header not matched");
			} else if (linkName.equalsIgnoreCase("Legal")) {
				Assert.assertTrue(ptr.getVisibleText(By.cssSelector(legalNoticeHeader)).contains(linkName),
						"Faild : page header not matched");
			}

		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void verifyAcceptAndRejectCookies(String buttonName) {
		try {
			if (buttonName.equalsIgnoreCase("Accept Cookies")) {

				// is for accept

				Assert.assertTrue(ptr.waitForElement(By.xpath(String.format(buttons, buttonName))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				// ptr.highlighElement(By.xpath(String.format(buttons, buttonName)));
				log.info("Button is displayed : " + buttonName);
				ptr.click(By.xpath(String.format(buttons, buttonName)), "Button is clicked : " + buttonName);

				Assert.assertEquals(ptr.getElementsSize(By.xpath(String.format(buttons, "Confirm My Choices"))), 0,
						"Failed : " + buttonName + " is displayed");

				log.info("Popup is not displayed for : " + buttonName);
				Assert.assertTrue(ptr.waitForElement(openPreferencesButton).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Open Preferences Button is displayed");
			} else if (buttonName.equalsIgnoreCase("Do Not Sell My Personal Information")) {

				// is for reject
				Assert.assertTrue(ptr.waitForElement(By.xpath(String.format(buttons, buttonName))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				// ptr.highlighElement(By.xpath(String.format(buttons, buttonName)));
				log.info("Link is displayed : " + buttonName);
				ptr.click(By.xpath(String.format(buttons, buttonName)), "Button is clicked : " + buttonName);
				Assert.assertTrue(
						ptr.waitForElement(By.xpath(String.format(buttons, "Confirm My Choices"))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Popup is displayed for : " + buttonName);
				ptr.click(closePopup, "Button is clicked : close");
				ptr.click(By.xpath(String.format(buttons, buttonName)), "Button is clicked : " + buttonName);
				Assert.assertTrue(
						ptr.waitForElement(By.xpath(String.format(buttons, "Confirm My Choices"))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Popup is displayed for : " + buttonName);
				ptr.click(By.xpath(String.format(buttons, "Confirm My Choices")),
						"Button is clicked : Confirm My Choices");
				Assert.assertTrue(ptr.waitForElement(openPreferencesButton).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Open Preferences Button is displayed");

			}
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}

	}

	/*
	 * Find all the URLS on the home page
	 */
	@Step("verifyBrokenLinks [ {1} ]")
	public void verifyBrokenLinks(String type) {

		List<WebElement> allURLs = null;
		if (type.equalsIgnoreCase("header")) {
			allURLs = driver.findElements(headerLinkes);
		} else if (type.equalsIgnoreCase("footer")) {
			allURLs = driver.findElements(footerLinkes);
		} else if (type.equalsIgnoreCase("image")) {
			allURLs = driver.findElements(images);
		} else {
			allURLs = driver.findElements(socialMediaLinkes);

		}
		System.out.println("Total links on the Wb Page: " + allURLs.size());

		Iterator<WebElement> iterator = allURLs.iterator();
		while (iterator.hasNext()) {
			String url;
			if (!type.equalsIgnoreCase("image")) {
				url = iterator.next().getAttribute("href");
				// log.info("======" + url);
			} else {
				url = iterator.next().getAttribute("src");
			}
			verifyLinkActive(url, type);

		}
		try {
			ptr.verifyAll();
		} catch (AssertionError e) {
			throw e;
		}
	}

	public void verifyLinkActive(String linkUrl, String type) {
		HttpURLConnection httpURLConnect = null;

		try {

			URL url = new URL(linkUrl);

			httpURLConnect = (HttpURLConnection) url.openConnection();
			String userpass = "zimmer" + ":" + "zmrbmt01!";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));

			httpURLConnect.setRequestProperty("Authorization", basicAuth);
			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {

				log.info("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : NOT BROKEN");
				Allure.step("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : NOT BROKEN");
				ptr.verifyEquals(httpURLConnect.getResponseCode(), 200,
						"Failed : BROKEN : " + httpURLConnect.getResponseCode());
			}
			// if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
			else {

				ptr.verifyTrue(false, "Failed : BROKEN : " + httpURLConnect.getResponseCode());

				log.info("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : BROKEN");

				Allure.step("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : BROKEN", Status.FAILED);

			}

		} catch (Exception e) {

		}
	}

	public void openAndCloseNavLinks(String linkName, boolean open) {
		try {
			if (open) {

				Assert.assertFalse(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is opened");
				// ptr.clickAt(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link
				// Header");
				ptr.click(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link Header");
				ptr.delay(2);
				// ptr.waitElementToLoad(closePopup, 0);
				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				log.info(linkName + " is opened");

			} else {

				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				// ptr.clickAt(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link
				// Header");
				ptr.click(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link Header");
				ptr.delay(2);
				Assert.assertFalse(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is opened");
				log.info(linkName + " is closed");

			}
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

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public void naviGateToPatients() {
		ptr.click(patientsTab, "Patients tab");
		ptr.delay(2);
	}

	// this is the correct no results - No search term provided.
	@Step("[ {0} ] search with keyword [ {1} ] and verify search term [ {2} ]")
	public void verifySearch(String type, String tab, String searchKeyword, String searchTerm) {
		try {
			if (searchKeyword.length() > 0) {
				if (type.equalsIgnoreCase("global")) {

					ptr.type(globalSearchTextBox, searchKeyword, "Global Search");
				} else {
					ptr.type(globalSearchTextBox, searchKeyword, "Child Search");
				}
			}
			// ptr.delay(2);
			ptr.pressTabAndEnter(globalSearchTextBox);
			ptr.pressEnter();
			ptr.delay(2);
			if (tab.equalsIgnoreCase("patients"))
				naviGateToPatients();
			if (searchTerm.length() > 0) {
				ptr.waitForElement(noSearchResults);
				Assert.assertEquals(ptr.getVisibleText(noSearchResults), searchTerm,
						"Failed : " + searchTerm + " is no found");
				ptr.highlighElement(noSearchResults);
				Allure.step("Search Term is visible : " + searchTerm);
				log.info("Search Term is visible : " + searchTerm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
			Allure.step("Search Term is not visible : " + searchTerm, Status.FAILED);
			ptr.highlight(noSearchResults);
			saveScreenshotPNG(driver);
			log.info("Search Term is not visible : " + searchTerm);
			e.printStackTrace();
			throw e;
		}

	}

	@Step("Verify cards displayed [ {1} ]")
	public void verifySearchCards(String cardName) {
		ptr.delay(5);
		try {

			ptr.getElements(searchCards).stream().forEach(ele -> {
				Assert.assertTrue(ele.getText().contains(cardName), "Failed : " + cardName + " is no found");
				ptr.highlighElement(ele);
				Allure.step(cardName + " is visible in card " + ele.getText());
				log.info(cardName + " is visible in card " + ele.getText());

			});

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
			Allure.step("Search Term is not visible : " + cardName, Status.FAILED);
			ptr.highlight(searchResultTerm);
			saveScreenshotPNG(driver);
			log.info("Search Term is not visible : " + cardName);
			e.printStackTrace();
			throw e;
		}

	}

	@Step("Verify ProductLink [ {0} ] Colors [ {1} ] [ {2} ] And Navigation [ {3} ]")
	public void verifyProductLinkColorAndNavigation(String productName, String testColor, String backgroundColor,
			String productHeader) {

		try {
			ptr.click(closeCookie);
			Assert.assertEquals(getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "color"),
					testColor, "Failed: test color not matched");
			log.info(
					"Text color is " + getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "color"));
			Allure.step(
					"Text color is " + getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "color"));
			Assert.assertEquals(
					getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "background-color"),
					backgroundColor, "Failed: background color not matched");
			log.info("Background color is "
					+ getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "background-color"));
			Allure.step("Background color is "
					+ getColorName(ptr.getDynamicLocator("XPATH", productName, productLink), "background-color"));
			ptr.click(ptr.getDynamicLocator("XPATH", productName, productLink), productName);
			ptr.delay(2);
			log.info(ptr.getVisibleText(productLinkHeader));
			Assert.assertTrue(ptr.getVisibleText(productLinkHeader).contains(productHeader),
					"Failed: product header not found");
			Allure.step("Product header found : " + productHeader + " in " + ptr.getVisibleText(productLinkHeader));
			Assert.assertTrue(driver.getTitle().contains(productHeader), "Failed: product title not found");
			Allure.step("Product page title found : " + productHeader + " in " + driver.getTitle());

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
			e.printStackTrace();
			Allure.step("Failed to verify product" + productName, Status.FAILED);
			ptr.highlight(ptr.getDynamicLocator("XPATH", productName, productLink));
			saveScreenshotPNG(driver);
			log.info("Failed to verify product : " + productName);
			throw e;
		}

	}

}
