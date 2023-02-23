package org.selenium.pom.base;

import io.qameta.allure.Attachment;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.collect.ImmutableMap;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

/**
 * 
 * 
 * * Base Test is the main class which takes care of Browser setup, loading
 * configuration file and other reusable methods like screenshot, handling sync
 * issues and many more. With base class you can avoid code duplication and can
 * reuse the code as much you want. Base Test works with Selenium in following
 * manner:
 * 
 * When we create base Test and if TestCases extends BaseTest then we can use
 * all the methods of BaseTest.
 * 
 * Before calling actual @Test, Base Test methods will get executed and Depends
 * on annotations it will call the respective methods.
 * 
 * We can extend this class in all test cases and we can call custom methods as
 * well directly.
 *
 */

public class BaseTest {
	private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private void setDriverManager(DriverManagerAbstract driverManager) {
		this.driverManager.set(driverManager);
	}

	protected DriverManagerAbstract getDriverManager() {
		return this.driverManager.get();
	}

	private void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	protected WebDriver getDriver() {
		return this.driver.get();
	}

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public synchronized void startDriver(@Optional String browser) {
		browser = System.getProperty("browser", browser);
		System.out.println("============" + browser);
		;
		setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
		setDriver(getDriverManager().getDriver());

		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
	}

	@Parameters("browser")
	@AfterMethod(alwaysRun = true)
	public synchronized void quitDriver(@Optional String browser, ITestResult result)
			throws InterruptedException, IOException {
		try {
			Thread.sleep(300);
			System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
			if (result.getStatus() == ITestResult.FAILURE) {
				File destFile = new File("scr" + File.separator + browser + File.separator
						+ result.getTestClass().getRealClass().getSimpleName() + "_"
						+ result.getMethod().getMethodName() + ".png");
				allureLog("Failed");
				saveScreenshotPNG(getDriver());
			} else if (result.getStatus() == ITestResult.SUCCESS)
				allureLog("Sucess");
			getDriverManager().getDriver().quit();
		} catch (Exception e) {

		}

		finally {
			if (getDriverManager().getDriver() != null)
				getDriver().quit();
		}
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Step(value = "{0}")
	public static String allureLog(String message) {
		return message;
	}

}
