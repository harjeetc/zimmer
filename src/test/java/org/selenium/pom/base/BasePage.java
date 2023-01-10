package org.selenium.pom.base;

import org.apache.log4j.Logger;

/**

 */

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
	
	/**
	 * Navigate to the ZimmerBiomet.com website
	 * This step will log into env and pass the user name/ password upon log in 
	 * 
	 */
//need to move the user log in information to constant class.
	@Step("Login with user credentials")
	public void load(String urlType) {
		log.info("============entered userName + passwrd[User is logged into QA Zimmer Website] =============");
		System.out.println("=========" + ConfigLoader.getInstance().getBaseUrl());
		if (urlType.equalsIgnoreCase("switch")) {
			driver.get(ConfigLoader.getInstance().getSwitchUrl());
	
		}else {
			driver.get(ConfigLoader.getInstance().getBaseUrl());
		}
	}
	
	
	}

