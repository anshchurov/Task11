import java.util.HashMap;
import java.util.Map;
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

public class Task2 {
    private WebDriver driver;
    private String baseUrl;
    private Map<String, String> pathAndField = new HashMap<String, String>();


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
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("(//span[@class='region-list__name'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//A[contains(text(), 'Нижегородская область')]"))));
        driver.findElement(By.xpath("//A[contains(text(), 'Нижегородская область')]")).click();

        WebElement webElem = driver.findElement(By.xpath("//ul[@class='social__list']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", webElem);

        // Проверка наличия значков соц. сетей
        assertEquals("https://www.facebook.com/bankdruzey", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_fb']/parent::a")).getAttribute("href"));
        assertEquals("http://twitter.com/sberbank/", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_tw']/parent::a")).getAttribute("href"));
        assertEquals("http://www.youtube.com/sberbank", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_yt']/parent::a")).getAttribute("href"));
        assertEquals("http://instagram.com/sberbank", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_ins']/parent::a")).getAttribute("href"));
        assertEquals("http://vk.com/sberbank", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_vk']/parent::a")).getAttribute("href"));
        assertEquals("https://ok.ru/sberbank", driver.findElement(By.xpath("//span[@class='social__icon social__icon_type_ok']/parent::a")).getAttribute("href"));

        System.out.println("COMPLETED");

        Thread.sleep(10000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
