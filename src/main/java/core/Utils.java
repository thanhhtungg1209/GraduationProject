package core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Utils extends BaseTest{
    private static WebDriver driver;
    public Utils(WebDriver driver){
        Utils.driver = driver;
    }

    /**
     *
     * @param second
     */
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Refresh
     */
    public static void refresh(){
        driver.navigate().refresh();
        Utils.sleep(2);
    }

    /**
     *  Wait for element visible
     * @param by
     * @param timeout
     */
    public static void waitForElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            System.out.println("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    /**
     *  Wait for element clickable
     * @param by
     * @param timeout
     */
    public static void waitForElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            System.out.println("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    /**
     * Verify element display
     * @param elementXpath
     * @return
     */
    public static boolean verifyElementDisplay(String elementXpath){
        try {
            Utils.waitForElementVisible(By.xpath(""+elementXpath+""),10);
            driver.findElement(By.xpath(""+elementXpath+"")).isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
