import core.BaseTest;
import core.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogoutTest extends BaseTest {
    private static int methodCount;

    @BeforeClass
    public void Init(){
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);
        Utils utils = new Utils(driver);
    }

    @Test(priority = 1,description = "Đảm bảo rằng người dùng có thể đăng xuất thành công sau khi đăng nhập")
    public void DX_1(){
        methodCount=1; int i=0;
        LoginPage.goToPage();
        LoginPage.login(i);
        ProfilePage.goToProfile();
        ProfilePage.logOut();
        Assert.assertTrue(ProfilePage.checkLogOutSuccess());
    }

    @Test(priority = 2,description = "Đảm bảo rằng sau khi đăng xuất, phiên người dùng không còn tồn tại và không thể truy cập vào các trang yêu cầu đăng nhập.")
    public void DX_2(){
        methodCount=2;
        ProfilePage.goToProfile();
        Assert.assertTrue(ProfilePage.checkLogOutSuccess());
    }

    @Test(priority = 3,description = "Đảm bảo rằng khi người dùng đăng xuất ở một tab trình duyệt, tất cả các tab khác cũng sẽ bị đăng xuất.")
    public void DX_3(){
        methodCount=3;int i=0;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        LoginPage.goToPage();
        LoginPage.login(i);
        String parent_window = driver.getWindowHandle();
        js.executeScript("window.open();");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        ProfilePage.goToProfile();
        driver.switchTo().window(parent_window);
        ProfilePage.goToProfile();
        ProfilePage.logOut();
        driver.switchTo().window(tabs.get(1));
        ProfilePage.goToProfile();
        Assert.assertTrue(ProfilePage.checkLogOutSuccess());
        driver.close();
        driver.switchTo().window(parent_window);
    }

    @Test(priority = 4,description = "Đảm bảo rằng khi người dùng đăng xuất, các sản phẩm trong giỏ hàng vẫn được giữ lại.")
    public void DX_4(){
        methodCount=4;int i=0;
        HomePage.goToPage();
        LoginPage.login(i);
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']");
        ProfilePage.goToProfile();
        ProfilePage.logOut();
        LoginPage.login(i);
        CartPage.goToCart();
        Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']");
    }

    @Test(priority = 5,description = "Đảm bảo rằng sau khi đăng xuất, người dùng không thể sử dụng nút \"Back\" của trình duyệt để truy cập vào các trang yêu cầu đăng nhập.")
    public void DX_5(){
        methodCount=5;int i=0;
        HomePage.goToPage();
        LoginPage.login(i);
        ProfilePage.goToProfile();
        ProfilePage.logOut();
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"Home Page");
    }

    @AfterMethod
    public void afterMethod(){
        List<Integer> methodCounts = Arrays.asList(4);
        if (methodCounts.contains(methodCount)){
            CartPage.removeProductFromCart();
            ProfilePage.goToProfile();
            ProfilePage.logOut();
        }
    }
}
