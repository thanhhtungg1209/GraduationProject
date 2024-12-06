import core.BaseTest;
import core.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {
    @BeforeClass
    public void Init(){
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        Utils utils = new Utils(driver);
        RegisterPage.goToPage();
    }

    @Test(priority = 1, description = "Đảm bảo người dùng có thể đăng ký tài khoản thành công khi cung cấp thông tin hợp lệ.")
    public void DK_1() {
        int i=0;
        RegisterPage.register(i);
        LoginPage.login(i);
        ProfilePage.goToProfile();
        Assert.assertTrue(LoginPage.checkLoginSuccess());
    }

    @Test(priority = 2, description = "Đảm bảo rằng hệ thống không cho phép người dùng đăng ký với địa chỉ email đã tồn tại.")
    public void DK_2(){
        int i=0;
        RegisterPage.register(i);
        Assert.assertTrue(RegisterPage.checkRegisterFail());
    }

    @Test(priority = 3, description = "Đảm bảo rằng hệ thống không cho phép đăng ký với email không hợp lệ.")
    public void DK_3(){
        int i=1;
        RegisterPage.register(i);
        Assert.assertTrue(RegisterPage.checkEmailInvalid());
    }

    @Test(priority = 4, description = "Đảm bảo rằng hệ thống không cho phép đăng ký với mật khẩu quá ngắn.")
    public void DK_4(){
        int i=2;
        RegisterPage.register(i);
        Assert.assertTrue(RegisterPage.checkPasswordInvalid());
    }
}
