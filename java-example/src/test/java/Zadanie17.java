import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Zadanie17
{
    private WebDriver driver;

    @Before
    public void Start()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void task17 ()
    {
        Login();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> allset = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a"));
        List<String> products = new ArrayList<>();
        for (WebElement el : allset)
            if (el.getAttribute("href").contains("product_id"))
               products.add(el.getAttribute("innerText"));
        for (String pr : products)
        {
            driver.findElement(By.linkText(pr)).click();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }

    }

    private void Login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }
}