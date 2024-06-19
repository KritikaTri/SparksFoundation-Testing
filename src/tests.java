import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class tests {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/C:\\Users\\Lenovo\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // Initialize Chrome WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://www.thesparksfoundationsingapore.org/");

        // Test Cases
        try {
            testHomePage(driver);
            testAboutPage(driver);
            testContactPage(driver);
            testProgramsPage(driver);
            testJoinUsPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void testHomePage(WebDriver driver) {
        // Example Test 1: Check if logo exists
        WebElement logo = driver.findElement(By.xpath("//a//img"));
        assert logo.isDisplayed(): "Logo not found";

        if (logo.isDisplayed()) {
            System.out.println("Logo is displayed.");
        } else {
            System.out.println("Logo is not displayed.");
        }

        // Example Test 2: Verify navigation bar
        WebElement navbar = driver.findElement(By.xpath("//nav[@class='navbar navbar-default']"));
        assert navbar.isDisplayed(): "Navigation bar not found";

        if (navbar.isDisplayed()) {
            System.out.println("Navbar is displayed.");
        } else {
            System.out.println("Navbar is not displayed.");
        }
    }

    public static void testAboutPage(WebDriver driver) {
        // Navigate to About Us page
        WebElement aboutUsLink = driver.findElement(By.linkText("About Us"));
        aboutUsLink.click();

        // Example Test 3: Validate name on About Us page
        WebElement name = driver.findElement(By.xpath("//*[@id=\"home\"]/div/div[1]/h1/a"));
        assert name.isDisplayed(): "Name on About Us page not found";

        if (name.isDisplayed()) {
            System.out.println("Name on about us page is displayed.");
        } else {
            System.out.println("Name on about us page is not displayed.");
        }
    }

    public static void testContactPage(WebDriver driver) {

        // Verify the presence of the map
        WebElement mapElement = driver.findElement(By.xpath("/html/body/div[3]"));

        if (mapElement.isDisplayed()) {
            System.out.println("Map is displayed.");
        } else {
            System.out.println("Map is not displayed.");
        }

        // Optionally interact with the map (e.g., zoom in/out)
        Actions actions = new Actions(driver);
        actions.moveToElement(mapElement).click().sendKeys(Keys.ARROW_UP).perform(); // Zoom in
        actions.moveToElement(mapElement).click().sendKeys(Keys.ARROW_DOWN).perform(); // Zoom out
    }

    public static void testProgramsPage(WebDriver driver) {
        // Navigate to Programs page
        WebElement programsLink = driver.findElement(By.linkText("Programs"));
        programsLink.click();

        // Check if the footer is displayed
        WebElement footer = driver.findElement(By.xpath("/html/body/div[3]"));
        assert footer.isDisplayed(): "Footer is displayed";

        if (footer.isDisplayed()) {
            System.out.println("Footer is displayed.");
        } else {
            System.out.println("Footer is not displayed.");
        }
    }

    public static void testJoinUsPage(WebDriver driver) {
        // Navigate to Join Us page
        WebElement joinLink = driver.findElement(By.linkText("Join Us"));
        joinLink.click();

        // Example Test 6: Validate presence of form on the page
        WebElement joinOptions = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]"));
        assert joinOptions.isDisplayed() : "Form not found";

        if (joinOptions.isDisplayed()) {
            System.out.println("Form is displayed.");
        } else {
            System.out.println("Form is not displayed.");
        }
    }
}
