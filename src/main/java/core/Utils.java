package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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
     * @param timeout: thời gian tối đa có thể chờ để phần tử hiển thị trong DOM
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
     * @param timeout:thời gian tối đa có thể chờ để phần tử hiển thị trong DOM và có thể click
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

    /**
     * Hover qua element
     * @param element
     */
    public static void hoverOverElement(String element){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(element))).perform();
    }

    /**
     * Click element
     * @param element
     */
    public static void clickOnElement(String element){
        try {
            driver.findElement(By.xpath(element)).click();
        } catch (Exception e) {
            System.out.println("Unable to click on element: " + e.getMessage());
        }
    }

    /**
     * Phương thức kiểm tra xem phần tử có bị vô hiệu hóa hay không
     * @param element
     * @return
     */
    public static boolean isElementDisabled(String element){
        return !driver.findElement(By.xpath(element)).isEnabled();
        // Trả về true nếu phần tử bị vô hiệu hóa, false nếu không
    }

    /**
     * Method cuộn đến phần tử bằng cách sử dụng JavascriptExecutor
     * @param element
     */
    public static void scrollToElement(String element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(By.xpath(element)));
        sleep(2);
    }
}
