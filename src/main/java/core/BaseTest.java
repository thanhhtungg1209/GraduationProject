package core;

import config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

/**
 * BaseTest là lớp cơ sở dùng để thiết lập và quản lý vòng đời của đối tượng WebDriver trong các bài kiểm thử tự động.
 * Lớp này đảm bảo môi trường kiểm thử được khởi tạo đúng cách trước mỗi lần chạy và được giải phóng tài nguyên sau khi hoàn thành.
 */

 public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        driver = BrowserConfig.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
