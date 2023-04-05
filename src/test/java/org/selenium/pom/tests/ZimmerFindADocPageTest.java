
package org.selenium.pom.tests;

import org.apache.log4j.Logger;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ZimmerFindADocPage;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;

public class ZimmerFindADocPageTest extends BaseTest {

	private Logger log = Logger.getLogger(ZimmerFindADocPageTest.class);






// find a doc test cases start from here
	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Test : Verify Find a Doctor by entering doc type, location and miles and docotors list is visible")
	public void verifyFindADotor() {
		/*
		 * Validate find a doc doctor with area location
		 */
		log.info("============Test started[ verifyFindADotor with results end2end ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		Allure.step("Find a Doctor by selecting type ,location and radius");
		zimmerFindADocPage.findADoctor("Knee", "San Jose", "25");
		zimmerFindADocPage.varifyDoctorList();
		log.info("============Test ended[ verifyFindADotor end2ensd ] =============");

	}



	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Test : Find a doc page: Verify No Location or miles Entered should throw a error")
	public void verifyNoLocationEnteredInFindADOCpage() {
		/*
		 * Validate find a doc doctor with area location
		 */
		log.info("============Test started[ verifyNoLocationEntered ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		Allure.step("Find a Doctor by selecting type ,but not location and radius");
		zimmerFindADocPage.verifyNoLocationError("error");

	}

	/*
	 * Validate the verifyPrivacyNoticePage is correct
	 */
	@Test(groups = { "HomePage", "find", "phase1" }, enabled = true)
	@Description("Verify Find a Doctor Page by clicking link on HomePage")
	@Story("ZBWR-646")
	@Link(name = "ZBWR-646", url = "https://concentrix-catalyst.atlassian.net/browse/ZBWR-646")
	public void verifyFindADoctorPage() throws InterruptedException {
		log.info("============Test started[ verifyFindADoctorPage] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Find a Doctor");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Find a Doctor");
		log.info("============Test ended[ verifyFindADoctorPage ] =============");

	}

	/*
	 * Validate the 'back to home page' link is functioning when user clicks on the
	 * find a doc page
	 */
	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Verify verifyBackToHomePage on Find a Doc Page")
	@Story("")
	@Link(name = "", url = "")
	public void verifyBackToHomePage() throws InterruptedException {
		log.info("============Test started[ verifyBackToHomePage] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Find a Doctor");
		zimmerHomePage.navigateBackToHomePage();
		zimmerHomePage.verifyPageTitle("Welcome to Zimmer Biomet");
		log.info("============Test ended[ verifyBackToHomePage ] =============");

	}

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify in Find a doc page user can filter by checkbox ")
	public void verifyFindADOCFilterCheckbox() {
		log.info("============Test started[  verifyFindADOCFilterCheckbox ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		zimmerFindADocPage.findADoctor("Robotic Surgery", "San Ramon", "25");
		Allure.step("Find a Doctor by selecting Filter type checkbox");
		zimmerFindADocPage.filterDoc("Minimally Invasive Surgery (MIS)", "procedure");
	}

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify in Find a doc page 'Terms and condtions link opens in a new window'")
	public void navigateAndVerifyTermsAndConditionsLink() {
		log.info("============Test started[  navigateAndVerifyTermsAndConditionsLink ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		Allure.step("Find a Doctor by selecting 'Terms and condtions link opens in a new window");
		zimmerFindADocPage.clickAndVerifyFindDocLink("Terms and Conditions");

	}

	/*
	 * This test case will check with checkbox procedure and checkbox language flow
	 * and validate the language is English
	 */
	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify in Find a doc page user can filter by Language")
	public void verifyFindADOCLanguageFilterCheckbox() {
		log.info("============Test started[  verifyFindADOCLanguageFilterLanguage ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		zimmerFindADocPage.findADoctor("Robotic Surgery", "San Ramon", "25");
		Allure.step("Find a Doctor by selecting Filter type checkbox");
		zimmerFindADocPage.filterDoc("English", "language");
		log.info("============Test ended[  verifyFindADOCLanguageFilterLanguage ] =============");

	}

	@Test(groups = { "findoc", "phase1" }, enabled = true)
	@Description("Test : Verify in Find a doc page user can clear filter checkbox filter tags")
	public void verifyFindADocClearFilter() {
		log.info("============Test started[  verifyFindADocClearFilter ] =============");
		ZimmerFindADocPage zimmerFindADocPage = new ZimmerFindADocPage(getDriver()).load();
		zimmerFindADocPage.findADoctor("Robotic Surgery", "San Ramon", "25");
		Allure.step("Find a Doctor by selecting Filter type checkbox");
		zimmerFindADocPage.verifyClearFilter("Anterior Hip Replacement", "procedure");
		log.info("============Test ended[  verifyFindADocClearFilter ] =============");

	}



}
