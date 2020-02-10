package transfer.config.properties;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtil {

    @SneakyThrows
    public static Properties readSystemOverridableProperties(ClassLoader classLoader, String file) {
        var defaults = readProperties(classLoader, file);

        var systemPropertiesStore = new ByteArrayOutputStream();
        System.getProperties().store(systemPropertiesStore, "");
        var finalProperties = new Properties(defaults);
        finalProperties.load(new ByteArrayInputStream(systemPropertiesStore.toByteArray()));

        return finalProperties;
    }

    @SneakyThrows
    public static Properties readProperties(ClassLoader classLoader, String file) {
        var properties = new Properties();
        try (var is = classLoader.getResourceAsStream(file)) {
            properties.load(is);
        }
        return properties;
    }
}
