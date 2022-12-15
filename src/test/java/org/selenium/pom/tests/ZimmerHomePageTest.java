package org.selenium.pom.tests;

import org.apache.log4j.Logger;
import org.selenium.pom.base.BaseTest;
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

	@Test(groups = { "HomePage", "Smoke", "sanity" }, enabled = false)
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
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Feature("HomePage Tests")
	@Description("Verify Careers Page by clicking link on HomePage")

	public void verifyCareersPage() throws InterruptedException {
		log.info("============Test started[ verify Careers Page ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Careers");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		log.info("============Test ended[ verify Careers Page ] =============");

	}

	/*
	 * Validate the verifyPrivacyNoticePage is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Verify Privacy Notice Page by clicking link on HomePage")

	public void verifyPrivacyNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyPrivacy Notice Page on HomePage ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Privacy Policy");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Privacy");
		log.info("============Test ended[ verifyPrivacyNoticePage ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Verify LegalNoticePage by clicking link on HomePage")

	@Feature("HomePage Tests")
	public void verifyLegalNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyLegalNoticePage ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Legal Notice");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Legal Notice");
		log.info("============Test ended[ verifyLegalNoticePage ] =============");

	}

	/*
	 * Validate The navigational h1 links are opening and closing
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
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

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Verify Broken Images on HomePage")
	public void verifyBrokenImages() {
		log.info("============Test started[ Verify Broken Images on HomePage are not broken] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("image");
	}

	/*
	 * validate header links are not broken
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Header Broken Links")
	public void verifyHeaderBrokenLinks() {
		log.info("============Test started[ verifyHeaderLinks are not Broken ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("header");
	}

	/*
	 * Validate any footer links are broken need to add the 200 status codes
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Footer Broken Links are working")
	public void verifyFooterBrokenLinks() {
		log.info("============Test started[ Verify Footer Broken Links are working ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("footer");
		log.info("============Test ended[ verifySocial Media Links are present and working ] =============");

	}

	/*
	 * validate social media links are not broken
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Social Media Broken Links are not broken")
	public void verifySocialMediaBrokenLinks() {
		log.info("============Test started[ verifySocialMediaLink are present and working ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("socialmedia");
		log.info("============Test ended[ verifySocialMediaLinks are present and working ] =============");
	}
	/*
	 * Validate The accept cookies button is accepted
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("TC 1 : Verify the 'Accept' cookies button is accepted on HomePage")
	@Story("ZB Site - Verification of the cookie pop up")
	@Link(name="ZBWR-665", url="https://concentrix-catalyst.atlassian.net/browse/ZBWR-665")
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

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("TC 2 : Verify the Reject cookies button pop up is displaying on HomePage")
	@Story("ZB Site - Verification of the cookie pop up")
	@Link(name="ZBWR-666", url="https://concentrix-catalyst.atlassian.net/browse/ZBWR-666")
	public void verifyRejectCookies() throws InterruptedException {
		log.info("============Test started[ verify Rejct Cookies ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Verify the Reject cookies button pop up is displaying on HomePage");
		zimmerHomePage.verifyAcceptAndRejectCookies("Do Not Sell My Personal Information");
		log.info("============Test ended[ verify Reject Cookies pop up is displaying  ]Test Ended =============");

		/*
		 * Validate Verify Embedded Video Player ZBEdge Video
		 */
	}

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Embedded ZBEdge Video Player")
	public void verifyEmbeddedVideoPlayer() {
		log.info("============Test started[  Verify Embedded Video Player ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyVideoPlayer("Watch the ZBEdge Video", "Close","Button");
	}
	

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Embedded ZBEdge Video Player")
	public void verifyEmbeddedVideoPlayer2() {
		log.info("============Test started[  Verify Embedded Video Player 'Tailored Resources for You'] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyVideoPlayer("Tailored Resources for You", "Close","Link");
	}

	/*
	 * Verify Different Country PopupMessage From Site Navigation")
	 * 
	 */
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Diffrent Country Popup Message From Site Navigation")

	public void verifyDiffrentCountryPopupMessageFromSiteNavigation() {
		log.info(
		"============Test started[  Verify Diffrent Country PopupMessage From Site Navigation ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySwitchCountry("Latin America");
	}

	/*
	 * Verify Different Country PopupMessage By Launching Url") ")
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Diffrent Country Popup Message By Launching Url")
	public void verifyDiffrentCountryPopupMessage() {
		log.info("============Test started[  Verify Diffrent Country PopupMessage By Launching Url ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySwitchCountry("");
	}
	// value medical placementno search terms 
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
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
		zimmerHomePage.verifySearch("global", "", "", "No searchh term provided.");

	}

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Global Search when passing no values it displays a message 'No Search terms provided' in Patients tab")
	public void verifyGlobalSearchPatients() {
		/*
		 * Validate Search filter when passing no values it returns a message on the
		 * search page "No Search terms provided "
		 */
		log.info(
				"============Test started[ verifyGlobalSearch with no data and getting message : Patients] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Patients Global Search");
		zimmerHomePage.verifySearch("global", "Patients", "", "No search term provided.");
	}

	/*
	 * Validate Search filter is displayed keywords passed in Search component
	 */
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Card Search with value")
	public void verifyCardSearchMedicalProfessionals() {
		
		log.info("============Test started[ verify Card Search with value : MedicalProfessionals ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		Allure.step("Medical Professionals Card Search");
		zimmerHomePage.verifySearchCards("Knee");
	}

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Card Search with value")
	public void verifyCardSearchPatients() {
		/*
		 * Validate Search filter is displayed keywords passed in Search component
		 */
		log.info("============Test started[ verify Card Search with value : Patients ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifySearch("global", "", "Knee", "");
		;
		zimmerHomePage.naviGateToPatients();
		Allure.step("Patients Card Search");
		zimmerHomePage.verifySearchCards("Knee");
	}
	
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Robotics Link Color And Navigation")
	public void verifyRoboticsLink() {
		/*
		 * Validate Robotics link color and navigation 
		 */
		log.info("============Test started[ verify Robotics Link ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyProductLinkColorAndNavigation("Experience ROSA® Robotics", "Black", "White", "Robotic");
	
	}
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Verify Find a Doctor by entering doc type, location and miles and docotors list is visible")
	public void verifyFindADotor() {
		/*
		 * Validate find a doc doctor with area location 
		 */
		log.info("============Test started[ verifyFindADotor ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Find a Doctor by selecting type ,location and radius");
		zimmerHomePage.findADoctor("Knee", "San Jose", "25");
		zimmerHomePage.varifyDoctorList();
	
	}
	@Test(groups = { "HomePage", "Smoke" }, enabled =false)
	@Description("Test :Find a doc page: Verify No DoctorType Selected hit search should throw a error message")
	
	public void verifyNoDoctorTypeSelected() {
		/*
		 * Validate find a doc doctor with area location 
		 */
		log.info("============Test started[ verifyNoDoctorTypeSelected ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Find a Doctor not selected any doc type and thorwing error");
		//zimmerHomePage.verifyNoDoctorTypeError("Please choose a pain type!");
		zimmerHomePage.verifyNoDoctorTypeError("Please choose a treatment type.");
	
	}
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Test : Find a doc page: Verify No Location or miles Entered should throw a error")
	public void verifyNoLocationEntered() {
		/*
		 * Validate find a doc doctor with area location 
		 */
		log.info("============Test started[ verifyNoLocationEntered ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		Allure.step("Find a Doctor by selecting type ,but not location and radius");
		zimmerHomePage.verifyNoLocationError("error");
	
	}
	/*
	 * Validate the verifyPrivacyNoticePage is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	@Description("Verify Find a Doctor Page by clicking link on HomePage")
	@Story("ZBWR-646")
	@Link(name="ZBWR-646", url="https://concentrix-catalyst.atlassian.net/browse/ZBWR-646")
	public void verifyFindADoctorPage() throws InterruptedException {
		log.info("============Test started[ verifyFindADoctorPage] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Find a Doctor");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Find a Doctor");
		log.info("============Test ended[ verifyFindADoctorPage ] =============");

	}
	
	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	@Description("Verify paginations works in GLobal search ")
	@Story("")
	@Link(name="")
	public void verifySearchPagination() throws InterruptedException {
		log.info("============Test started[ verifySearchPagination] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyPagination(1);
		log.info("============Test ended[ verifySearchPagination ] =============");

	}

}
