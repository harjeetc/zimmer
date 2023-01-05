package org.selenium.pom.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.tests.ZimmerHomePageTest;
import org.selenium.pom.utils.ConfigLoader;

import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private Logger log = Logger.getLogger(ZimmerHomePageTest.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@Step("Login with user credentials")
	public void load(String urlType) {
		log.info("============entered userName + passwrd[User is logged into QA Zimmer Website] =============");
		String cred = "zimmer:zmrbmt01!@";
		System.out.println("=========" + ConfigLoader.getInstance().getBaseUrl());
		if (urlType.equalsIgnoreCase("switch")) {
			driver.get(ConfigLoader.getInstance().getSwitchUrl().replace("CRED", cred));
			// driver.get("https://"+cred+"qa-www.zimmerbiomet.com/en/");
		}else {
			driver.get(ConfigLoader.getInstance().getBaseUrl().replace("CRED", cred));
		}
	}

	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		System.out.println("OVERLAY SIZE" + overlays.size());
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
			System.out.println("OVERLAYS INVISIBLE");
		} else {
			System.out.println("OVERLAY NOT FOUND");
		}
	}
}
