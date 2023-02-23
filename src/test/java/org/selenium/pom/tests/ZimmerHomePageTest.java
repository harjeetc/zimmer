
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

public class ZimmerHomePageTest extends BaseTest {

	private Logger log = Logger.getLogger(ZimmerHomePageTest.class);

	/*
	 * Validate the Page Title is correct
	 */

	@Test(groups = { "HomePage", "Test", "phase1" }, enabled = true)
	@Description("Test to verify HomePage Title is Welcome to Zimmer Biomet")
	@Feature("HomePage Tests")
	public void verifyHomePageTitle() throws InterruptedException {
		log.info("============Test started[ verifyHomePageTitle ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		log.info("============Test Steps[ User is logged into QA Zimmer HomePage ] =============");
		zimmerHomePage.verifyPageTitle("Welcome to Zimmer Biomet");
		log.info("============Test ended[ verifyHomePageTitle ] =============");

	}

	/*
	 * Validate the verifyPrivacyNoticePage is correct
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Verify Privacy Notice Page by clicking link on HomePage")

	public void verifyPrivacyNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyPrivacy Notice Page on HomePage ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Privacy Policy");
		// Privacy Policy
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Privacy Policy");
		log.info("============Test ended[ verifyPrivacy Policy Page] =============");

	}

	/*
	 * Validate the Legal notices
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Verify Legal Notices Page by clicking link on HomePage")

	@Feature("HomePage Tests")
	public void verifyLegalNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyLegal Notices Page ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Legal Notices");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Legal Notices");
		log.info("============Test ended[ verify Legal Notices Page ] =============");

	}

	/*
	 * Validate The Navigations h1 links are opening and closing
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	@Description("Verify the Navigations h1 links Expand and Collapse 'Product & Solutions', 'Education & Resources' and 'Abous US'")
	public void verifyExpandAndCollapseProdSolutionsLink() throws InterruptedException {
		log.info("============Test started[ Verify the Navigation h1 links Expand and Collapse] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();

		zimmerHomePage.openAndCloseNavLinks("Products & Solutions", true);

		zimmerHomePage.openAndCloseNavLinks("Products & Solutions", false);

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", true);

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", false);

		zimmerHomePage.openAndCloseNavLinks("About Us", true);

		zimmerHomePage.openAndCloseNavLinks("About Us", false);
		log.info("============Test Ended[ Verify the Navigation h1 links Expand and Collapse] =============");

	}

	/*
	 * validate images are not broken on homepage
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)

	@Description("Verify Broken Images on HomePage")
	public void verifyBrokenImages() {
		log.info("============Test started[ Verify Broken Images on HomePage are not broken] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("image");
	}

	/*
	 * validate header links are not broken on homepage
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify Header Broken Links")
	public void verifyHeaderBrokenLinks() {
		log.info("============Test started[ verifyHeaderLinks are not Broken ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("header");
	}

	/*
	 * Validate any footer links are broken need to add the 200 status codes
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify Footer Broken Links are working")
	public void verifyFooterBrokenLinks() {
		log.info("============Test started[ Verify Footer Links are working ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("footer");
		log.info("============Test ended[ Verify Footer links are working ] =============");

	}

	/*
	 * validate social media links are not broken
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify Social Media Links are not broken (Linkedin Link is returning 999 status code)")
	public void verifySocialMediaBrokenLinks() {
		log.info("============Test started[ verifySocialMediaLink are present and not broken ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("socialmedia");
		log.info("============Test ended[ verifySocialMediaLinks are present and not broken ] =============");
	}
	/*
	 * Validate The accept cookies button is accepted
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("TC 1 : Verify the 'Accept' cookies button is accepted on HomePage")
	@Story("ZB Site - Verification of the cookie pop up")
	@Link(name = "ZBWR-665", url = "https://concentrix-catalyst.atlassian.net/browse/ZBWR-665")
	public void verifyAcceptCookies() throws InterruptedException {
		log.info("============Test started[ verify Accept Cookies on HomePage ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Verify the 'Accept' cookies button is accepted on HomePage");
		zimmerHomePage.verifyAcceptAndRejectCookies("Accept Cookies");
		log.info("============Test ended[ verify Accept Cookies ] =============");

	}
	/*
	 * Validate the Reject cookies button
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("TC 2 : Verify the Reject cookies button pop up is displaying on HomePage")
	@Story("ZB Site - Verification of the cookie pop up")
	@Link(name = "ZBWR-666", url = "https://concentrix-catalyst.atlassian.net/browse/ZBWR-666")
	public void verifyRejectCookies() throws InterruptedException {
		log.info("============Test started[ verify Rejct Cookies ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Verify the Reject cookies button pop up is displaying on HomePage");
		zimmerHomePage.verifyAcceptAndRejectCookies("Do Not Sell My Personal Information");
		log.info("============Test ended[ verify Reject Cookies pop up is displaying  ]Test Ended =============");

	}

// value medical placement no search terms
	@Test(groups = { "HomePage", "phase1", "Search" }, enabled = true)
	@Description("Test : Verify Global Search when passing no values it displays a message 'No Search terms provided' in Medical Professionals tab")
	public void verifyGlobalSearchMedicalProfessionals() {
		/*
		 * Validate Search filter when passing no values it returns a message on the
		 * search page "No Search terms provided "
		 */
		log.info(
				"============Test started[ verifyGlobalSearch with no data and getting message : MedicalProfessionals] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Medical Professionals Global Search");
		zimmerHomePage.verifySearch("global", "", "", "No search term provided.");

	}

	@Test(groups = { "HomePage", "phase1", "Search" }, enabled = true)
	@Description("Test : Verify Global Search when passing no values it displays a message 'No Search terms provided' in Patients tab")
	public void verifyGlobalSearchPatients() {
		/*
		 * Validate Search filter when passing no values it returns a message on the
		 * search page "No Search terms provided " Please choose a pain type! is showing
		 * in stage business is aware and will move this test case to false
		 */
		log.info(
				"============Test started[ verifyGlobalSearch with no data and getting message : Patients] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Patients Global Search");
		zimmerHomePage.verifySearch("global", "Patients", "", "No search term provided.");
		log.info(
				"============Test Ended[ verifyGlobalSearch with no data and getting message : Patients] =============");
	}

	/*
	 * Validate Search filter is displayed keywords passed in Search component
	 *
	 */
	@Test(groups = { "HomePage", "phase1", "Search" }, enabled = true)
	@Description("Test : Verify Card Search with value In search")
	public void verifyCardSearchMedicalProfessionals() {

		log.info("============Test started[ verify Card Search with value : MedicalProfessionals ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		Allure.step("Medical Professionals Card Search");
		zimmerHomePage.verifySearchCards("Knee");
		log.info("============Test Ended[ verify Card Search with value : MedicalProfessionals ] =============");

	}

	@Test(groups = { "HomePage", "phase1", "Search" }, enabled = true)
	@Description("Test : Verify Card Search with value")
	public void verifyCardSearchPatients() {
		/*
		 * Validate Search filter is displayed keywords passed in Search component
		 */
		log.info("============Test started[ verify Card Search with value : Patients ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		zimmerHomePage.naviGateToPatients();
		Allure.step("Patients Card Search");
		zimmerHomePage.verifySearchCards("Knee");
		log.info("============Test Ended[ verify Card Search with value : Patients ] =============");

	}

	/*
	 * Validate the verifyPrivacyNoticePage is correct
	 */
	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Verify Find a Doctor Page by clicking link on HomePage")
	@Story("ZBWR-646")
	@Link(name = "")
	public void verifyFindADoctorPage() throws InterruptedException {
		log.info("============Test started[ verifyFindADoctorPage] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Find a Doctor");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Find a Doctor");
		log.info("============Test ended[ verifyFindADoctorPage ] =============");

	}

	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Verify paginations works in GLobal search")
	@Story("")
	@Link(name = "")
	public void verifySearchPagination() throws InterruptedException {
		log.info("============Test started[ verifySearchPagination] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyPagination(1);
		log.info("============Test ended[ verifySearchPagination ] =============");

	}

	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Test : Verify Embedded ZBEdge Video Player")
	public void verifyEmbeddedVideoPlayer() {
		log.info("============Test started[  Verify Embedded Video Player 'ZBEdge Video Player' ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyVideoPlayer("Watch the ZBEdge Video", "Close", "Button");
		log.info("============Test Ended[  Verify Embedded Video Player ] =============");

	}

	@Test(groups = { "HomePage", "video", "phase1" }, enabled = true)
	@Description("Test : Verify Embedded Tailored Resources for You Video Player")
	public void verifyEmbeddedVideoPlayer2() {
		log.info("============Test started[  Verify Embedded Video Player 'Tailored Resources for You'] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyVideoPlayer("Tailored Resources for You", "Close", "Link");
		log.info("============Test Ended[  Verify Embedded Video Player 'Tailored Resources for You'] =============");

	}

	/*
	 * Verify Different Country PopupMessage From Site Navigation")
	 * 
	 */
	@Test(groups = { "HomePage", "Smoke", "phase1" }, enabled = true)
	@Description("Test : Verify Diffrent Country Popup Message From Site Navigation")

	public void verifyDiffrentCountryPopupMessageFromSiteNavigation() {
		log.info(
				"============Test started[  Verify Diffrent Country PopupMessage From Site Navigation ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySwitchCountry("Latin America");
		log.info("============Test ended[  Verify Diffrent Country PopupMessage From Site Navigation ] =============");
	}

	@Test(groups = { "HomePage", "phase1", "Search" }, enabled = true)
	@Description("Test : Verify Global Search Card by filters Formats ( move the document into seperate test case.s")
	public void verifyCardSearchFilterByFormat() {
		/*
		 * Validate
		 */
		log.info("============Test started[ verify Card Search by Filter  : Medical Professionals ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		// zimmerHomePage.verifySearchFilter("Videos");
		zimmerHomePage.verifySearchFilter("Documents");
		log.info("============Test ended[ verify Card Search by Filter  : Medical Professionals ] =============");

	}

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Description("Test : Verify in  Global Search  clear filter button can clear  tags in Medical Professionals flow  ")
	public void verifySearchClearFilter() {
		log.info("============Test started[  verifySearchClearFilter ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		zimmerHomePage.verifySearchClearFilter("Documents", "Medical");

		log.info("============Test ended[  verifySearchClearFilter ] =============");

	}

}
