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
	 * Validate the Page Title is correct 
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
	 * Validate the verifyPrivacyNoticePage is correct
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
	 * Validate The navigational h1 links are opening and closing 
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
	 *  validate images are not broken on homepage  
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyBrokenImages() {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("image");
	}
	
	/*
	 * Validate any footer links are broken need to add the 200 status codes 
	 */
	
	/*
	 *  validate header links are not broken 
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyHeaderBrokenLinks() {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("header");
	}
	
	/*
	 * Validate any footer links are broken need to add the 200 status codes 
	 */
	
	@Test(groups = { "HomePage", "Smoke" }, enabled = false)

	public void verifyFooterBrokenLinks() {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyBrokenLinks("footer");
	}
	/*
	 * Validate The accept cookies button 
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyAcceptCookies() throws InterruptedException {
		log.info("============Test started[ verifyAcceptCookies ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyAcceptAndRejectCookies("Accept Cookies");

		log.info("============Test ended[ verifyAcceptCookies ] =============");

	}
	/*
	 * Validate the Reject button 
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = false)
	public void verifyRejectCookies() throws InterruptedException {
		log.info("============Test started[ verifyAcceptCookies ] =============");
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyAcceptAndRejectCookies("Do Not Sell My Personal Information");

		log.info("============Test ended[ verifyAcceptCookies ] =============");

	}
}
