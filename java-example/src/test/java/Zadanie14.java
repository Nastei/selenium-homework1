import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Zadanie14 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void links(){
        Login();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.cssSelector(".button")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("form [target='_blank']"));

        for (WebElement el : links){
            String main = driver.getWindowHandle();
            Set <String> oldWindows = driver.getWindowHandles();
            el.click();
            String newWindow = wait.until(anyNewWindow(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(main);
        }

    }

    private void Login() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    ExpectedCondition<String> anyNewWindow(Set<String> oldWindows)
    {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}