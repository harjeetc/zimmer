package org.selenium.pom.pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.ZimmerLogInCredentails;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/*
 * 
 */
public class ZimmerHomePage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerHomePage.class);

	Functions ptr;

	public ZimmerHomePage(WebDriver driver) {

		super(driver);
		ptr = new Functions(driver);

	}

	// (//*[name()='svg'][@class='icon icon--fad icon--normal icon--default'])[1]
	// By findADoc = By.xpath("//a[@class='link
	// link--blank'][normalize-space()='Find a Doctor']");
	By findADoc = By.xpath("(//*[name()='svg'][@class='icon icon--fad icon--normal icon--default'])[1]");
	By locationTextBox = By.cssSelector("#location");
	By radiusTextBox = By.cssSelector("#radius");
	By docTypes = By.xpath("//ul[@justify='content-between']/li");
	By findADocter = By.xpath("//span[.='Find a Doctor']/..");

	/*
	 * String headerLinks = "(//a[.='%s'])[1]"; String.format(headerLinks, linkName)
	 * here format function will replace the %s with input string i.e. linkName
	 */

	// Dynamic locators
	String headerLinks = "//div[contains(@class,'header')]//a[contains(normalize-space(.),'%s')]";
	String headerLinksCSS = "div[class*='header__row-top'] a";

	String footerLinks = "//div[contains(@class,'footer')]//a[contains(.,'%s')]";
	String privacyPolicyHeader = "div[class*='cmp-container'] h1";
	String legalNoticeHeader = "div[class*='cmp-container'] h2";
	String navLinkHeader = "//span[contains(.,'%s')]/..";
	String buttons = "//button[.='%s']";
	String playButton = "//*[.='%s']/../../../..//button";
	String playLink = "//*[.='%s']/../../../../..//a[@aria-label='Opens video in modal']";
	String closeVideoPlayer = "//div[contains(@id,'%s')  and contains(@class,'card')]/../../../../..//button[contains(@class,'modal-close')]";
	String videoPlayer = "div[id*='%s'][class*='card'] video";
	String countryButtons = "(//div[.='%s'])[1]";

	// Static locators
	By careerHeader = By
			.cssSelector("div[class*='cmp-container'] div[class*='title']:first-child div h1[class*='white']");
	By closePopup = By.cssSelector("div[aria-label='Company Logo']+button[class*='close']");
	By openPreferencesButton = By.cssSelector("button[aria-label='Open Preferences']");
	By headerLinkes = By.cssSelector("nav[class*='navigation--utility'] a");
	By footerLinkes = By.cssSelector("div[class*='global-footer--primary-container'] ul[label='footer'] a");
	By socialMediaLinkes = By.cssSelector("div[class*='global-footer--primary-container'] ul[class*='social-media'] a");
	By globalSearchTextBox = By.id("global-search");
	By globalSearchButton = By.cssSelector(
			"div[class='container container--default grid grid__gap--none grid__breakpoint--nobreak global-header__row-bottom'] button[aria-label='Site search']");
	By searchResultTerm = By.cssSelector("label[for='site-search__term']");
	By noSearchResults = By.xpath("//h3[normalize-space()='No search term provided.']");
	By images = By.tagName("img");
	By siteLink = By.cssSelector("nav[class*='navigation--utility'] a[href*='en/site']");
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

	/**
	 * Search Filter Format locators
	 */

	String filterCheckBox = "//label[contains(.,'%s')]/..";
	By searchCount = By.cssSelector("span[class*='term-count'] strong");

	String filterChkBox = "//label[contains(.,'%s')]/..//input";

	String filterButton = "//button[contains(.,'%s')]";

	By filterTags = By.xpath("//h6[.='Filters']/..//span");

	By searchedFilterCards = By.cssSelector("a[class~='card'] div:first-child span[class*='button']");

	By mediaDownloadIcons = By.cssSelector("a[class~='card'] div:last-child span[class*='button']");

	public ZimmerHomePage load() {
		load(" ");
		return this;
	}

	public ZimmerHomePage loadSwitch() {
		load("switch");
		return this;
	}

	@Step("Verify Pagination")
	public void verifyPagination(int defaultPageNumber) {
		try {
			// KNOWN data from the search results pagination.
			List<String> expPaginations = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

			verifySearch("global", "", "Knee", "");

			ptr.scrollPage(nextResultsButton);
			Assert.assertTrue(ptr.getVisibleText(paginationCount).contains("1-10 of"));
			Allure.step("Pagination count contains 1-10 of");
			Assert.assertTrue(ptr.getElement(nextResultsButton).isEnabled());
			Allure.step("Next button is enabled");
			Assert.assertEquals((int) Integer.valueOf(ptr.getVisibleText(activePage)), defaultPageNumber);
			Allure.step("Default pagination number is 1");
			/*
			 * collectors is a class and list is a static function .. taking all the
			 * elements storing in a list
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

	@Step("Verify Page Title")
	public void verifyPageTitle(String title) {
		try {
			Assert.assertEquals(driver.getTitle(), title, "Faild : page title is matchmatched");
			log.info("Title is verifed : " + title);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Step("Verify Embedded Video Player [ {0} ] with Action [ {1} ]")
	public void verifyVideoPlayer(String videoName, String action, String playType) {
		String playerId;
		try {
			if (playType.equals("Button")) {
				ptr.scrollPage(ptr.getDynamicLocator("XPATH", videoName, playButton));
				playerId = ptr.getAttribute(ptr.getDynamicLocator("XPATH", videoName, playButton), "data-modal");
				ptr.scrollPage(ptr.getDynamicLocator("XPATH", videoName, playButton));
				ptr.click(ptr.getDynamicLocator("XPATH", videoName, playButton), videoName);
				log.info("clicked playbutton");

			} else {
				ptr.scrollPage(ptr.getDynamicLocator("XPATH", videoName, playLink));
				playerId = ptr.getAttribute(ptr.getDynamicLocator("XPATH", videoName, playLink), "data-modal");
				ptr.click(ptr.getDynamicLocator("XPATH", videoName, playLink), videoName);
			}

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

	@Step("Verify Header Link with CSS")
	public void verifyHeaderLinkCSS(String linkName) {
		
		for (int i = 0; i < driver.findElements(By.cssSelector(headerLinksCSS)).size(); i++) {
			if (driver.findElements(By.cssSelector(headerLinksCSS)).get(i).getText().trim()
					.equalsIgnoreCase(linkName)) {
				ptr.scrollPage(driver.findElements(By.cssSelector(headerLinksCSS)).get(i));
				Assert.assertEquals(driver.findElements(By.cssSelector(headerLinksCSS)).get(i).isDisplayed(), true,
						"Failed : " + linkName + " is not displayed");
				log.info("Link is displayed : " + linkName);
			}
		}
		try {
			// ptr.scrollPage(By.xpath(String.format(headerLinks, linkName)));

		} catch (Exception e) {
			e.printStackTrace();
		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Step("Verify Footer Link")
	public void verifyFooterLink(String linkName) {
		try {
			ptr.scrollPage(By.xpath(String.format(footerLinks, linkName)));
			Assert.assertEquals(ptr.waitForElement(By.xpath(String.format(footerLinks, linkName))).isDisplayed(), true,
					"Failed : " + linkName + " is not displayed");
			log.info("Link is displayed : " + linkName);
		} catch (Exception e) {

			e.printStackTrace();
		} catch (AssertionError e) {

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
			// ptr.delay(10);
			ptr.click(By.xpath(String.format(headerLinks, linkName)));
			log.info("Header is clicked : " + linkName);
			ptr.waitforTitlepresent(linkName, 20);
			log.info("Title is verifed : " + linkName);
			
			if (linkName.equalsIgnoreCase("Careers")) {
				Assert.assertEquals(ptr.getVisibleText(careerHeader), linkName, "Failed : page header not matched");
				log.info("Header is displayed : " + linkName);
				Assert.assertEquals(getColorName(careerHeader, "color"), "White", "Failed: font color not matched");
				log.info("Font color is " + getColorName(careerHeader, "color"));
			} else if (linkName.equalsIgnoreCase("Find a Doctor")) {
				Assert.assertEquals(ptr.getVisibleText(productLinkHeader), "Find a health provider near you",
						"Failed : page header not matched");
				log.info("Header is displayed : Find a health provider near you");
				// ptr.highlighElement(productLinkHeader);

			}

		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void navigateAndVerifyHeaderLinkTitleCSS(String linkName) throws InterruptedException {
		
		try {
			log.info("I am in inside the css finddoc issue : " + linkName);
			for (int i = 0; i < driver.findElements(By.cssSelector(headerLinksCSS)).size(); i++) {
				if (driver.findElements(By.cssSelector(headerLinksCSS)).get(i).getText().trim()
						.equalsIgnoreCase(linkName)) {
					log.info("waitingto be clicked : " + linkName);
					ptr.waitForElementToBeClickable(driver, findADoc, 5);
					ptr.click(driver.findElements(By.cssSelector(headerLinksCSS)).get(i));
					log.info("clicked on find a doc now  : " + linkName);

				}
			}
			// ptr.delay(10);
			//ptr.click(By.xpath(String.format(headerLinks, linkName)));
			log.info("Header is clicked : " + linkName);
			//ptr.waitforTitlepresent(linkName, 20);
			//log.info("Title is verifed : " + linkName);
			
//			if (linkName.equalsIgnoreCase("Careers")) {
//				Assert.assertEquals(ptr.getVisibleText(careerHeader), linkName, "Failed : page header not matched");
//				log.info("Header is displayed : " + linkName);
//				Assert.assertEquals(getColorName(careerHeader, "color"), "White", "Failed: font color not matched");
//				log.info("Font color is " + getColorName(careerHeader, "color"));
//			} else if (linkName.equalsIgnoreCase("Find a Doctor")) {
//				Assert.assertEquals(ptr.getVisibleText(productLinkHeader), "Find a health provider near you",
//						"Failed : page header not matched");
//				log.info("Header is displayed : Find a health provider near you");
//				// ptr.highlighElement(productLinkHeader);
//
//			}

		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void navigateBackToHomePage() throws InterruptedException {
		try {
			ptr.click(backLink);
			log.info("Link is clicked : Back");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void navigateAndVerifyFooterLinkTitle(String linkName) throws InterruptedException {
		try {
			ptr.delay(2);

			ptr.click(By.xpath(String.format(footerLinks, linkName)));
			log.info("Footer is clicked : " + linkName);

			if (linkName.equalsIgnoreCase("Privacy")) {
				Assert.assertTrue(ptr.getVisibleText(By.cssSelector(privacyPolicyHeader)).contains(linkName),
						"Faild : page footer not matched");
			} else if (linkName.equalsIgnoreCase("Legal Notice")) {
				Assert.assertTrue(ptr.getVisibleText(By.cssSelector(legalNoticeHeader)).contains(linkName),
						"Faild : page footer not matched");
			} else if (linkName.equalsIgnoreCase("Find a Doctor")) {
				Assert.assertEquals(ptr.getVisibleText(productLinkHeader), "Find a health provider near you",
						"Faild : page footer not matched");
				log.info("Footer is displayed : Find a health provider near you");
				ptr.highlighElement(productLinkHeader);
			}

		} catch (AssertionError e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void verifyAcceptAndRejectCookies(String buttonName) {
		try {

			if (buttonName.equalsIgnoreCase("Accept Cookies")) {

				// is for accept flow for cookies

				Assert.assertTrue(ptr.waitForElement(By.xpath(String.format(buttons, buttonName))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Button is displayed : " + buttonName);
				ptr.click(By.xpath(String.format(buttons, buttonName)), "Button is clicked : " + buttonName);
				Assert.assertEquals(ptr.getElementsSize(By.xpath(String.format(buttons, "Confirm My Choices"))), 0,
						"Failed : " + buttonName + " is displayed");
				log.info("Popup is not displayed for : " + buttonName);
				Assert.assertTrue(ptr.waitForElement(openPreferencesButton).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");
				log.info("Open Preferences Button is displayed");
			} else if (buttonName.equalsIgnoreCase("Do Not Sell My Personal Information")) {
				/**
				 * // is for reject flow
				 */
				Assert.assertTrue(ptr.waitForElement(By.xpath(String.format(buttons, buttonName))).isDisplayed(),
						"Failed : " + buttonName + " is not displayed");

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
			} else {
				url = iterator.next().getAttribute("src");
			}
			verifyLinkActive(url, type);
			log.info(
					"The Linkedin 999 Request Denied status code is a generic error message The LinkedIn social media site returns this HTTP "
							+ "status code for different reasons, including submitting too many HTTP requests in a day, as well as the user agent that is making the HTTP request.");

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
			String basicAuth = "Basic "
					+ new String(Base64.getEncoder().encode(ZimmerLogInCredentails.getCred().getBytes()));

			httpURLConnect.setRequestProperty("Authorization", basicAuth);
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200 || httpURLConnect.getResponseCode() == 301
					|| httpURLConnect.getResponseCode() == 999) {

				log.info("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : NOT BROKEN");
				Allure.step("[ " + type.toUpperCase() + " ] : " + linkUrl + " - " + httpURLConnect.getResponseMessage()
						+ " : " + httpURLConnect.getResponseCode() + " : NOT BROKEN", Status.PASSED);

			} else {

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
				ptr.click(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link Header");
				ptr.delay(2);
				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				log.info(linkName + " is opened");

			} else {

				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				ptr.click(By.xpath(String.format(navLinkHeader, linkName)), "Nav Link Header");
				ptr.delay(2);
				Assert.assertFalse(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is opened");
				log.info(linkName + " is closed");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {
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

	/**
	 * 
	 * @param filterTypeFormat is used when user search keyword. User then selects
	 *                         Format type.
	 */
	@Step("Verify Global Search Filter Format")
	public void verifySearchFilter(String filterType) {

		ptr.delay(2);
		try {

			ptr.click(ptr.getDynamicLocator("XPATH", filterType, filterCheckBox), filterType);
			log.info("Selected a filter type checkbox" + filterType);
			ptr.click(ptr.getDynamicLocator("XPATH", "Apply Filters", filterButton), "Apply Filters");
			ptr.delay(5);
			log.info("clicked 'Apply Filter' button " + filterButton);

			Assert.assertEquals(ptr.getElementsSize(filterTags), 1, "Failed: search filter count mismatched");
			Allure.step("search filter count matched");
			Assert.assertEquals(ptr.getElement(filterTags).getText().trim(), filterType,
					"Failed: search filter value mismatched");
			ptr.highlighElement(filterTags);
			Allure.step("The filter value macthed with the searched tag");
			log.info("The filter count macthed with the search message count");

			Assert.assertTrue(ptr.getElementsSize(searchedFilterCards) > 0,
					"Failed: card count based on filter mismatched");

			ptr.getElements(searchedFilterCards).stream().forEach(ele -> {
				ptr.scrollPage(ele);
				if (filterType.equalsIgnoreCase("Videos")) {
					Assert.assertEquals(ele.getAttribute("aria-label").trim(), "open video in new window",
							"Failed: card type mismacthed");
					ptr.highlighElement(ele);
				} else if (filterType.equalsIgnoreCase("Documents")) {
					Assert.assertTrue(ele.getAttribute("class").trim().contains("media"),
							"Failed: card type mismacthed");
					ptr.highlighElement(ele);
				}

			});

			if (filterType.equalsIgnoreCase("Documents")) {
				ptr.getElements(mediaDownloadIcons).stream().forEach(ele -> {
					ptr.scrollPage(ele);
					Assert.assertEquals(ele.getAttribute("aria-label").trim(), "download pdf",
							"Failed: card type mismacthed");
					ptr.highlighElement(ele);

				});
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

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

	@Step("Verify the find a doc clear all filters")
	public void verifySearchClearFilter(String searchFilter, String filterType) {
		try {
			ptr.delay(5);
			int filterLabelCount = Integer
					.parseInt(ptr.getVisibleText(ptr.getDynamicLocator("XPATH", searchFilter, filterCheckBox))
							.replaceAll("[\\D]", ""));

			int searchTermCount = Integer.parseInt(ptr.getVisibleText(searchCount).trim());

			Assert.assertNotEquals(filterLabelCount, searchTermCount,
					"Failed: total search term count is equal to filter label count before apply filter");

			Allure.step("Before apply filter Total Search Term Count is [ " + searchTermCount
					+ " ] and Filter Count is [ " + filterLabelCount + " ]");

			ptr.click(ptr.getDynamicLocator("XPATH", searchFilter, filterCheckBox), searchFilter);
			log.info("Selected a filter type checkbox" + searchFilter);
			ptr.click(ptr.getDynamicLocator("XPATH", "Apply Filters", filterButton), "Apply Filters");
			ptr.delay(5);
			log.info("clicked 'Apply Filters' button " + filterButton);
			filterLabelCount = Integer
					.parseInt(ptr.getVisibleText(ptr.getDynamicLocator("XPATH", searchFilter, filterCheckBox))
							.replaceAll("[\\D]", ""));

			searchTermCount = Integer.parseInt(ptr.getVisibleText(searchCount).trim());

			Assert.assertEquals(filterLabelCount, searchTermCount,
					"Failed: total search term count is not equal to filter label count after apply filter");
			Allure.step("After apply filter Total Search Term Count is [ " + searchTermCount
					+ " ] and Filter Count is [ " + filterLabelCount + " ]");

			if (filterType.equalsIgnoreCase("medical")) {

				Assert.assertEquals(
						ptr.getElement(ptr.getDynamicLocator("XPATH", searchFilter, filterChkBox)).isSelected(), true,
						"Failed: filter is not checked");

				ptr.scrollPage(ptr.getElement(filterTags));
				Assert.assertTrue(ptr.getElements(filterTags).size() > 0, "Failed: filter count is 0");
				IntStream.range(0, ptr.getElements(filterTags).size()).forEach(i -> {

					Assert.assertEquals(ptr.getElements(filterTags).get(i).getText().trim(), searchFilter,
							"Failed: filter is not displayed on the label");
					ptr.highlighElement(ptr.getElements(filterTags).get(i));

				});
				ptr.scrollPage(ptr.getElement(ptr.getDynamicLocator("XPATH", "Clear Filters", filterButton)));
				ptr.click(ptr.getDynamicLocator("XPATH", "Clear Filters", filterButton), "Clear Filters");
				ptr.delay(2);
				filterLabelCount = Integer
						.parseInt(ptr.getVisibleText(ptr.getDynamicLocator("XPATH", searchFilter, filterCheckBox))
								.replaceAll("[\\D]", ""));

				searchTermCount = Integer.parseInt(ptr.getVisibleText(searchCount).trim());

				Assert.assertNotEquals(filterLabelCount, searchTermCount,
						"Failed: total search term count is equal to filter label count after clear filter");

				Allure.step("After clear filter Total Search Term Count is [ " + searchTermCount
						+ " ] and Filter Count is [ " + filterLabelCount + " ]");
				Assert.assertEquals(
						ptr.getElement(ptr.getDynamicLocator("XPATH", searchFilter, filterChkBox)).isSelected(), false,
						"Failed: filter is checked");

				Assert.assertTrue(driver.findElements(filterTags).size() == 0,
						"Failed: the doctor filter label is displayed");
				log.info("The search filter label is not displayed");

			} else {

			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}

	}

}
