package MUFrameworkClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.MyTestListeners;

import BaseClass.BaseTest;
import MUFrameworkPages.ScrollingClickingLinks;
import MUFrameworkUtilities.ExtentManager;

@Listeners(MyTestListeners.class)
public class ScrollClickAndClose extends BaseTest {
	ScrollingClickingLinks linksToExecute;
	Logger log;
	
	@BeforeTest
	public void loggerSetUp() {
		log = LogManager.getLogger(ScrollClickAndClose.class);
	}
	
	@Test
	public void verifySuccessfulLogin() {
		// Creating object for the page class.
		linksToExecute = new ScrollingClickingLinks(driver);
		ExtentManager.getTest().info("Created Object for the Page class.");
		// Calling the method using page object.
		linksToExecute.scrollingClickingAndHandlingTabs();
		log.info("Executed the method Successfully");
		ExtentManager.getTest().info("Called the Method where we perform scrolling, clicking links.");

	}
	
}
