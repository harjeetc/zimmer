package org.selenium.pom.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.Color;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.Functions;
import org.testng.Assert;
import org.testng.TestException;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

/*
 * 
 */
public class ZimmerCareersPage extends BasePage {

	private Logger log = Logger.getLogger(ZimmerCareersPage.class);

	Functions ptr;

	public ZimmerCareersPage(WebDriver driver) {

		super(driver);
		ptr = new Functions(driver);
	}


	public ZimmerCareersPage load() {
		load(" ");
		return this;
	}




}
