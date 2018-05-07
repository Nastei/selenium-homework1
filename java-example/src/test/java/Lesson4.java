
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

import static jdk.nashorn.internal.objects.NativeArray.forEach;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


@SuppressWarnings("ALL")
public class Lesson4 {
    private WebDriver driver;


    @Before
    public void start()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Test
    public void LoginToLitecart() {
        Login();
        ClickToAllAdminSections();
    }

    private void Login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }



    public void ClickToAllAdminSections()
    {
        List<WebElement> elements = driver.findElements(By.cssSelector("li#app- > a span.name"));

        for (int i = 0; i < elements.size(); i++)
        {
            driver.findElements(By.cssSelector("li#app- > a span.name")).get(i).click();

            for (int j = 0; j < driver.findElements(By.cssSelector("li#app- > ul.docs span.name")).size(); j++)
            {
                driver.findElements(By.cssSelector("ul.docs span.name")).get(j).click();
                assertTrue(isElementPresent(By.cssSelector("h1")));
            }
        }
    }

        @Test
        public void GoToMainPage()
        {
            driver.get("http://localhost/litecart/en/");
            CheckAllDucksHaveSticker();
        }

        public void CheckAllDucksHaveSticker() {
            List<WebElement> allDucks = driver.findElements(By.cssSelector(".product"));
            for (WebElement duck: allDucks) {
                Assert.assertTrue(duck.findElements(By.cssSelector(".sticker")).size() == 1);
            }
    }


    @After
        public void stop() {
            driver.quit();
            driver = null;
        }
    }

