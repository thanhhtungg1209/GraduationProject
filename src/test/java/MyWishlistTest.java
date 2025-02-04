import core.BaseTest;
import core.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyWishlistPage;

public class MyWishlistTest extends BaseTest {
    private static int methodCount;
    @BeforeClass
    public void Init(){
        MyWishlistPage myWishlistPage = new MyWishlistPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        Utils utils = new Utils(driver);
    }

    @Test(priority = 1,description = "Kiểm tra chức năng thêm sản phẩm vào danh sách yêu thích")
    public void MW_1(){
        int i=0; methodCount=1;
        LoginPage.goToPage();
        LoginPage.login(i);
        MyWishlistPage.addMyWishlist(i);
        MyWishlistPage.goToMyWishlistPage();
        Assert.assertTrue(MyWishlistPage.isProductInWishlist(i));
    }

    @Test(priority = 2, description = "Kiểm tra chức năng xóa sản phẩm khỏi Wishlist")
    public void MW_2(){
        int i=0; methodCount=2;
        MyWishlistPage.removeMyWishlist(i);
        MyWishlistPage.goToMyWishlistPage();
        Assert.assertFalse(MyWishlistPage.isProductInWishlist(i));
    }
}
