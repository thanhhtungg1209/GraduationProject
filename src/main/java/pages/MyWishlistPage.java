package pages;

import core.Utils;
import org.openqa.selenium.WebDriver;

public class MyWishlistPage {
    private static WebDriver driver;
    public static String urlMyWishlistPage = "https://demo-magento2.vuestorefront.io/default/customer/my-wishlist";

    public MyWishlistPage(WebDriver driver){
        MyWishlistPage.driver = driver;
    }

    public static void goToMyWishlistPage(){
        driver.get(urlMyWishlistPage);
        Utils.sleep(2);
    }
}
