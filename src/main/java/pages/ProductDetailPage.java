package pages;

import config.ConfigManager;
import core.Config;
import core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailPage {
    private static WebDriver driver;
    public static Config[] searchInput = ConfigManager.readTestObjectsFromJson("src/main/resources/search.json");
    public static String linkProductDetail1 = "https://demo-magento2.vuestorefront.io/default/olivia-1-4-zip-light-jacket.html?OTM%3D=Y29uZmlndXJhYmxlLzkzLzQ5&MTQ0=Y29uZmlndXJhYmxlLzE0NC8xNjY%3D";
    public static String linkProductDetail2 = "https://demo-magento2.vuestorefront.io/default/riona-full-zip-jacket.html";
    private static String linkCartPage = "https://demo-magento2.vuestorefront.io/default/cart";
    public static String nameProduct1 = "Olivia 1/4 Zip Light Jacket";
    public static String nameProduct2 = "Riona Full Zip Jacket";

    public ProductDetailPage(WebDriver driver){
        ProductDetailPage.driver = driver;
    }

    /**
     * Go to product detail by link product
     */
    public static void navigateToProductDetailByLink(String linkProductDetail){
        driver.get(linkProductDetail);
        Utils.sleep(2);
    }

    /**
     * Go to product detail by name product
     * @param i
     */
    public static void navigateToProductDetailByName(int i){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement productNameElement = driver.findElement(By.xpath("//span[contains(text(),'"+searchInput[i].getValue()+"')]"));
        js.executeScript("arguments[0].click();",productNameElement);
        Utils.sleep(2);
    }

    /**
     * Get product price
     */
    public static double getProductPrice(){
        WebElement priceElement  = driver.findElement(By.xpath("//div[@class='sf-price']"));
        String priceText = priceElement.getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    /**
     * Select color product
     * @param code
     */
    public static void selectColorProduct(String code){
        driver.findElement(By.xpath("//button[@style='--color-background: "+code+";']")).click();
        Utils.sleep(1);
    }

    /**
     * Select size product
     * @param size
     */
    public static void selectSizeProduct(String size){
        Select selectBox = new Select(driver.findElement(By.xpath("//select[@id='Size']")));
        selectBox.selectByVisibleText(size);
        Utils.sleep(1);
    }

    /**
     * Add to cart
     */
    public static void addToCart(){
        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        Utils.sleep(2);
    }
}
