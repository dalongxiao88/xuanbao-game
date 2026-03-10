package org.come.until;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 运行时配置读取工具。
 *
 * 统一从系统属性与 `important.properties` 中读取配置，
 * 用于逐步替换仓库中的外部服务硬编码地址与敏感参数。
 */
public final class RuntimeConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = RuntimeConfig.class.getClassLoader().getResourceAsStream("important.properties")) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            }
        }
        catch (IOException ignored) {
        }
    }

    private RuntimeConfig() {
    }

    /**
     * 按“系统属性 > important.properties > 默认值”的顺序读取配置。
     */
    public static String get(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.trim().isEmpty()) {
            return systemValue.trim();
        }
        String propertyValue = PROPERTIES.getProperty(key);
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return propertyValue.trim();
        }
        return defaultValue;
    }
}
