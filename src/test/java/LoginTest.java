import core.BaseTest;
import core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(LoginTest.class);
    private static int methodCount;

    @BeforeClass
    public void Init(){
        LoginPage loginPage = new LoginPage(driver);
        Utils utils = new Utils(driver);
        LoginPage.goToPage();
        log.info("Test case started");
    }

    @Test(priority = 1, description = "Đảm bảo người dùng có thể đăng nhập thành công khi cung cấp thông tin hợp lệ")
    public void DN_1(){
        methodCount = 1;int i=0;
        log.info("Starting test case: DN_1");
        LoginPage.login(i);
        Assert.assertTrue(LoginPage.checkLoginSuccess());
        log.info("Test case DN_1 passed");
    }

    @Test(priority = 2, description = "Đảm bảo hệ thống không cho phép đăng nhập với địa chỉ email không hợp lệ.")
    public void DN_2(){
        methodCount = 2;int i=1;
        Utils.refresh();
        log.info("Starting test case: DN_2");
        LoginPage.login(i);
        Assert.assertTrue(LoginPage.checkInvalidEmailError());
        log.info("Test case DN_2 passed");
    }

    @Test(priority = 3, description = "Đảm bảo hệ thống không cho phép đăng nhập khi mật khẩu không đúng")
    public void DN_3(){
        methodCount = 3;int i=2;
        Utils.refresh();
        LoginPage.login(i);
        Assert.assertTrue(LoginPage.checkInvalidPasswordError());
    }

    @Test(priority = 4, description = "Đảm bảo hệ thống không cho phép đăng nhập khi cả email và mật khẩu đều sai.")
    public void DN_4(){
        methodCount = 4; int i=3;
        Utils.refresh();
        LoginPage.login(i);
        Assert.assertTrue(LoginPage.checkInvalidError());
    }

    @Test(priority = 5, description = "Đảm bảo hệ thống không cho phép đăng nhập khi không nhập thông tin gì.")
    public void DN_5(){
        methodCount = 5; int i=4;
        Utils.refresh();
        LoginPage.login(i);
        Assert.assertTrue(LoginPage.checkRequired());
    }

    @AfterMethod
    public void afterMethod(){
        List<Integer> methodCounts = Arrays.asList(1);
        if (methodCounts.contains(methodCount)){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement logOut = driver.findElement(By.xpath("//span[normalize-space()='Log out']"));
            js.executeScript("arguments[0].click();",logOut);
            Utils.sleep(2);
        }
    }
}
