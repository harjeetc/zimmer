package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {


	@Override
	protected void startDriver() {
		// WebDriverManager.chromedriver().cachePath("Drivers").setup();
		System.out.println("*** startDriver for ChromeDriver ***");
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.addArguments("--headless");
		opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource
		opt.addArguments("--window-size=1920x1200");
		opt.addArguments("--screenshot");
		opt.addArguments("--enable-screenshot-testing-with-mode");
		opt.addArguments("--hide-scrollbars");
		opt.addArguments("--disable-setuid-sandbox");
		opt.addArguments("--no-sandbox");
		opt.addArguments("--disable-gpu");
		opt.addArguments("--disable-infobars");
		opt.addArguments("--remote-debugging-port=9222");
		opt.addArguments("--enable-automation");
		opt.addArguments("--disable-notifications");
		// problems
		// opt.addArguments("--no-sandbox"); // Bypass OS security model
		driver = new ChromeDriver(opt);
		//driver.manage().window().maximize();

	}
   
}
