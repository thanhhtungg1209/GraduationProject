import core.BaseTest;
import core.Utils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;

public class CheckOutTest extends BaseTest {
    private static int methodCount;

    @BeforeClass
    public void Init(){
        CartPage cartPage = new CartPage(driver);
        Utils utils = new Utils(driver);
    }

    @Test(priority = 5, description = "Đảm bảo rằng người dùng không thể tiến hành thanh toán khi giỏ hàng trống.")
    public void THTT_05(){
        CartPage.goToCart();
        Utils.verifyElementDisplay("//h2[contains(normalize-space(),'Your cart is empty')]");
    }
}
