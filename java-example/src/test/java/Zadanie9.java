
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;


@SuppressWarnings("ALL")
public class Zadanie9 {
    private WebDriver driver;


    @Before
    public void start()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(230, TimeUnit.SECONDS);
    }
    boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Test
    public void CheckOrderOfCountries() {
        Login();
        OpenCountriesPage();

        String getCountries = "td:nth-child(5) a";
        List<WebElement> elements = driver.findElements(By.cssSelector(getCountries));
        ArrayList<String> list = new ArrayList<String>();
        for(WebElement el : elements )
        {
            String str = el.getAttribute("textContent");
            list.add(str);
        }
//list.add(3, "USA"); // add wrong country in the list to test
        Boolean res = true;
        for (int i = 0; i <list.size()-1; i++)
        {
            if (list.get(i+1).compareTo(list.get(i)) < 0)
            {
                res = false;
                break;
            }
        }
        assertTrue(res);

    }


    @Test
    public void CheckZonesInsideOfCountries()
    {
        Login();
        OpenCountriesPage();

        ArrayList<String> list = new ArrayList<String>();

        List<WebElement> rows = driver.findElements(By.cssSelector(".row"));
        String getZones = "td:nth-child(6)";

        for(int i = 0; i < rows.size(); i++)
        {
            WebElement row = driver.findElements(By.cssSelector(".row")).get(i);
            String zone = row.findElement(By.cssSelector(getZones)).getAttribute("textContent");

            if (!zone.equals("0"))
            {
                String country = row.findElement(By.cssSelector("td:nth-child(5)")).getAttribute("textContent");
                By href = By.linkText(country);
                row.findElement(href).click();

                List<WebElement> zoneNames = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3) [type=hidden]"));
                for (WebElement z : zoneNames) {
                    String str = z.getAttribute("textContent");
                    list.add(str);
                }


                Boolean res = true;
                for (int j = 0; j < list.size() - 1; j++) {
                    if (list.get(j + 1).compareTo(list.get(j)) < 0) {
                        res = false;
                        break;
                   }
                }
                assertTrue(res);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
        }
    }

     @Test
     public void CheckGeoZonesOrder() {
         Login();
         OpenGeoZonesPage();
         String getRows = ".dataTable tr td:nth-child(3)";
         List<WebElement> elems = driver.findElements(By.cssSelector(getRows));
         ArrayList<String> countries = new ArrayList<String>();

         for (WebElement el : elems) {
             countries.add(el.getAttribute("textContent"));
         }

         for (String each : countries) {
             driver.findElement(By.linkText(each)).click();
             elems = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3) select option:checked"));
             ArrayList<String> zones = new ArrayList<String>();

             for (WebElement el : elems) {
                 zones.add(el.getAttribute("textContent"));
             }

             List<String> oldZones = zones;
             Collections.sort(zones);
             Assert.assertEquals(oldZones,zones);
             driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
         }
     }

    private void Login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
    private void OpenCountriesPage() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }
    private void OpenGeoZonesPage() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

