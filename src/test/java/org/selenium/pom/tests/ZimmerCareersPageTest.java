
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
				"============Test ended[  verify Careers Page when clicking on the Careers link on Homepage  ] =============");

	}



	

}
