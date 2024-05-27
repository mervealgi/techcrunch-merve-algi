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
        List<WebElement> articles = theLatest.findElements(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//div[@class='wp-block-tc23-post-picker']"));
        System.out.println(articles.size());


        for (WebElement article : articles) {
            WebElement image = article.findElement(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//img[@class='attachment-post-thumbnail size-post-thumbnail wp-post-image']"));
            Assert.assertTrue(image.isDisplayed(), "Image is not displayed for an article");
            System.out.println(image);

            WebElement author = article.findElement(By.xpath("//div[@class='wp-block-tc23-post-picker-group rapid-read-enabled rapid-read-date']//div[@class='wp-block-tc23-author-card-name has-xsmall-font-size wp-elements-d055c9e8dc404f1acd88f01867eaea50']"));
            Assert.assertTrue(author.isDisplayed(), "Title is not displayed for an article");
            System.out.println(author);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
