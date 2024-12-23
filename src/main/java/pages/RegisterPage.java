package pages;

import config.ConfigManager;
import core.Config;
import core.Utils;
import org.openqa.selenium.*;

public class RegisterPage {
    public static Config[] webConfigs = ConfigManager.readTestObjectsFromJson("src/main/resources/config.json");
    public static Config[] registerConfigs = ConfigManager.readTestObjectsFromJson("src/main/resources/register.json");
    private static final String emailXpath = "//input[@name='email']";
    private static final String firstNameXpath = "//input[@name='first-name']";
    private static final String lastNameXpath = "//input[@name='last-name']";
    private static final String passwordXpath = "//input[@name='password']";
    private static final String createButtonXpath = "//div[contains(text(),'Create an account')]";

    private static WebDriver driver;
    public RegisterPage(WebDriver driver){
        RegisterPage.driver = driver;
    }

    /**
     * Điều hướng đến trang chủ
     */
    public static void goToPage() {
        driver.get(webConfigs[0].getBaseUrl());
        Utils.sleep(2);
    }

    /**
     * Nhập thông tin form Đăng ký tài khoản
     */
    public static void formRegister(int i) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@aria-label='Account']//span[@aria-label='Account']")));
        Utils.sleep(2);
        Utils.waitForElementClickable(By.xpath("//button[normalize-space()='Register today']"), 10);
        driver.findElement(By.xpath("//button[normalize-space()='Register today']")).click();
        Utils.waitForElementVisible(By.xpath(emailXpath), 10);
        driver.findElement(By.xpath(emailXpath)).sendKeys(registerConfigs[i].getEmail());
        Utils.waitForElementClickable(By.xpath(firstNameXpath), 10);
        driver.findElement(By.xpath(firstNameXpath)).sendKeys(registerConfigs[i].getFirstName());
        Utils.waitForElementClickable(By.xpath(lastNameXpath), 10);
        driver.findElement(By.xpath(lastNameXpath)).sendKeys(registerConfigs[i].getLastName());
        Utils.waitForElementVisible(By.xpath(passwordXpath), 10);
        driver.findElement(By.xpath(passwordXpath)).sendKeys(registerConfigs[i].getPassword());
        driver.findElement(By.xpath("//span[contains(text(),'Sign Up for Newsletter')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'I want to create an account')]")).click();
    }

    /**
     * Click button Creat
     */
    public static void clickCreatButton(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement creatButton = driver.findElement(By.xpath(createButtonXpath));
        js.executeScript("arguments[0].click();",creatButton);
    }

    /**
     * Check đăng ký không thành công
     * @return
     */
    public static boolean checkRegisterFail(){
        try {
            Utils.verifyElementDisplay("//span[contains(text(),'A customer with the same email address already exists in an associated website.')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check email invalid
     * @return
     */
    public static boolean checkEmailInvalid(){
        try{
            Utils.verifyElementDisplay("//div[contains(text(),'Invalid email')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Check password invalid
     * @return
     */
    public static boolean checkPasswordInvalid(){
        try {
            Utils.verifyElementDisplay("//div[contains(text(),'The password must be at least 8 characters long and must contain at least: 1 uppercase or lowercase letter, 1 number, or one special character (E.g. , . _ & ? etc)')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
