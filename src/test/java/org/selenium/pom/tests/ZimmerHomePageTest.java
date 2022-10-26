package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.annotations.Test;

public class ZimmerHomePageTest extends BaseTest {
	ZimmerHomePage zimmerHomePage;
	
	
	/*
	 * Validate the Page Title 
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyHomePageTitle() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyPageTitle("Welcome to Zimmer Biomet");

	}
	
	/*
	 * Validate the Careers link is present and Careers title is correct 
	 */

	@Test(groups = { "HomePage", "Smoke" }, enabled = true)
	public void verifyCareersPage() throws InterruptedException {
		zimmerHomePage = new ZimmerHomePage(getDriver()).load();
		zimmerHomePage.verifyHeaderLink("Careers");
		zimmerHomePage.navigateAndVerifyHeaderLinkTitle("Careers");

	}

}
