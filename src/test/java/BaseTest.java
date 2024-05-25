import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/mervealgi/IdeaProjects/techcrunch-merve-algi/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
