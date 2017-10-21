import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task1 {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
        baseUrl = "http://www.sberbank.ru/ru/person/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void insuranceTask() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("(//span[contains(text(), 'Застраховать себя')])[1]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[contains(text(), 'Страхование путешественников')]"))));
        driver.findElement(By.xpath("//a[contains(text(), 'Страхование путешественников')]")).click();

        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text(), 'Оформить онлайн'"))));
        if(driver.getTitle() == "Страхование путешественников") {
            driver.findElement(By.xpath("//span[text(), 'Оформить онлайн'")).click();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
