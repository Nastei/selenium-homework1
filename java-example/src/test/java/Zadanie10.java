
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

public class Zadanie10 {
    private WebDriver driver;

    @Before
    public void start()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void CheckRightPageOfItems()
    {
        driver.get("http://localhost/litecart/");
        WebElement main = driver.findElement(By.cssSelector("#box-campaigns .price-wrapper"));
        String titleOnMain = driver.findElement(By.cssSelector("#box-campaigns .product .name")).getAttribute("textContent");
        String regularPrice = main.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String campaignPrice = main.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String colorRegularPrice = main.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String colorCampaignPrice = main.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String decorRegularPrice = main.findElement(By.cssSelector(".regular-price")).getTagName();
        String decorCampaignPrice = main.findElement(By.cssSelector(".campaign-price")).getTagName();
        String regularPriceFontSize = main.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String campaignPriceFontSize = main.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");

        driver.findElement(By.cssSelector("#box-campaigns .link")).click();
        String titleOnProductPage = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");
        WebElement product = driver.findElement(By.cssSelector("#box-product .price-wrapper"));
        String productRegularPrice = product.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String productCampaignpPrice = product.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");
        String productColorRegularPrice = product.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String productColorCampaignPrice = product.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String decorProductRegularPrice = product.findElement(By.cssSelector(".regular-price")).getTagName();
        String decorProductCampaignPrice = product.findElement(By.cssSelector(".campaign-price")).getTagName();
        String productRegularPriceFontSize = product.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String productCampaignPriceFontSize = product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");


       //  а) на главной странице и на странице товара совпадает текст названия товара
        Assert.assertTrue(titleOnMain.equals(titleOnProductPage));
        // б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        Assert.assertTrue(regularPrice.equals(productRegularPrice));
        Assert.assertTrue(campaignPrice.equals(productCampaignpPrice));
        // в) обычная цена серая и зачёркнутая, а акционная цена красная и жирная
        Assert.assertTrue(colorRegularPrice.equals("rgba(119, 119, 119, 1)"));
        Assert.assertTrue(decorRegularPrice.equals("s"));
        Assert.assertTrue(colorCampaignPrice.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(decorCampaignPrice.equals("strong"));

        Assert.assertTrue(productColorRegularPrice.equals("rgba(102, 102, 102, 1)"));
        Assert.assertTrue(decorProductRegularPrice.equals("s"));
        Assert.assertTrue(productColorCampaignPrice.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(decorProductCampaignPrice.equals("strong"));

        // г) акционная цена крупнее, чем обычная
        Assert.assertTrue(Double.parseDouble(regularPriceFontSize.replace("px","")) < Double.parseDouble(campaignPriceFontSize.replace("px","")));
        Assert.assertTrue(Double.parseDouble(productRegularPriceFontSize.replace("px","")) < Double.parseDouble(productCampaignPriceFontSize.replace("px","")));

    }

    @After
    public void Stop()
    {
        driver.quit();
        driver = null;
    }

}