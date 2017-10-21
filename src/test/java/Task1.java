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

public class Task1 {
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
        driver.findElement(By.xpath("(//span[contains(text(), 'Застраховать себя')])[1]")).click();

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[contains(text(), 'Страхование путешественников')]"))));
        driver.findElement(By.xpath("//a[contains(text(), 'Страхование путешественников')]")).click();

        if(driver.getTitle().contains("Страхование путешественников")) {
            //driver.findElement(By.xpath("(//a[@target='_blank'])[1]")).click();
            //wait.until((ExpectedConditions.visibilityOf(
                    //driver.findElement(By.xpath("//div[id='views']")))));
            driver.get(driver.findElement(By.xpath("(//a[@target='_blank'])[1]")).getAttribute("href"));
            WebElement webElem = driver.findElement(By.xpath("(//h3[@class='b-form-section-title'])[5]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webElem);


            driver.findElement(By.xpath("(//div[@class='b-form-prog-box'])[1]")).click();
            driver.findElement(By.xpath("//span[@class='b-continue-btn']")).click();

 /*           fillField(By.name("insured0_surname"), "AAA");
            fillField(By.name("insured0_name"), "BBB");
            fillField(By.name("insured0_birthDate"), "19.07.1999");

            driver.findElement(By.name("surname")).click();
            fillField(By.name("surname"), "йцукен");
            fillField(By.name("name"), "фывап");
            fillField(By.name("middlename"), "Вапрол");
            fillField(By.name("birthDate"), "19.10.1990");

            driver.findElement(By.xpath("//input[@name='female']")).click();
            fillField(By.name("passport_series"), "1123");
            fillField(By.name("passport_number"), "456987");
            fillField(By.name("issueDate"), "01.01.2014");
            fillField(By.name("issuePlace"), "Ghkjhdcfvulhv");

            assertEquals("AAA", driver.findElement(By.name("insured0_surname")).getAttribute("value"));
            assertEquals("BBB", driver.findElement(By.name("insured0_name")).getAttribute("value"));
            assertEquals("19.07.1999", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

            assertEquals("Йцукен", driver.findElement(By.name("surname")).getAttribute("value"));
            assertEquals("Фывап", driver.findElement(By.name("name")).getAttribute("value"));
            assertEquals("Вапрол", driver.findElement(By.name("middlename")).getAttribute("value"));
            assertEquals("19.10.1990", driver.findElement(By.name("birthDate")).getAttribute("value"));

            assertEquals("BBB", driver.findElement(By.name("insured0_name")).getAttribute("value"));
            assertEquals("1", driver.findElement(By.xpath("//input[@name='female']")).getAttribute("value"));
            assertEquals("1123", driver.findElement(By.name("passport_series")).getAttribute("value"));
            assertEquals("456987", driver.findElement(By.name("passport_number")).getAttribute("value"));
            assertEquals("01.01.2014", driver.findElement(By.name("issueDate")).getAttribute("value"));
            assertEquals("Ghkjhdcfvulhv", driver.findElement(By.name("issuePlace")).getAttribute("value"));
*/

            createFieldValues();
            driver.findElement(By.xpath("//input[@name='female']")).click();

            checkingFields();
            assertEquals("1", driver.findElement(By.xpath("//input[@name='female']")).getAttribute("value"));


            driver.findElement(By.xpath("//span[@class='b-continue-btn']")).click();
            if (driver.findElement(By.xpath("//div[text()='Заполнены не все обязательные поля']")).isDisplayed()) {
                System.out.println("COMPLETED");
            }
            else
                System.out.println("Something going wrong :(");

        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private void fillField(By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    private void createFieldValues(){
        pathAndField.put("insured0_surname", "AName");
        pathAndField.put("insured0_name", "BBB");
        pathAndField.put("insured0_birthDate", "19.07.1999");
        pathAndField.put("surname", "Йцукен");
        pathAndField.put("name", "Фывап");
        pathAndField.put("middlename", "Вапрол");
        pathAndField.put("birthDate", "19.10.1990");
        pathAndField.put("passport_series", "1123");
        pathAndField.put("passport_number", "456987");
        pathAndField.put("issueDate", "01.01.2014");
        pathAndField.put("issuePlace", "Ghkjhdcfvulhv");



        for (Map.Entry<String, String> entry : pathAndField.entrySet())
            fillField(By.name(entry.getKey()), entry.getValue());

    }

    private void checkingFields(){

        for(Map.Entry<String, String> entry : pathAndField.entrySet())
            assertEquals(entry.getValue(), driver.findElement(By.name(entry.getKey())).getAttribute("value"));


    }
}
