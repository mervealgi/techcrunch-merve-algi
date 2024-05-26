import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BaseTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/mervealgi/IdeaProjects/techcrunch-merve-algi/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void test() {
        driver.get("https://techcrunch.com/");

        // Find the 'The Latest' section
        WebElement theLatest = driver.findElement(By.xpath("(//div[@class='wp-block-group is-layout-flow wp-block-group-is-layout-flow'])[1]"));
        List<WebElement> articles = theLatest.findElements(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']"));

        for (WebElement article : articles) {
            // Check if the image exists
            WebElement image = article.findElement(By.xpath(".//figure[contains(@class, 'post-block__media')]//img"));
            Assert.assertTrue(image.isDisplayed(), "Image is not displayed for an article");

            // Check if the title exists
            WebElement title = article.findElement(By.xpath(".//header[contains(@class, 'post-block__header')]//h2"));
            Assert.assertTrue(title.isDisplayed(), "Title is not displayed for an article");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
