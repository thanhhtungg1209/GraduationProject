package pages;

import config.ConfigManager;
import core.Config;
import core.Utils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MyWishlistPage {
    private static WebDriver driver;
    public static String urlMyWishlistPage = "https://demo-magento2.vuestorefront.io/default/customer/my-wishlist";
    public static Config[] myWishlistConfigs = ConfigManager.readTestObjectsFromJson("src/main/resources/wishlist.json");

    public MyWishlistPage(WebDriver driver){
        MyWishlistPage.driver = driver;
    }

    public static void goToMyWishlistPage(){
        driver.get(urlMyWishlistPage);
        Utils.sleep(2);
    }

    /**
     * add to my wishlist
     * @param i
     */
    public static void addMyWishlist(int i){
        driver.get(myWishlistConfigs[i].getUrl());
        Utils.sleep(2);
        Utils.scrollToElement("//button[normalize-space()='Add to Wishlist']");
        Utils.clickOnElement("//button[normalize-space()='Add to Wishlist']");
        Utils.sleep(2);
    }

    /**
     * remove my wishlist
     * @param i
     */
    public static void removeMyWishlist(int i){
        driver.get(myWishlistConfigs[i].getUrl());
        Utils.sleep(2);
        Utils.scrollToElement("//button[contains(text(),'Remove from Wishlist')]");
        Utils.clickOnElement("//button[contains(text(),'Remove from Wishlist')]");
        Utils.sleep(2);
    }

    public static boolean isProductInWishlist(int i){
        try{
            Utils.verifyElementDisplay("//span[contains(text(),'"+myWishlistConfigs[i].getName()+"')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
