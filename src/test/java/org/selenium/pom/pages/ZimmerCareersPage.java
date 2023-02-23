package org.selenium.pom.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class ZimmerCareersPage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerCareersPage.class);

	Functions ptr;

	public ZimmerCareersPage(WebDriver driver) {

		super(driver);
		ptr = new Functions(driver);

	}

	String buttons = "//button[.='%s']";

	// 2 differnt video types
	private String playVideo = "(//p[contains(.,'%s')]/../../../..//*[contains(.,'play video')])[last()]";
	private String closeVideoPlayer = "//div[contains(@id,'%s')  and contains(@class,'card')]/../../../../..//button[contains(@class,'modal-close')]";
	private String videoPlayer = "div[id*='%s'][class*='card'] video";

	public ZimmerCareersPage load() {
		load(" ");
		return this;
	}

	public void openPage(String pageTitle, String pageUrl) {
		System.out.println("pageURL==>" + pageUrl);

		if (driver.getTitle().equals(pageTitle)) {
			driver.get(pageUrl);
		}

	}

	@Step("Verify Embedded Video Player in Careers HomePage [ {0} ] with Action [ {1} ]")
	public void verifyVideoPlayer(String videoName, String action) {
		String playerId;
		try {
			/*
			 * in this step if the video button has button tag.
			 */

			ptr.scrollPage(ptr.getDynamicLocator("XPATH", videoName, playVideo));
			playerId = ptr.getAttribute(ptr.getDynamicLocator("XPATH", videoName, playVideo), "data-modal");
			ptr.click(ptr.getDynamicLocator("XPATH", videoName, playVideo), videoName);
			log.info("clicked playbutton");

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

	private By DiversityEquityInclusionHeader = By
			.xpath("(//div[contains(@aria-labelledby,'label-3')]//h2[contains(@class,'heading')])[1]");

	private By LivingOurMissionHeader = By
			.xpath("(//div[contains(@aria-labelledby,'label-2')]//h2[contains(@class,'heading')])[1]");

	private By ExploreCareersSearchBox = By.cssSelector("input[id='careers-search__term']");

	private By CultureTeamHeader = By
			.xpath("(//div[contains(@aria-labelledby,'label-0')]//h3[contains(@class,'heading')])[1]");

	private String CareerTabs = "//button[contains(.,'%s')]";

	@Step("Verify Tabs in Careers Page [ {0} ] with Action [ {1} ]")
	public void verifyCareerTabs(String tabName, String text) {

		try {
			/*
			 * in this step if the video button has button tag.
			 */

			ptr.scrollPage(ptr.getElement(ptr.getDynamicLocator("XPATH", tabName, CareerTabs)));
			ptr.click(ptr.getDynamicLocator("XPATH", tabName, CareerTabs));
			log.info("clicked tab");

			switch (tabName) {

			case "Culture & Team" -> {
				Assert.assertTrue(ptr.getVisibleText(CultureTeamHeader).contains(text),
						"Faild : Header name mismatched for  tab name " + tabName);
				ptr.highlighElement(CultureTeamHeader);
				log.info("Tab [ " + tabName + " ] is verified ");
			}
			case "Explore Careers" -> {
				Assert.assertTrue(ptr.getAttribute(ExploreCareersSearchBox, "placeholder").contains(text),
						"Faild : Header name mismatched for  tab name " + tabName);
				ptr.highlighElement(ExploreCareersSearchBox);
				log.info("Tab [ " + tabName + " ] is verified ");
			}
			case "Living Our Mission" -> {
				Assert.assertTrue(ptr.getVisibleText(LivingOurMissionHeader).contains(text),
						"Faild : Header name mismatched for  tab name " + tabName);
				ptr.highlighElement(LivingOurMissionHeader);
				log.info("Tab [ " + tabName + " ] is verified ");
			}
			case "Diversity, Equity & Inclusion" -> {
				Assert.assertTrue(ptr.getVisibleText(DiversityEquityInclusionHeader).contains(text),
						"Faild : Header name mismatched for  tab name " + tabName);
				ptr.highlighElement(DiversityEquityInclusionHeader);
				log.info("Tab [ " + tabName + " ] is verified ");
			}
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

	private By countryDropdown = By.xpath("//span[.='Country']/..");

	private By cityDropdown = By.xpath("//span[.='City']/..");

	private By tagContent = By.className("tag__content");

	@Step("Verify Tabs in Careers Page")
	public void verifyExploreCareer() {

		try {

			Assert.assertTrue(ptr.getElement(countryDropdown).isEnabled(), "Faild : Country dropdown is disabled");
			Assert.assertTrue(ptr.getElement(cityDropdown).getAttribute("class").contains("disabled"),
					"Faild : City dropdown is enabled");
			if (ptr.getElementsSize(tagContent) > 0) {

				ptr.getElements(tagContent).stream().forEach(ele -> {

					ptr.scrollPage(ele);
					ptr.highlighElement(ele);
					Allure.step("Tags captured : " + ele.getText());

				});

			} else {
				Allure.step("Faild: No tag list found");
				throw new TestException("Faild: No tag list found");

			}

			log.info("clicked tab");

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		} catch (AssertionError e) {

			e.printStackTrace();
			throw e;
		}
	}

}
