package org.selenium.pom.factory.abstractFactory;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

	@Override
	protected void startDriver() {
	String	WINDOW_SIZE = "1920,1080";
		// WebDriverManager.chromedriver().cachePath("Drivers").setup();
		System.out.println("*** startDriver for ChromeDriver ***");
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("--start-maximized");
		opt.addArguments("--remote-allow-origins=*");
		opt.addArguments("--headless=new");
		//opt.addArguments("--window-size=%s" + WINDOW_SIZE);
	//	opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource
		//opt.addArguments("--window-size=1920x1200");
		//opt.addArguments("--window-position=0x0");
		opt.addArguments("--disable-browser-side-navigation");
	//	opt.addArguments("--window-size=1840,1080");
	//	opt.addArguments("--screenshot");
	//	opt.addArguments("--enable-screenshot-testing-with-mode");
	//	opt.addArguments("--hide-scrollbars");
	//	opt.addArguments("--disable-gpu");
	//	opt.addArguments("--disable-infobars");
	//	opt.addArguments("--enable-automation");
	//	opt.addArguments("--disable-notifications");
		//opt.addArguments("--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors","--no-sandbox", "--disable-dev-shm-usage");
		//2
		
		opt.addArguments("--window-size=1920,1080");
		opt.addArguments("--disable-gpu");
		opt.addArguments("--start-maximized");
	
	
		
		
	
		opt.addArguments("--no-sandbox"); // Bypass OS security model
		opt.addArguments("--disable-setuid-sandbox");
		//opt.addArguments("--remote-debugging-port=9222");

		driver = new ChromeDriver(opt);
	//	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().setSize(new Dimension(1920, 1080));
		//driver.manage().window().maximize().;

	}

}
