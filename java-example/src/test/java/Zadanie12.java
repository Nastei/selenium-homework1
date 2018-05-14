
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;



public class Zadanie12 {
    private WebDriver driver;

    @Before
    public void start()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void AddProduct() {
        Login();
        goToCatalog();
        driver.findElement(By.cssSelector("#content .button:nth-child(2)")).click();

        // General
        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        String name = "New product";
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(name);
        String code = "xxx123";
        driver.findElement(By.cssSelector("[name=code]")).sendKeys(code);
        driver.findElement(By.cssSelector("[name='categories[]'][value='0']")).click();
        driver.findElement(By.cssSelector("[name='categories[]'][value='1']")).click();
        driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-2']")).click();
        String quantity = "13";
        driver.findElement(By.cssSelector("[name = 'quantity']")).clear();
        driver.findElement(By.cssSelector("[name = 'quantity']")).sendKeys(quantity);
        File picture = new File("src/test/java/duck.jpg");
        String path = picture.getAbsolutePath();
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(path);
        driver.findElement(By.cssSelector("#add-new-image")).click();
        driver.findElement(By.cssSelector("#tab-general td [name=date_valid_from]")).sendKeys("01.01.2010");
        driver.findElement(By.cssSelector("#tab-general td [name=date_valid_to]")).sendKeys("01.01.2050");

        // Information
        driver.findElement(By.cssSelector(".index li:nth-child(2)")).click();
        new Select(driver.findElement(By.cssSelector("[name='manufacturer_id']"))).selectByVisibleText("ACME Corp.");
        String keywords = "New test product";
        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys(keywords);
        String sdescription = "Short test description";
        driver.findElement(By.cssSelector("[name='short_description[en]'")).sendKeys(sdescription);
        String description = "New product for test";
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(description);
        String title = "New Product";
        driver.findElement(By.cssSelector("[name='head_title[en]'")).sendKeys(title);
        String meta = "Meta description for test";
        driver.findElement(By.cssSelector("[name='meta_description[en]'")).sendKeys(meta);

        // Prices
        driver.findElement(By.cssSelector(".index li:nth-child(4)")).click();
        String price = "12.3";
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).clear();
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).sendKeys(price);
        new Select(driver.findElement(By.cssSelector("[name='purchase_price_currency_code']"))).selectByValue("EUR");
        price = "33";
        driver.findElement(By.cssSelector("[name = 'prices[EUR]']")).clear();
        driver.findElement(By.cssSelector("[name = 'prices[EUR]']")).sendKeys(price);
        driver.findElement(By.cssSelector("[name = save]")).click();

    }

    private void goToCatalog() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
    }

    private void Login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}