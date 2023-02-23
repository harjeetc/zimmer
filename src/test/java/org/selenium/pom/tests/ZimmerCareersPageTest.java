
package org.selenium.pom.tests;

import org.apache.log4j.Logger;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ZimmerCareersPage;
import org.selenium.pom.pages.ZimmerFindADocPage;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;

public class ZimmerCareersPageTest extends BaseTest {

	private Logger log = Logger.getLogger(ZimmerCareersPageTest.class);

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "phase1" }, enabled = true)
	@Feature("HomePage Tests")
	@Description("Verify Careers Page by clicking link on HomePage")

	public void verifyCareersPage() throws InterruptedException {
		log.info(
				"============Test started[ verify Careers Page when clicking on the Careers link on Homepage ] =============");
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Careers");
		// Careers
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		log.info(
				"============Test ended[verify Careers Page when clicking on the Careers link on Homepage  ] =============");

	}

	@Test(groups = { "HomePage", "video2", "phase1" }, enabled = true)
	@Description("Test : Verify Embedded ZBEdge Video Player")
	public void verifyEmbeddedVideoPlayer_ThePower_of_Us() throws InterruptedException {
		log.info("============Test started[  Verify Embedded Video Player 'Careers Video Player' ] =============");
		ZimmerCareersPage cp = new ZimmerCareersPage(getDriver()).load();
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver());
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		cp.verifyVideoPlayer("Alleviate pain and improve the quality of life for", "Close");
		log.info("============Test Ended[  Verify Embedded Video Player on careers ] =============");

	}

	@Test(groups = { "HomePage", "careersTab", "phase1" }, enabled = true)
	@Description("Test : Verify Career tabs open when clicked")
	public void verifyTabs() throws InterruptedException {
		log.info("============Test started[  Verify Career tabs open when clicked' ] =============");
		ZimmerCareersPage cp = new ZimmerCareersPage(getDriver()).load();
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver());
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		cp.verifyCareerTabs("Culture & Team", "Our culture promises represent our shared commitment for how we work together to deliver on our mission.");
		cp.verifyCareerTabs("Explore Careers", "Search by title or job number");
		cp.verifyCareerTabs("Living Our Mission", "Living Our Mission");
		cp.verifyCareerTabs("Diversity, Equity & Inclusion","Diversity, Equity and Inclusion come to life through our culture promises and in our environment.");
		log.info("============Test Ended[Verify Career tabs open when clicked ] =============");

	}
	
	@Test(groups = { "HomePage", "careersTablist", "phase1" }, enabled = true)
	@Description("Test : Verify Explore Careers tab")
	public void verifyExploreCareersTab() throws InterruptedException {
		log.info("============Test started[  Verify Explore Careers tab ] =============");
		ZimmerCareersPage cp = new ZimmerCareersPage(getDriver()).load();
		ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver());
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		cp.verifyCareerTabs("Explore Careers", "Search by title or job number");
		cp.verifyExploreCareer();

		log.info("============Test Ended[ Verify Explore Careers tab ] =============");

	}

}
