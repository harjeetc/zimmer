package org.selenium.pom.tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.Debug;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.annotations.Test;

public class ZimmerHomePageTest extends BaseTest {
	ZimmerHomePage zimmerHomePage;
	private Logger log = Logger.getLogger(ZimmerHomePageTest.class);

	/*
	 * Validate the Page Title
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyHomePageTitle() throws InterruptedException {
		log.info("============Test started[ verifyHomePageTitle ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyPageTitle("Welcome to Zimmer Biomet");
		log.info("============Test ended[ verifyHomePageTitle ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyCareersPage() throws InterruptedException {
		log.info("============Test started[ verifyCareersPage ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Careers");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");
		log.info("============Test ended[ verifyCareersPage ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyPrivacyNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyPrivacyNoticePage ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Privacy");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Privacy");
		log.info("============Test ended[ verifyPrivacyNoticePage ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyLegalNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyLegalNoticePage ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Legal");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Legal");
		log.info("============Test ended[ verifyLegalNoticePage ] =============");

	}

	/*
	 * Example >
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyallHomePageLinks() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();

		// zimmerHomePage.grabAllURLLinks();
		// ZimmerHomePage.verifyLinkActive(null);

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyExpandAndCollapseProdSolutionsLink() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();

		zimmerHomePage.openAndCloseNavLinks("Products & Solutions", true);

		zimmerHomePage.openAndCloseNavLinks("Products & Solutions", false);

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", true);

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", false);

		zimmerHomePage.openAndCloseNavLinks("About Us", true);

		zimmerHomePage.openAndCloseNavLinks("About Us", false);

	}

	/*
	 * Validate the Navigation link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyExpandAndCollapseEducateResourceslink() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", true);

		zimmerHomePage.openAndCloseNavLinks("Education & Resources", false);

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyExpandAndCollapseAboutUsLink() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();

		zimmerHomePage.openAndCloseNavLinks("About Us", true);

		zimmerHomePage.openAndCloseNavLinks("About Us", false);

	}

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)

	public void verifyBrokenLinks() {
	}
	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyAcceptCookies() throws InterruptedException {
		log.info("============Test started[ verifyAcceptCookies ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyAcceptAndRejectCookies("Accept Cookies");

		log.info("============Test ended[ verifyAcceptCookies ] =============");

	}
	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyRejectCookies() throws InterruptedException {
		log.info("============Test started[ verifyAcceptCookies ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyAcceptAndRejectCookies("Do Not Sell My Personal Information");

		log.info("============Test ended[ verifyAcceptCookies ] =============");

	}
}
