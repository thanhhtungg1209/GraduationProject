import core.BaseTest;
import core.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;

import java.util.Arrays;
import java.util.List;

public class AddToCartTest extends BaseTest {
    private static int methodCount;
    @BeforeClass
    public void Init(){
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        Utils utils = new Utils(driver);
    }

    @Test(priority = 1,description = "Kiểm tra xem sản phẩm được thêm vào giỏ hàng khi nhấn nút \"Add to carrt\" trên trang chi tiết sản phẩm")
    public void TSPVGH_1(){
        methodCount =1;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']"));
    }

    @Test(priority = 2,description = "Kiểm tra khả năng thêm sản phẩm với các tùy chọn khác nhau (màu sắc, kích thước, v.v.).\n")
    public void TSPVGH_2(){
        methodCount =2;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail2);
        ProductDetailPage.selectColorProduct("#ff0000");
        ProductDetailPage.selectSizeProduct("XS");
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct2+"']"));
        Assert.assertTrue(Utils.verifyElementDisplay("//span[normalize-space()='XS']"));
        Assert.assertTrue(Utils.verifyElementDisplay("//span[normalize-space()='Red']"));
    }

    @Test(priority = 3,description = "Kiểm tra khả năng thêm nhiều sản phẩm vào giỏ hàng liên tiếp.")
    public void TSPVGH_3(){
        methodCount =3;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']"));
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct2+"']"));
        CartPage.removeProductFromCart();
        CartPage.removeProductFromCart();
    }

    @Test(priority = 4,description = "Kiểm tra khả năng thêm sản phẩm vào giỏ hàng từ kết quả tìm kiếm.")
    public void TSPVGH_4(){
        methodCount =4; int i=0;
        HomePage.goToPage();
        HomePage.searchName(i);
        ProductDetailPage.navigateToProductDetailByName(i);
        ProductDetailPage.selectColorProduct("#1857f7");
        ProductDetailPage.selectSizeProduct("32");
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.searchInput[i].getValue()+"']"));
    }

    @Test(priority = 5,description = "Kiểm tra tổng giá trị giỏ hàng sau khi thêm sản phẩm.")
    public void TSPVGH_5(){
        methodCount=5; int i=0;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        Double productPrice = ProductDetailPage.getProductPrice();
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Double orderTotal = CartPage.getOrderTotal();
        Assert.assertEquals(productPrice,orderTotal);
    }

    @Test(priority = 6, description = "Kiểm tra xem số lượng sản phẩm trong giỏ hàng tăng lên khi thêm nhiều sản phẩm giống nhau.")
    public void TSPVGH_6(){
        methodCount =6;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        for (int i=0;i<3;i++){
            ProductDetailPage.addToCart();
        }
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//span[contains(text(),'3')]"));
    }

    @Test(priority = 7, description = "Kiểm tra giỏ hàng trống sau khi xóa tất cả các sản phẩm.")
    public void TSPVGH_8(){
        methodCount=8;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        CartPage.removeProductFromCart();
        Utils.verifyElementDisplay("//h2[contains(text(),'Your cart is empty')]");
    }

    @Test(priority = 8,description = "Kiểm tra giỏ hàng vẫn giữ lại sản phẩm sau khi tải lại trang.")
    public void TSPVGH_9(){
        methodCount =9;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail1);
        ProductDetailPage.addToCart();
        CartPage.goToCart();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']"));
        Utils.refresh();
        Assert.assertTrue(Utils.verifyElementDisplay("//a[normalize-space()='"+ProductDetailPage.nameProduct1+"']"));
    }

    @Test(priority = 9, description = "Kiểm tra nút Add to Cart bị vô hiệu hóa khi không chọn số lượng và các đặc tính của sản phẩm")
    public void TSPVGH_10(){
        methodCount=10;
        ProductDetailPage.navigateToProductDetailByLink(ProductDetailPage.linkProductDetail2);
        Utils.isElementDisabled("//button[contains(text(),'Add to cart')]");
    }

    @AfterMethod
    public void afterMethod(){
        List<Integer> methodCounts = Arrays.asList(1,4,5,6,9);
        if (methodCounts.contains(methodCount)){
            CartPage.removeProductFromCart();
        }
    }
}
