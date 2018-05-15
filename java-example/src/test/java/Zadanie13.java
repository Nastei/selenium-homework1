import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Zadanie13 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void Start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
        // open mainpage of shop and add 3 ducks to cart in loop
        public void basket() {

        driver.get("http://localhost/litecart");
        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("#box-most-popular .product:first-child")).click();
            if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
                new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Small");
            driver.findElement(By.cssSelector(".quantity [name=add_cart_product]")).click();
            String str = String.valueOf(i);
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), str));
            driver.get("http://localhost/litecart");
        }

        driver.findElement(By.cssSelector("#cart a.link")).click();

        List<WebElement> items = driver.findElements(By.cssSelector("td.item"));
        for (int i = 0; i < items.size()-1; i++)
        {
                WebElement remove = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                remove.click();
                wait.until(stalenessOf(items.get(0)));
            }
                WebElement remove = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                remove.click();
                wait.until(stalenessOf(items.get(0)));

        }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }
}