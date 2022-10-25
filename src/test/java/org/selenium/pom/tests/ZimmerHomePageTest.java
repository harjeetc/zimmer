package org.selenium.pom.tests;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ZimmerHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ZimmerHomePageTest extends BaseTest {

    @Test
    public void homePageTest() throws InterruptedException {
       ZimmerHomePage zimmerHomePage = new ZimmerHomePage(getDriver()).load();
       
       
       Thread.sleep(5000);
   
       //zimmerHomePage.authLogin();
       //zimmerHomePage.clickFindDoc();
       
    }

   
}
