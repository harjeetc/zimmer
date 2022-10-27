package org.selenium.pom.tests;

import org.apache.log4j.Logger;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.annotations.Test;

public class ZimmerHomePageTest extends BaseTest {
	ZimmerHomePage zimmerHomePage;
	private Logger log = Logger.getLogger(ZimmerHomePageTest.class);

	/*
	 * Validate the Page Title
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyHomePageTitle() throws InterruptedException {
		log.info("============Test started[ verifyHomePageTitle ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyPageTitle("Welcome to Zimmer Biomet");
		log.info("============Test ended[ verifyHomePageTitle ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
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

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
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

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyLegalNoticePage() throws InterruptedException {
		log.info("============Test started[ verifyLegalNoticePage ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyFooterLink("Legal");
		zimmerHomePage.navigateAndVerifyFooterLinkTitle("Legal");
		log.info("============Test ended[ verifyLegalNoticePage ] =============");

	}

	/*
	 * Validate the Careers link is present and Careers title is correct
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyPrivacyPolicyPage() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		// zimmerHomePage.verifyHeaderLink("PrivacyPolicy");
		// zimmerHomePage.navigateAndVerifyHeaderLinkTitle("PrivacyPolicy");
		zimmerHomePage.grabAllURLLinks();

	}

}
