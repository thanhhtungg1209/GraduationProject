import core.BaseTest;
import core.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class SearchTest extends BaseTest {
    private static int methodCount;
    @BeforeClass
    public void Init(){
        HomePage homePage = new HomePage(driver);
        Utils utils = new Utils(driver);
        HomePage.goToPage();
    }

    @Test(priority = 1, description = "Kiểm tra xem sản phẩm hiển thị đúng khi tìm kiếm bằng từ khóa chính xác")
    public void TKSP_1(){
        methodCount=1; int i=0;
        HomePage.searchName(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithValidInput(i));
    }

    @Test(priority = 2, description = "Kiểm tra hệ thống xử lý khi tìm kiếm với từ khóa không chính xác")
    public void TKSP_2(){
        methodCount=2; int i=1;
        Utils.refresh();
        HomePage.searchName(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithInValidInput());
    }

    @Test(priority = 3, description = "Kiểm tra tìm kiếm với từ khóa có ký tự đặc biệt.")
    public void TKSP_3(){
        methodCount=3; int i=2;
        Utils.refresh();
        HomePage.searchName(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithInValidInput());
    }

    @Test(priority = 4, description = "Kiểm tra khả năng tìm kiếm với từ khóa dài.")
    public void TKSP_4(){
        methodCount=4; int i=3;
        Utils.refresh();
        HomePage.searchDescription(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithValidInput(i));
    }

    @Test(priority = 5, description = "Kiểm tra xem tìm kiếm có phân biệt chữ hoa và chữ thường hay không")
    public void TKSP_5(){
        methodCount=5; int i=4;
        Utils.refresh();
        HomePage.searchDescription(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithValidInput(i));
    }

    @Test(priority = 6, description = "Kiểm tra khả năng tìm kiếm với từ khóa chứa số.")
    public void TKSP_6(){
        methodCount=6; int i=5;
        Utils.refresh();
        HomePage.searchName(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithInValidInput());
    }

    @Test(priority = 7, description = "Kiểm tra xem có hiển thị nhiều sản phẩm khi tìm kiếm với từ khóa chung chung.")
    public void TKSP_7(){
        methodCount=7; int i=6;
        Utils.refresh();
        HomePage.searchName(i);
        Assert.assertTrue(HomePage.isSearchSuccessfulWithValidInput(3));
        Assert.assertTrue(HomePage.isSearchSuccessfulWithValidInput(7));
    }
}
