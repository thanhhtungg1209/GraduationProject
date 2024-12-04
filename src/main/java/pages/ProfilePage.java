package pages;

import core.Utils;
import org.openqa.selenium.*;
import org.testng.Assert;

public class ProfilePage {
    private static WebDriver driver;
    public static String linkProfilePage = "https://demo-magento2.vuestorefront.io/default/customer/my-profile";

    public ProfilePage(WebDriver driver){
        ProfilePage.driver = driver;
    }

    public static void goToProfile(){
        driver.get(linkProfilePage);
        Utils.sleep(2);
    }

    /**
     * Log out
     */
    public static void logOut(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement logOutElement = driver.findElement(By.xpath("//span[contains(text(),'Log out')]"));
        js.executeScript("arguments[0].click();",logOutElement);
        Utils.sleep(2);
    }

    /**
     * Check log out success
     * @return
     */
    public static boolean checkLogOutSuccess(){
        try{
            Assert.assertEquals(driver.getTitle(),"Home Page");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@aria-label='Account']//span[@aria-label='Account']")));
            Utils.sleep(2);
            Utils.verifyElementDisplay("//input[@name='email']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
