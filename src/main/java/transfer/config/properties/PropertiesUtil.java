package transfer.config.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtil {

    @SneakyThrows
    public static Properties readSystemOverridableProperties(ClassLoader classLoader, String file) {
        final var defaults = readProperties(classLoader, file);
        System.getProperties().forEach((key, value) -> {
            Optional.ofNullable(defaults.getProperty(key.toString()))
                    .ifPresent(v -> defaults.setProperty(key.toString(), value.toString()));
        });
        return defaults;
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
