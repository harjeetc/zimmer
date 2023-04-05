package org.selenium.pom.utils;

import static org.testng.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import net.bytebuddy.asm.Advice.Enter;

/**
 * Takes care of common functions
 *
 * @author Harjeet Singh
 */
public class Functions {
	private WebDriver driver;
	SoftAssert verify;

	public Functions(WebDriver driver) {
		this.driver = driver;
		verify = new SoftAssert();
	}

	private long WAIT_DURATION_IN_SECONDS = 60;
	private int TIME_OUT_IN_SECONDS = 30;

	private long NINTY_SECOND_IN_MILLIS = 90;

	private Logger logger = Logger.getLogger(Functions.class);

	public long launchTime = 0;
	public static String startTime = "0";
	public static String endTime = "0";

	public WebDriverWait wait;

	private InputStream getResourceFromClasspath(String resourceName) throws IOException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new FileNotFoundException("resource '" + resourceName + "' not found in the classpath");
		}
		return inputStream;
	}

//Function to get time stamp in YYYY-MM-DD format
	public void verifyEquals(Object actual, Object expected, String failedLog) {
		verify.assertEquals(actual, expected, failedLog);

	}

	public void verifyTrue(boolean value, String failedLog) {
		verify.assertTrue(value, failedLog);

	}

	// Function to get time stamp in YYYY-MM-DD format
	public void verifyAll() {
		verify.assertAll();

	}

//Function to get time stamp in YYYY-MM-DD format
	public String dateYearStamp() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	// Function to get Last day in Next Month
	public String getNextDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date nextDate = cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = df.format(nextDate);
		return reportDate;
	}

	// Function to get time stamp in MMMdd format
	public String dateStamp() {

		DateFormat df = new SimpleDateFormat("MMMdd");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	// Function to get year in yyyy format
	public String yearStamp() {

		DateFormat df = new SimpleDateFormat("yyyy");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	// Function to get random string number
	public String RandomStringNumber() throws SQLException {
		// note a single Random object is reused here
		Random randomGenerator = new Random();
		int randomInt = 0;
		for (int idx = 1; idx <= 10; ++idx) {
			randomInt = randomGenerator.nextInt(10000);
		}
		String randomStr = String.valueOf(randomInt);

		System.out.println(randomStr);
		return randomStr;
	}

	// Function to change time from days,hrs into seconds
	public String ChangeTimeStamp(int days, int hrs) throws Exception {
		long time = (System.currentTimeMillis() / 1000);
		time = time - (24 * 60 * 60 * days + hrs * 60 * 60);
		String changedTime = (String.valueOf(time));
		return changedTime;
	}

	// Function to generate random string
	public String randomstring(int length) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(length);
	}

	// Function to get the year in format yyyy
	public String timeinyear() {

		DateFormat df = new SimpleDateFormat("yyyy");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	// Function to get the time stamp in MMddyyhhmmss format
	public String timeStamp() {

		DateFormat df = new SimpleDateFormat("MMddyyhhmmss");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	// Connects to config property and read the confidential details
	public static String getConfDetails(String confValueName) throws Exception {

		BufferedReader in = new BufferedReader(new FileReader("Conf.txt"));
		String line;
		int lineCount = 1;
		String confValue = "";
		while ((line = in.readLine()) != null) {
			if (line.indexOf(confValueName) != -1) {
				confValue = line.split("=")[1];

			}
			lineCount++;
		}
		in.close();

		return confValue;
	}

	public void CompareXML(String SourceFile, String DownloadedFile) throws Exception {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			dbf.setCoalescing(true);
			dbf.setIgnoringElementContentWhitespace(true);
			dbf.setIgnoringComments(true);
			DocumentBuilder db = dbf.newDocumentBuilder();

			org.w3c.dom.Document doc1 = db.parse(new File(SourceFile));
			doc1.normalizeDocument();

			org.w3c.dom.Document doc2 = db.parse(new File(DownloadedFile));

			doc2.normalizeDocument();
			Assert.assertTrue(doc1.isEqualNode(doc2));
			logger.info("XML comparison Passed");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("XML comparison failed");
		}

	}

	/**
	 * Function to to Wait for Presence Of Element Located
	 *
	 * @throws Exception
	 */
	public static void WaitforPresenceOfElementLocated(WebDriver driver, By by) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Function to to FindHeaderColumn in table grid
	 *
	 * @throws Exception
	 */
	public int FindHeaderColumn(WebDriver driver, String Col_Header) throws Exception {
		int FoundInColumn = 0;
		logger.info("Searching table for string: " + Col_Header);

		// get the header and size
		int Cols = driver.findElements(By.xpath("//div[1]/table/tbody/tr[2]/td")).size();

		// verify header for "search" in header columns
		for (int x = 2; x < Cols; x++) {
			String ColumnTxt = driver.findElement(By.xpath("//div[1]/table/tbody/tr[2]/td[" + x + "]")).getText();
			if (ColumnTxt.contains(Col_Header)) {
				FoundInColumn = x;
				logger.info("Grid header with '" + Col_Header + "'found in column" + FoundInColumn);
				break;
			}
		}
		return FoundInColumn;
	}

	// GEt Portal displayed time and return in epoc
	public static long GetPortalTime(WebDriver driver) throws Exception {

		WebElement sPortalDt = driver.findElement(By.xpath("//div/div[2]/div[2]"));
		String sPortalDate = sPortalDt.getText();
		System.out.println(sPortalDate);
		String[] sPortalDate1 = sPortalDate.split("day, ");

		sPortalDate = sPortalDate1[1].substring(0, sPortalDate1[1].length() - 4);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMM dd, yyyy hh:mm aa");
		Date parsedDate = sdf.parse(sPortalDate);
		long launchTime = parsedDate.getTime();
		System.out.println("\n*****launchTime: " + launchTime + " *****\n");
		return launchTime;
	}

	public void callingvbs(String fileName) throws IOException {
		try {
			logger.info("TestData/UploadBatchData/" + fileName + "_xml.vbs file");

			final URL fileURL = Functions.class.getClassLoader().getResource("TestData/UploadBatchData");
			final String folderPath = fileURL.getPath().substring(1);

			logger.info("VBS file path = " + folderPath + "/" + fileName + "_xml.vbs");
			String cmd = "wscript " + folderPath + "/" + fileName + "_xml.vbs  \"" + folderPath + "\" ";
			Process p = Runtime.getRuntime().exec(cmd);

		} catch (IOException e) {
			System.exit(0);
		}

	}

	public void CreateBatchfile_XML(String path) throws IOException {
		try {
			logger.info(path);
			String cmd = "wscript " + path;
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.exit(0);
		}

	}

	public static void Handle_onPresent(WebDriver driver, String xpath) throws Exception {
		try {
			WebElement elemnt = driver.findElement(By.xpath(xpath));
			elemnt.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Created by Harjeet
	 * 
	 */
	 

	// send keys and get visibilityOf of Element
	public void type(By locator, String value, String ele) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(value);
		logger.info(value + " typed in " + ele);
	}

	public void clear(By locator) {
		//
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();

	}

	// click function that will wait for visibilityOf element
	public void clickOnElement(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	/*
	 * idea to click/ and enabled.
	 */
	public boolean verifyEditable(WebDriver driver, By locater) {
		boolean elementIsClickable = false;
		try {
			elementIsClickable = driver.findElement(locater).isEnabled();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("verifyEditable");
		}
		return elementIsClickable;
	}
	// click and wait

	public void click(By by) {
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.elementToBeClickable(by));
		highlighElement(by);
		int timeout=20;
		try {
			ele.click();
		} catch (ElementClickInterceptedException e) {
			int i = 0;
			while (i < timeout) {
				try {
					Thread.sleep(i * 1000);
				} catch (InterruptedException e2) {
					/*
					 * Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity. 
					 * Occasionally a method may wish to test whether the current thread has been interrupted, and if so, to immediately throw this exception.
					 */
					e2.printStackTrace();
				}
				try {
					ele.click();
					logger.info("Cliked for ElementClickInterceptedException");
					break;
				} catch (Exception e1) {

				}
			}
		}
	}

	public void waitElementToLoad(By locator, int time) throws InterruptedException {
		int visible = 0;
		while (driver.findElements(locator).size() == 0 && visible < time) {
			logger.info("Waiting for element " + locator + " to visible");
			visible++;
		}
		logger.info("Element" + locator + "is visible");
		int enable = 0;
		while (enable < time) {
			logger.info("Waiting for element " + locator + " to enable");
			// Thread.sleep(enable*50);
			Thread.sleep(2000);
			try {
				if (driver.findElement(locator).isEnabled()) {
					logger.info("Element" + locator + "is enable");
					break;
				}

				// enable++;
			} catch (Exception e) {
				logger.info("Checking for Element" + locator + "is enable for " + enable + "Time(s)");
			}
			enable++;

		}

	}

	// click and wait and log
	public void click(By by, String eleName) {
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS)))
				.until(ExpectedConditions.elementToBeClickable(by));
		highlighElement(by);
		ele.click();
		logger.info("Clicked at : " + eleName);
	}

	public void switchWindowAndNavigateTo(String url) {

		switchToWindow(driver, 1);
		driver.get(url);
		delay(5);
		System.out.println(driver.getCurrentUrl());

	}

	/**
	 * Wait for an element to be displayed in the DOM
	 *
	 * @param elementLocator - CSS of the element
	 * @return - WebElement
	 */
//adding step
	public WebElement waitForElement(By elementLocator) {
		WebElement webElement = null;
		int timeout = TIME_OUT_IN_SECONDS; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			System.err.println(elementLocator);
			webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		} catch (WebDriverException e) {
			// do nothing, don't want to log this
		}

		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: "
					+ elementLocator + "\nCurrent page: " + driver.getCurrentUrl());
		}
		return webElement;
	}

	public int getElementsSize(By elementLocator) {
		List<WebElement> webElement = null;
		int size = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			System.err.println(elementLocator);
			size = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator)).size();

		} catch (WebDriverException e) {
			// do nothing, don't want to log this
		}

		return size;
	}

	public WebElement getElement(By elementLocator) {

		if (Objects.isNull(elementLocator)) {
			return null;
		}
		return driver.findElement(elementLocator);
	}

	public List<WebElement> getElements(By elementLocator) {

		if (Objects.isNull(elementLocator)) {
			return null;
		}
		return driver.findElements(elementLocator);
	}

	public WebElement waitForElementPrescence(WebDriver driver, By elementLocator) {
		WebElement webElement = null;
		int timeout = 10; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			System.out.println(elementLocator);
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

		} catch (StaleElementReferenceException e) {
			// do nothing, don't want to log this
		}
		// if element wasn't found -> fail test
		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: "
					+ elementLocator + "\nCurrent page: " + driver.getCurrentUrl());
		}
		return webElement;
	}

	public boolean selectDropDown(WebDriver driver, By locator, String val)
			throws InterruptedException, ClassNotFoundException, IOException {
		boolean result = false;
		int attempts = 0;
		Select select;
		while (attempts < 5) {
			try {
				select = new Select(driver.findElement(locator));
				select.selectByVisibleText(val);

				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
			assertFalse(true, "WebElement not found within " + attempts + " Failing test!" + " of element: " + locator
					+ "\nCurrent page: " + driver.getCurrentUrl());
		}
		return result;
	}

	/**
	 * Method to wait for page to update following an action that changes the pages
	 * state. This is needed to avoid any race conditions between page updates and
	 * Selenium, which can sometimes occur.
	 */

	public void refreshPage(WebDriver driver) {

		String currentURL = getCurrentURL(driver);
		driver.navigate().to(currentURL);
	}

	public void navigateTo(String url) {

		driver.navigate().to(url);
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

//  public String getElementText(WebDriver driver, String elementLocator) {
//
//    WebElement webElement = waitForElement(driver, elementLocator);
//    return webElement.getText();
//  }

	public void waitForFrameAndSwitch(WebDriver driver, By elementLocator) {

		int timeout = TIME_OUT_IN_SECONDS; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			// System.out.println(elementLocator);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));

			while (driver.findElements(By.xpath("//iframe[@aria-label='Main content']")).size() == 0) {
				logger.info("waiting for page to load");
			}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementLocator));

			logger.info("Switched To Frame :" + elementLocator.toString());
		} catch (NoSuchFrameException e) {
			// do nothing, don't want to log this
			e.printStackTrace();
			Assert.fail("Failed : waitForFrameAndSwitch" + e.toString());
		}
	}

	public WebElement highlighElement(By by) {
		WebElement element = driver.findElement(by);
		// draw a border around the found element
		try {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", element);

				Thread.sleep(500);

				((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed : highlighElement" + e.toString());
		}
		return element;
	}

	public WebElement highlight(By by) {
		WebElement element = driver.findElement(by);
		// draw a border around the found element
		try {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", element);

				Thread.sleep(500);

				// ((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''",
				// element);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed : highlighElement" + e.toString());
		}
		return element;
	}

	public WebElement waitForElementToBeClickable(WebDriver driver, By elementLocator, int timeout) {
		WebElement webElement = null;
		// int timeout = 10; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			System.out.println(elementLocator);
			webElement = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));

		} catch (StaleElementReferenceException e) {
			// do nothing, don't want to log this
		}

		// if element wasn't found -> fail test
		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds. Failing test!" + " of element: "
					+ elementLocator + "\nCurrent page: " + driver.getCurrentUrl());
		}
		return webElement;
	}

	public String getCurrentTimeStamp(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

//  public boolean isreadOnly(element) {
//    Boolean readOnly = false;
//    readOnly = ((element.getAttribute("disabled") != null) || (element.getAttribute("readonly") != null));
//    return readOnly;

	public String getVisibleText(By locator) {
		int timeout = 20;
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(timeout)))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		logger.info("Text captured :" + ele.getText().trim());
		return ele.getText().trim();

	}

	public String getAttribute(By locator, String attr) {
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(10)))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		return ele.getAttribute(attr).trim();

	}

	public void clickAt(By locator, String elem) {
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(10)))
				.until(ExpectedConditions.elementToBeClickable(locator));
		Actions builder = new Actions(driver);
		// highlighElement(locator);
		builder.moveToElement(ele).pause(1000).build().perform();
		logger.info("clicked at " + elem);
	}

	public void pressTabAndEnter(By locator) {
		WebElement ele = (new WebDriverWait(driver, Duration.ofSeconds(10)))
				.until(ExpectedConditions.elementToBeClickable(locator));
		Actions builder = new Actions(driver);
		highlighElement(locator);
		builder.moveToElement(ele).click().pause(1000).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

	}

	public void pressEnter() {

		Actions builder = new Actions(driver);

		builder.pause(1000).sendKeys(Keys.ENTER).pause(1000).build().perform();

	}

	public void moveTo(WebDriver driver, By locator) {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(locator)).build().perform();
	}

//  public void waitForText(String text, By by, String timeoutMsg) throws Exception {
//    for (int second = 0;; second++) {
//           if (second >= 30)
//                  fail(timeoutMsg);
//           try {
//                  if (text.equals(driver.findElement(by).getText()))
//                        break;
//           } catch (Exception e) {
//           }
//
//    }

	public static String getTimeStamp() {
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_YYYY_HH_MM_SS");
		String CurrentDate = sdf.format(d);
		return CurrentDate;
	}

	public String switchToWindow(WebDriver driver, String getActiveWindowHandle) {

		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (getActiveWindowHandle != windowHandle) {

				return windowHandle;

			}
//
		}
		return getActiveWindowHandle;

	}

	public void waitForWebElementInvisibility(WebDriver driver, WebElement webElement) {
		boolean found = false;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.invisibilityOf(webElement));
		} catch (final Exception e) {
			// logWarning(webElement.getText(), e);

		}
	}

	public String randomNumber() {
		int min = 10000;
		int max = 99999;
		int random_int = (int) (Math.random() * (max - min + 1) + min);

		return Integer.toString(random_int);
	}

	/**
	 * Mapping the column header with which column it belongs This will allow header
	 * columns moved around, still we can re-map correctly Also, allows to construct
	 * Xpath locators dynamically
	 */
	public WebElement getCellByTableAndHeader(WebDriver driver, String tableName, String headerName) {
		String data = null;
		WebElement ele = null;
		for (int i = 0; i < driver
				.findElements(
						By.xpath("//table[contains(@id,'" + tableName + "')]//th[contains(@class,'list_header')]"))
				.size(); i++) {
			data = driver
					.findElements(
							By.xpath("//table[contains(@id,'" + tableName + "')]//th[contains(@class,'list_header')]"))
					.get(i).getAttribute("glide_label").trim();
			if (data.equalsIgnoreCase(headerName)

					&& data != null) {
				// System.err.println(data + " found" + i);
				ele = driver.findElement(By.xpath("(//table[contains(@id,'" + tableName + "')]//tr[contains(@id,'"
						+ tableName + "')])[2]/td[" + (i + 3) + "]"));
			}

		}
		return ele;
	}

	public WebElement highlighElement(WebElement ele) {

		// draw a border around the found element
		try {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", ele);

				Thread.sleep(500);

				((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", ele);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed : highlighElement" + e.toString());
		}
		return ele;
	}

	// Function to scroll the page vertically:
	public void scrollPage(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(element));
			// executor.executeScript("arguments[0].scrollIntoView(true);", element);
			executor.executeScript(
					"arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"nearest\"});",
					element);
		} catch (Exception e) {
			try {
				// executor.executeScript("arguments[0].scrollIntoView(false);", element);
				executor.executeScript(
						"arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"nearest\"});",
						element);
			} catch (Exception e1) {
				throw e;
			}
		}

	}

//Function to scroll the page vertically:
	public void scrollPage(By locator) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript("arguments[0].scrollIntoView(true);",
		// driver.findElement(locator));
		try {
			new WebDriverWait(driver, Duration.ofSeconds(2))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
		} catch (Exception e) {
			executor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(locator));
		}

	}

	public String waitforTitlepresent(String titlevalue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(titlevalue));
		return driver.getTitle();
	}

	public void switchToWindow(WebDriver driver, int index) {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(index));
		logger.info("Switched to window " + tabs2.get(index));
	}

	/**
	 * @author SreeMoore Function to get time stamp in yyyy-MM-dd hh:mm:ss format
	 */
	public String dateYearWithTimeStamp() {
		// date format 2020-09-25 09:53:04 PM
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}

	/**
	 * @author SreeMoore Function to get time stamp + N days in yyyy-MM-dd hh:mm:ss
	 *         format
	 */
	public String dateYearWithTimeStamp(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		Date nextDate = cal.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		String reportDate = df.format(nextDate);
		return reportDate;
	}

	public void delay(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * dynmaic value will save in %s format from String class
	 */

	public By getDynamicLocator(String type, String dynamicValue, String locator) {

		By by = null;
		if (type.equalsIgnoreCase("XPATH"))
			by = By.xpath(String.format(locator, dynamicValue));
		else if (type.equalsIgnoreCase("CSS"))
			by = By.cssSelector(String.format(locator, dynamicValue));
		else if (type.equalsIgnoreCase("ID"))
			by = By.id(String.format(locator, dynamicValue));

		return by;
	}

}
