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

//    @Test(priority = 1, description = "Đảm bảo người dùng có thể đăng ký tài khoản thành công khi cung cấp thông tin hợp lệ.")
//    public void DK_1() {
//        int i=0;
//        RegisterPage.formRegister(i);
//        RegisterPage.clickCreatButton();
//        LoginPage.login(i);
//        ProfilePage.goToProfile();
//        Assert.assertTrue(LoginPage.checkLoginSuccess());
//    }

    @Test(priority = 2, description = "Đảm bảo rằng hệ thống không cho phép người dùng đăng ký với địa chỉ email đã tồn tại.")
    public void DK_2(){
        int i=0;
        Utils.refresh();
        RegisterPage.formRegister(i);
        RegisterPage.clickCreatButton();
        Assert.assertTrue(RegisterPage.checkRegisterFail());
    }

    @Test(priority = 3, description = "Đảm bảo rằng hệ thống không cho phép đăng ký với email không hợp lệ.")
    public void DK_3(){
        int i=1;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkEmailInvalid());
    }

    @Test(priority = 4, description = "Đảm bảo rằng hệ thống không cho phép đăng ký với mật khẩu quá ngắn.")
    public void DK_4(){
        int i=2;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkPasswordInvalid());
    }

    @Test(priority = 5, description = "Đảm bảo không cho phép đăng ký nếu trường First Name để trống hoặc nhập toàn space")
    public void DK_5(){
        int i=3;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkRequiredField());
    }

    @Test(priority = 6, description = "Đảm bảo không cho phép đăng ký nếu trường Last Name để trống.")
    public void DK_6(){
        int i=4;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkRequiredField());
    }

    @Test(priority = 7, description = "Đảm bảo không cho phép đăng ký nếu trường Email để trống.")
    public void DK_7(){
        int i=5;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkRequiredField());
    }

    @Test(priority = 8, description = "Đảm bảo hệ thống không cho phép nhập email dài hơn giới hạn cho phép.")
    public void DK_8(){
        int i=6;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkEmailInvalid());
    }

    @Test(priority = 9, description = "Đảm bảo hệ thống không cho phép mật khẩu vượt quá độ dài cho phép.")
    public void DK_9(){
        int i=7;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkPasswordInvalid());
    }

    @Test(priority = 10, description = "Đảm bảo hệ thống kiểm tra định dạng email đầy đủ.")
    public void DK_10(){
        int i=8;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkEmailInvalid());
    }

    @Test(priority = 11, description = "Đảm bảo hiển thị thông báo lỗi nếu mật khẩu không chứa ký tự số.")
    public void DK_11(){
        int i=9;
        Utils.refresh();
        RegisterPage.formRegister(i);
        Assert.assertTrue(RegisterPage.checkPasswordInvalid());
    }
}
