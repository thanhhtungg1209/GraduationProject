package pages;

import config.ConfigManager;
import core.Config;
import core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public static Config[] webConfigs = ConfigManager.readTestObjectsFromJson("src/main/resources/config.json");
    public static Config[] login = ConfigManager.readTestObjectsFromJson("src/main/resources/login.json");

    private static WebDriver driver;
    public LoginPage(WebDriver driver){
        LoginPage.driver = driver;
    }

    public static void goToPage(){
        driver.get(webConfigs[0].getBaseUrl());
        Utils.sleep(2);
    }

    public static void login(int i){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@aria-label='Account']//span[@aria-label='Account']")));
        Utils.sleep(2);
        Utils.waitForElementVisible(By.xpath("//input[@name='email']"),10);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(login[i].getEmail());
        Utils.waitForElementVisible(By.xpath("//input[@name='password']"),10);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(login[i].getPassword());
        Utils.waitForElementClickable(By.xpath("//div[contains(text(),'Login')]"),10);
        driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
        Utils.sleep(3);
    }

    /**
     * Check login success
     * @return
     */
    public static boolean checkLoginSuccess(){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@aria-label='Account']//span[@aria-label='Account']")));
            Utils.sleep(2);
            Utils.verifyElementDisplay("//h1[normalize-space()='My Account']");
            System.out.println("Success");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check hien thi thong bao loi khi nhap email khong hop le
     * @return
     */
    public static boolean checkInvalidEmailError(){
        try {
            Utils.verifyElementDisplay("//div[@id='email-error']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check hien thi thong bao loi khi nhap sai password
     * @return
     */
    public static boolean checkInvalidPasswordError(){
        try {
            Utils.verifyElementDisplay("//span[text()='The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check hien thi thong bao loi khi nhap sai email va password
     * @return
     */
    public static boolean checkInvalidError(){
        try {
            Utils.verifyElementDisplay("//span[text()='The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check hien thi thong bao khi khong nhap thong tin email va password
     * @return
     */
    public static boolean checkRequired(){
        try {
            Utils.verifyElementDisplay("//div[contains(text(),'This field is required')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check khoa tai khoan khi nhap sai password nhieu lan
     * @return
     */
    public static boolean checkLockAccount(){
        try {
            Utils.verifyElementDisplay("//div[contains(text(),'Your account is locked due to multiple failed login attempts. Please try again later or contact support.')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
