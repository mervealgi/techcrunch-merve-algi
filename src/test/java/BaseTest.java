import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

        // "The Latest" bölümündeki makaleleri bulun
        WebElement theLatest = driver.findElement(By.xpath("(//div[@class='wp-block-group is-layout-flow wp-block-group-is-layout-flow'])[1]"));
        List<WebElement> articles = theLatest.findElements(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//div[@class='wp-block-tc23-post-picker']"));
        System.out.println(articles.size());

        // Check the articles has img and author
        for (WebElement article : articles) {
            WebElement image = article.findElement(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//img[@class='attachment-post-thumbnail size-post-thumbnail wp-post-image']"));
            Assert.assertTrue(image.isDisplayed(), "Image is not displayed for an article");

            WebElement author = article.findElement(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//div[@class='wp-block-tc23-author-card-name has-xsmall-font-size wp-elements-d055c9e8dc404f1acd88f01867eaea50']"));
            Assert.assertTrue(author.isDisplayed(), "Title is not displayed for an article");
        }

        // Check the article list is empty or not
        if (articles.isEmpty()) {
            System.out.println("No articles found in 'The Latest' section.");
        } else {
            // Create an index for random selection
            Random rand = new Random();
            int randomIndex = rand.nextInt(articles.size());

            // Keep the random selection's title and url
            WebElement randomArticle = articles.get(randomIndex);
            String randomArticleTitle = randomArticle.findElement(By.cssSelector("h2")).getText();
            String randomArticleUrl = randomArticle.findElement(By.cssSelector("a")).getAttribute("href");


            // Print the news title
            // System.out.println("Selected Article Title: " + randomArticle.findElement(By.cssSelector("h2")).getText());

            // Click to random news and wait to appear
            randomArticle.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        }

    }

        @AfterClass
        public void tearDown () {
            if (driver != null) {
                driver.quit();
            }
        }
    }