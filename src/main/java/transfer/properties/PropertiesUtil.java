package transfer.properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
        var uri = classLoader.getResource(file).toURI();
        var properties = new Properties();
        try (var is = Files.newInputStream(Paths.get(uri))) {
            properties.load(is);
        }
        return properties;
    }
}
