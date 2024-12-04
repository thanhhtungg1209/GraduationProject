package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Lớp này có trách nhiệm cấu hình trình duyệt cho dự án
 * Cung cấp phương thức tĩnh getDriver để khởi tạo và trả về đối tượng WebDriver cho trình duyệt được chỉ định (hiện tại là Chrome)
 * Có thể mở rộng để hỗ trợ nhiều trình duyệt khác trong tương lai (như Firefox, Edge).
 */

public class BrowserConfig {
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
