import core.BaseTest;
import core.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {
    private static int methodCount;

    @BeforeClass
    public void init(){
        HomePage homePage = new HomePage(driver);
        Utils utils = new Utils(driver);
    }

    @Test(priority = 1,description = "Kiểm tra các button trên banner có hoạt động chính xác hay không")
    public void TC_1(){
        methodCount=1;
        HomePage.goToPage();
        Utils.scrollToElement("//a[contains(text(),'Learn more')]");
        Utils.clickOnElement("//a[contains(text(),'Learn more')]");
        Utils.verifyElementDisplay("//h2[contains(text(),'Women')]");
    }

    @Test(priority = 2, description = "Kiểm tra sự hiện diện và hoạt động của thanh tìm kiếm trên trang chủ")
    public void TC_2(){
        methodCount=2; int i=0;
        HomePage.goToPage();
        Utils.verifyElementDisplay("//input[@placeholder='Search for items']");
    }

    @Test(priority = 3, description = "Kiểm tra các danh mục trên menu điều hướng chính")
    public void TC_3(){
        methodCount=3;
        Utils.hoverOverElement("//a[contains(text(),'Women')]");
        Utils.clickOnElement("//a[contains(text(),'Jackets')]");
        Utils.verifyElementDisplay("//h2[contains(text(),'Jackets')]");
    }

    @Test(priority = 4, description = "Kiểm tra các liên kết và thông tin trong phần footer (chân trang) của trang chủ")
    public void TC_4(){
        methodCount=4;
        HomePage.goToPage();Utils.sleep(2);
        Utils.scrollToBottom();
        Utils.clickOnElement("//span[contains(text(),'Who we are')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 5, description = "Kiểm tra các liên kết và thông tin trong phần footer (chân trang) của trang chủ")
    public void TC_5(){
        methodCount=5;
        Utils.scrollToElement("//span[contains(text(),'Women fashion')]");
        Utils.clickOnElement("//span[contains(text(),'Women fashion')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 6, description = "Kiểm tra các liên kết và thông tin trong phần footer (chân trang) của trang chủ")
    public void TC_6(){
        methodCount=6;
        Utils.scrollToElement("//span[contains(text(),'Customer service')]");
        Utils.clickOnElement("//span[contains(text(),'Customer service')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 7, description = "Kiểm tra các liên kết và thông tin trong phần footer (chân trang) của trang chủ")
    public void TC_7(){
        methodCount=7;
        Utils.scrollToElement("//span[contains(text(),'Purchase terms')]");
        Utils.clickOnElement("//span[contains(text(),'Purchase terms')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 8, description = "Kiểm tra các liên kết mạng xã hội trên trang chủ")
    public void TC_8(){
        methodCount=8;
        Utils.scrollToElement("//h5[contains(text(),'Social')]");
        Utils.clickOnElement("//img[@alt='facebook']");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 9, description = "Kiểm tra các liên kết mạng xã hội trên trang chủ")
    public void TC_9(){
        methodCount=9;
        Utils.clickOnElement("//img[@alt='pinterest']");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 10, description = "Kiểm tra các liên kết mạng xã hội trên trang chủ")
    public void TC_10(){
        methodCount=10;
        Utils.clickOnElement("//img[@alt='google']");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 11, description = "Kiểm tra các liên kết mạng xã hội trên trang chủ")
    public void TC_11(){
        methodCount=11;
        Utils.clickOnElement("//img[@alt='twitter']");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 12, description = "Kiểm tra các liên kết mạng xã hội trên trang chủ")
    public void TC_12(){
        methodCount=12;
        Utils.clickOnElement("//img[@alt='youtube']");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 13, description = "Kiểm tra liên kết đến trang trợ giúp và câu hỏi thường gặp của trang web")
    public void TC_13(){
        methodCount=13;
        Utils.scrollToElement("//button[contains(text(),'Help & FAQs')]");
        Utils.clickOnElement("//button[contains(text(),'Help & FAQs')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }

    @Test(priority = 14, description = "Đảm bảo rằng khi người dùng nhấn nút \"Subscribe\", họ được điều hướng đến đúng trang hoặc bật lên (popup) liên quan")
    public void TC_14(){
        methodCount=14;
        Utils.scrollToElement("//button[contains(text(),'Subscribe')]");
        Utils.clickOnElement("//button[contains(text(),'Subscribe')]");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://demo-magento2.vuestorefront.io/default", "The URL should not be https://demo-magento2.vuestorefront.io/default");
    }
}
