package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenium.pom.factory.DriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        opt.addArguments("--headless");
        opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        opt.addArguments("--no-sandbox"); // Bypass OS security model
        opt.addArguments("--disable-gpu"); // applicable to windows os only
        opt.addArguments("display=:99");
        driver = new ChromeDriver(opt);
        driver.manage().window().maximize();
        
    }
}
