package org.selenium.pom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;

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

	@FindBy(xpath = "(//a[contains(text(),'Find a Doctor')])[1]")
	WebElement findADoclink1;

	/*
	 * String headerLinks = "(//a[.='%s'])[1]"; String.format(headerLinks, linkName)
	 * here format function will replace the %s with input string i.e. linkName
	 */

	String headerLinks = "(//a[.='%s'])[1]";
	String footerLinks = "(//a[contains(.,'%s')])[1]";
	String careerHeader = "div[class*='cmp-container'] div[class*='title']:first-child div h4[class*='white']";
	String privacyPolicyHeader = "div[class*='cmp-container'] h1";
	String legalNoticeHeader = "div[class*='cmp-container'] h2";
	String navLinkHeader = "//span[contains(.,'%s')]/..";
	String buttons = "//button[.='%s']";
	By closePopup = By.cssSelector("div[aria-label='Company Logo']+button[class*='close']");
	By openPreferencesButton = By.cssSelector("button[aria-label='Open Preferences']");
	By footerLinkes = By.cssSelector("nav[class*='navigation--utility'] a");
	By headerLinkes = By.cssSelector("div[class*='global-footer--primary-row'] a");
	By images = By.tagName("img");
	String playButton = "//*[.='%s']/../../../..//button";
	String closeVideoPlayer = "//div[contains(@id,'%s')  and contains(@class,'card')]/../../../../..//button[contains(@class,'modal-close')]";
	String videoPlayer = "div[id*='%s'][class*='card'] video";
	By siteLink = By.cssSelector("nav[class*='navigation--utility'] a[href*='en/site']");
	String countryButtons = "(//div[.='%s'])[1]";
	By differentCountryPopup = By.xpath("//*[.='We noticed that you are visiting from a different country']/..");
	By differentCountryPopupMessage = By.xpath("//*[.='We noticed that you are visiting from a different country']");

	public ZimmerHomePage load() {
		load("/");
		return this;
	}

	@Step("Verify Page Title")
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
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
			}else {
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

	@Step("This will add the color to element locator {0}")
	public String getColorName(String eleLocator) {
		String hex = Color.fromString(ptr.waitForElement(By.cssSelector(eleLocator)).getCssValue("color")).asHex();
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

	public void navigateAndVerifyHeaderLinkTitle(String linkName) throws InterruptedException {
		try {
			ptr.click(By.xpath(String.format(headerLinks, linkName)));
			log.info("Header is clicked : " + linkName);
			ptr.waitforTitlepresent(linkName, 5);
			log.info("Title is verifed : " + linkName);
			if (linkName.equalsIgnoreCase("Careers")) {
				Assert.assertEquals(ptr.getVisibleText(By.cssSelector(careerHeader)), linkName,
						"Faild : page header not matched");
				log.info("Header is displayed : " + linkName);
				Assert.assertEquals(getColorName(careerHeader), "White", "Failed: font color not matched");
				log.info("Font color is " + getColorName(careerHeader));
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
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

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void verifyBrokenLinks(String type) {

		List<WebElement> allURLs = null;
		if (type.equalsIgnoreCase("header")) {
			allURLs = driver.findElements(headerLinkes);
		} else if (type.equalsIgnoreCase("footer")) {
			allURLs = driver.findElements(footerLinkes);
		} else if (type.equalsIgnoreCase("image")) {
			allURLs = driver.findElements(images);
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
				ptr.verifyEquals(httpURLConnect.getResponseCode(), 200,
						"Failed : BROKEN : " + httpURLConnect.getResponseCode());
			}
			// if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
			else {

				ptr.verifyTrue(false, "Failed : BROKEN : " + httpURLConnect.getResponseCode());
				log.info(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);

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
				ptr.clickAt(By.xpath(String.format(navLinkHeader, linkName)));
				ptr.delay(3);
				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				log.info(linkName + " is opened");

			} else {

				Assert.assertTrue(
						ptr.getAttribute(By.xpath(String.format(navLinkHeader, linkName)), "class").contains("opened"),
						"Failed : " + linkName + " is closed");
				ptr.clickAt(By.xpath(String.format(navLinkHeader, linkName)));
				ptr.delay(3);
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

}
