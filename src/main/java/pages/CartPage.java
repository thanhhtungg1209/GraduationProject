package pages;

import core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CartPage {
    private static WebDriver driver;
    private static String linkCartPage = "https://demo-magento2.vuestorefront.io/default/cart";

    public CartPage(WebDriver driver){
        CartPage.driver = driver;
    }

    /**
     * Go to cart
     */
    public static void goToCart(){
        driver.get(linkCartPage);
        Utils.sleep(2);
    }

    /**
     * Check product in cart
     * @return
     */
    public static boolean checkProductInCart(String nameProduct){
        try {
            Utils.verifyElementDisplay("//a[normalize-space()='"+nameProduct+"']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Get order total
     */
    public static double getOrderTotal(){
        WebElement priceElement  = driver.findElement(By.xpath("//span[@class='sf-price__regular']"));
        String priceText = priceElement.getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    /**
     * Remove product from cart
     */
    public static void removeProductFromCart(){
        Actions actions = new Actions(driver);
        WebElement iconRemove = driver.findElement(By.xpath("//span[@class='sf-circle-icon__icon sf-icon']//*[name()='svg']"));
        actions.moveToElement(iconRemove).perform();
        iconRemove.click();
        Utils.sleep(2);
        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
        Utils.sleep(2);
    }
}
