package config;

import com.google.gson.Gson;
import core.Config;
import org.openqa.selenium.json.TypeToken;

import java.io.FileReader;
import java.io.IOException;

/**
 * Lớp này quản lý việc tải và cung cấp các cấu hình cho ứng dụng từ file cấu hình (JSON)
 * Sử dụng Jackson để đọc cấu hình từ file JSON và lưu trữ vào một bản đồ (map).
 * Cung cấp phương thức loadConfig để tải cấu hình và getConfig để lấy các cấu hình đã được tải.
 */

public class ConfigManager {
    public static Config[] readTestObjectsFromJson(String filepath) {
        try {
            FileReader reader = new FileReader(filepath);
            Gson gson = new Gson();
            TypeToken<Config[]> token = new TypeToken<Config[]>() {};
            Config[] configs = gson.fromJson(reader, token.getType());
            reader.close();
            return configs;
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return new Config[0];
        }
    }
}
