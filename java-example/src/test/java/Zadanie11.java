
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class Zadanie11 {
    private WebDriver driver;

    @Before
    public void start()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void registrationOfCustomer()
    {
        driver.get("http://localhost/litecart/");

        WebElement newCustomer = driver.findElement(By.cssSelector("#box-account-login td a"));
        newCustomer.click();


        String firstName = "Name";
        String lastName = "Lastname";
        String address = "Test address ";
        String postcode = "12345";
        String city = "Test city";
        String country = "United States";
        String email = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + "@gmail.com";
        String phone = "1234567";
        String password = "qweqweq1";

        WebElement fillform = driver.findElement(By.cssSelector("#create-account .content"));
        fillform.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstName);
        fillform.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastName);
        fillform.findElement(By.cssSelector("[name=address1]")).sendKeys(address);
        fillform.findElement(By.cssSelector("[name=postcode]")).sendKeys(postcode);
        fillform.findElement(By.cssSelector("[name=city]")).sendKeys(city);
        fillform.findElement(By.cssSelector("[name=country_code]")).sendKeys(country);
        fillform.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        fillform.findElement(By.cssSelector("[name=phone]")).sendKeys(phone);
        fillform.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        fillform.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        fillform.findElement(By.cssSelector("button")).click();

        driver.findElement(By.cssSelector(".account li:nth-child(5) a")).click();

        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(3)")).sendKeys(email);
        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(2)")).sendKeys(password);
        driver.findElement(By.cssSelector("#box-account-login td .button-set [name='login']")).click();

    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}