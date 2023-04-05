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
		opt.addArguments("--headless=new");
		opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource
		//opt.addArguments("--window-size=1920x1200");
		opt.addArguments("--window-position=0,0");
		opt.addArguments("--window-size=1840,1080");
	//	opt.addArguments("--screenshot");
	//	opt.addArguments("--enable-screenshot-testing-with-mode");
	//	opt.addArguments("--hide-scrollbars");
		opt.addArguments("--disable-gpu");
	//	opt.addArguments("--disable-infobars");
		opt.addArguments("--enable-automation");
	//	opt.addArguments("--disable-notifications");
		//opt.addArguments("--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors","--no-sandbox", "--disable-dev-shm-usage");
		//2
		opt.addArguments("--no-sandbox"); // Bypass OS security model
		opt.addArguments("--disable-setuid-sandbox");
		opt.addArguments("--remote-debugging-port=9222");

		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();

	}

}
