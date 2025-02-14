package pages;

import config.ConfigManager;
import core.Config;
import core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public static Config[] webConfigs = ConfigManager.readTestObjectsFromJson("src/main/resources/config.json");
    public static Config[] searchInput = ConfigManager.readTestObjectsFromJson("src/main/resources/search.json");
    private static WebDriver driver;

    public HomePage(WebDriver driver){
        HomePage.driver = driver;
    }

    /**
     * Go to home page
     */
    public static void goToPage(){
        driver.get(webConfigs[0].getBaseUrl());
        Utils.sleep(2);
    }

    /**
     * Search
     * @param i
     */
    public static void searchName(int i){
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(searchInput[i].getValue());
        Utils.sleep(2);
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.ENTER);
        Utils.sleep(2);
    }

    public static void searchDescription(int i){
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(searchInput[i].getDescription());
        Utils.sleep(2);
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(Keys.ENTER);
        Utils.sleep(2);
    }

    public static boolean isSearchSuccessfulWithValidInput(int i){
        try {
            Utils.verifyElementDisplay("//span[contains(text(),'"+searchInput[i].getValue()+"')]");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public static boolean isSearchSuccessfulWithInValidInput(){
        try {
            Utils.verifyElementDisplay("//span[@aria-label='Error']//*[name()='svg']");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
