import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

public class tests {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver","/C:\\Users\\Lenovo\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            openInNewTab(driver, "https://www.thesparksfoundationsingapore.org/");
            openInNewTab(driver, "https://www.thesparksfoundationsingapore.org/about/vision-mission-and-values/");
            openInNewTab(driver, "https://www.thesparksfoundationsingapore.org/contact-us/");
            openInNewTab(driver, "https://www.thesparksfoundationsingapore.org/programs/");
            openInNewTab(driver, "https://www.thesparksfoundationsingapore.org/join-us/why-join-us/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void openInNewTab(WebDriver driver, String url) {
        // Open a new tab with the given URL
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Get all window handles
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        // Navigate to the URL
        driver.get(url);
        System.out.println("Opened " + url + " in a new tab.");

        // Perform tests based on the URL
        if (url.contains("thesparksfoundationsingapore.org/")) {
            if (url.endsWith("/about/")) {
                testAboutPage(driver);
            } else if (url.endsWith("/contact-us/")) {
                testContactPage(driver);
            }
           else if (url.endsWith("/programs/")) {
               testProgramsPage(driver);
           }
           else if (url.endsWith("/join-us/")) {
               testJoinUsPage(driver);
           }
            else {
                testHomePage(driver);
            }
        } else {
            System.out.println("Unsupported URL for testing: " + url);
        }
    }

        public static void testHomePage(WebDriver driver) {
        // Example Test 1: Check if logo exists
        WebElement logo = waitForElementVisible(driver, By.xpath("//a//img"));
        assert logo.isDisplayed(): "Logo not found";

        if (logo.isDisplayed()) {
            System.out.println("Logo is displayed.");
        } else {
            System.out.println("Logo is not displayed.");
        }

        // Example Test 2: Verify navigation bar
        WebElement navbar = waitForElementVisible(driver, By.xpath("//nav[@class='navbar navbar-default']"));
        assert navbar.isDisplayed(): "Navigation bar not found";

        if (navbar.isDisplayed()) {
            System.out.println("Navbar is displayed.");
        } else {
            System.out.println("Navbar is not displayed.");
        }
    }

    public static void testAboutPage(WebDriver driver) {
        // Navigate to About Us page
        WebElement aboutUsLink = waitForElementClickable(driver, By.linkText("About Us"));
        aboutUsLink.click();

        // Example Test 3: Validate name on About Us page
        WebElement name = waitForElementVisible(driver, By.xpath("//*[@id='home']/div/div[1]/h1/a"));
        assert name.isDisplayed(): "Name on About Us page not found";

        if (name.isDisplayed()) {
            System.out.println("Name on about us page is displayed.");
        } else {
            System.out.println("Name on about us page is not displayed.");
        }
    }
    public static void testContactPage(WebDriver driver) {
        // Verify the presence of the map
        WebElement mapElement = waitForElementVisible(driver, By.xpath("/html/body/div[3]"));

        if (mapElement.isDisplayed()) {
            System.out.println("Map is displayed.");
        } else {
            System.out.println("Map is not displayed.");
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(mapElement).click().sendKeys(Keys.ARROW_UP).perform();
        System.out.println("Performed zoom in action.");
        actions.moveToElement(mapElement).click().sendKeys(Keys.ARROW_DOWN).perform();
        System.out.println("Performed zoom out action.");

    }

    public static void testProgramsPage(WebDriver driver) {
        // Navigate to Programs page
        WebElement programsLink = waitForElementClickable(driver, By.linkText("Programs"));
        programsLink.click();

        // Check if the footer is displayed
        WebElement footer = waitForElementVisible(driver, By.xpath("/html/body/div[3]"));
        assert footer.isDisplayed(): "Footer is displayed";

        if (footer.isDisplayed()) {
            System.out.println("Footer is displayed.");
        } else {
            System.out.println("Footer is not displayed.");
        }
    }

    public static void testJoinUsPage(WebDriver driver) {
        // Navigate to Join Us page
        WebElement joinLink = waitForElementClickable(driver, By.linkText("Join Us"));
        joinLink.click();

        // Example Test 6: Validate presence of form on the page
        WebElement joinOptions = waitForElementVisible(driver, By.xpath("/html/body/div[2]/div/div[3]"));
        assert joinOptions.isDisplayed() : "Form not found";

        if (joinOptions.isDisplayed()) {
            System.out.println("Form is displayed.");
        } else {
            System.out.println("Form is not displayed.");
        }
    }

    // Helper methods for explicit waits
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(22));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(22));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}


