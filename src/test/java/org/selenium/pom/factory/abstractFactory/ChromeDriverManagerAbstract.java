package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {


	@Override
	protected void startDriver() {
		// WebDriverManager.chromedriver().cachePath("Drivers").setup();
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.addArguments("--headless=new");
		opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource
		// problems
		// opt.addArguments("--no-sandbox"); // Bypass OS security model
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();

	}
   
}
