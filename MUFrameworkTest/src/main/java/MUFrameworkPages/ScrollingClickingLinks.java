package MUFrameworkPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MUFrameworkUtilities.ExtentManager;

public class ScrollingClickingLinks {

	public WebDriver driver = null;

	/* Constructor Creation. */
	public ScrollingClickingLinks(WebDriver driver) {
		this.driver = driver;
	}

	// WebElement Locators for the Pages.
	By agenticCloudsLink = By
			.xpath("//a[(@href='https://www.testmuai.com/agentic-cloud/' and text()='Explore Agentic Clouds')]");
	By agenticCloudsCrossBrowserElement = By.xpath("//a[normalize-space()='CROSS-BROWSER TESTING']");
	By tryForFreeLink = By.xpath("//a[@class='chfw-signup']");
	By crossBrowserTab = By.xpath("//button[normalize-space()='Cross-Browser']");
	By crossBrowserLink = By.xpath("//h3[normalize-space()='Seamlessly Scale Cross-Browser Testing']");

	/*
	 * Method to find 'Explore Agentic Clouds' and click on it. In the next
	 * agent-cloud tab, search for 'Cross-Browser' and click on it and verify the
	 * text and click on 'Get Started for free'. It navigates to next tab and verify
	 * the URL of the tab and switch back to original tab and goto 'Blogs'. In
	 * 'Blogs', search for 'Community' tab and click on it and verify the URL and
	 * close the tab.
	 */
	public void scrollingClickingAndHandlingTabs() {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Custom explicit wait that executes JavaScript until it returns true.
			wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

			ExtentManager.getTest().info("The DOM and all page assets are completely loaded!");

			// Identifying Agent Cloud Element by scrolling and clicking on it.
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, arguments[0]);", driver.findElement(agenticCloudsLink));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(agenticCloudsLink))).click();

			// Verifying the Current URL of the page.
			String newTabUrl = driver.getCurrentUrl();
			int windowCount = driver.getWindowHandles().size();
			ExtentManager.getTest().info("Success! Context switched to the new tab. URL is: " + newTabUrl);
			Assert.assertTrue(newTabUrl.contains("https://www.testmuai.com/agentic-cloud/"));

			// Identifying the Cross Browser Element and Clicking on it and Verifying the
			// expected text.
			String crossBrowserText = "Seamlessly Scale Cross-Browser Testing";
			js.executeScript("window.scrollTo(0, arguments[0]);", driver.findElement(crossBrowserTab));
			js.executeScript("arguments[0].click();", driver.findElement(crossBrowserTab));
			Assert.assertTrue(driver.findElement(crossBrowserLink).getText().contains(crossBrowserText));

			// Identifying 'Get Started For Free' link and clicking on it.
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(agenticCloudsCrossBrowserElement)))
					.click();

			// Verifying the Title of the page.
			String expectedTitle = "Cross Browser Testing Tool";
			String actualTitle = driver.getTitle();
			ExtentManager.getTest().info("The Actual Title is : " + actualTitle);
			Assert.assertTrue(actualTitle.contains(expectedTitle),
					"Page title does not match! Expected: " + expectedTitle + " but found: " + actualTitle);
			ExtentManager.getTest().info("Success! Page title matches perfectly.");

			// Identifying 'Get Started For Free' link and clicking on it.
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(tryForFreeLink))).click();

			// Verifying the new URL after navigating.
			String newTabUrl1 = driver.getCurrentUrl();
			ExtentManager.getTest().info("Success! Context switched to the new tab. URL is: " + newTabUrl1);
			Assert.assertTrue(newTabUrl1.contains("https://www.testmuai.com/register/"));

			// Printing the Tab count and URL.
			int windowCount1 = driver.getWindowHandles().size();
			if (windowCount1 == 1) {
				System.out.println("Success! The page opened in the same window.");
				System.out.println("New URL in current window: " + driver.getCurrentUrl());
			} else {
				System.out.println("Failure! A new tab opened. Total windows: " + windowCount);
			}

			// Verifying the Title of the page.
			String expectedTitleToTry = "Register to Your Account | TestMu AI (Formerly LambdaTest)";
			String actualTitleToTry = driver.getTitle();
			Assert.assertEquals(actualTitleToTry, expectedTitleToTry,
					"Page title does not match! Expected: " + expectedTitleToTry + " but found: " + actualTitleToTry);
			ExtentManager.getTest().info("Success! Page title matches perfectly.");

			// Navigating to the URL.
			String targetUrl = "https://www.testmuai.com/blog";
			driver.get(targetUrl);

			// Clicking on the Community link.
			By communityLinkLocator = By.xpath("//a[normalize-space()='Community']");
			WebElement communityLink = wait
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(communityLinkLocator)));
			communityLink.click();

			// Waiting for the Community page to load completely.
			String expectedCommunityUrl = "https://community.testmuai.com/";
			wait.until(ExpectedConditions.urlToBe(expectedCommunityUrl));

			// Verifying the URL is matched as expected.
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(actualUrl, expectedCommunityUrl, "The URL after clicking Community did not match!");
			ExtentManager.getTest().info("Verification passed! Navigated to: " + actualUrl);
	}

}
