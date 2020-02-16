package transfer.config.properties;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesUtilTest {

    private static final String TEST_PROPERTIES_FILE = "PropertiesUtilTest.properties";
    private static final String PROPERTY_ONE = "test.property.one";
    private static final String VALUE_ONE = "value1";
    private static final String PROPERTY_TWO = "test.property.two";
    private static final String VALUE_TWO = "1000";

    @Test
    void testReadProperties() {
        var properties = PropertiesUtil.readProperties(getClass().getClassLoader(), TEST_PROPERTIES_FILE);

        assertThat(properties.size()).isEqualTo(2);
        assertThat(properties.getProperty(PROPERTY_ONE)).isEqualTo(VALUE_ONE);
        assertThat(properties.getProperty(PROPERTY_TWO)).isEqualTo(VALUE_TWO);
    }

    @Test
    void testReadSystemOverridableProperties_NoOverrides() {
        var properties = PropertiesUtil.readSystemOverridableProperties(getClass().getClassLoader(),
                TEST_PROPERTIES_FILE);

        assertThat(properties.size()).isEqualTo(2);
        assertThat(properties.getProperty(PROPERTY_ONE)).isEqualTo(VALUE_ONE);
        assertThat(properties.getProperty(PROPERTY_TWO)).isEqualTo(VALUE_TWO);
    }

    @Test
    void testReadSystemOverridableProperties_OnePropertyOverride() {
        System.setProperty(PROPERTY_ONE, VALUE_ONE + "OVERRIDDEN");

        var properties = PropertiesUtil.readSystemOverridableProperties(getClass().getClassLoader(),
                TEST_PROPERTIES_FILE);

        assertThat(properties.size()).isEqualTo(2);
        assertThat(properties.getProperty(PROPERTY_ONE)).isEqualTo(VALUE_ONE + "OVERRIDDEN");
        assertThat(properties.getProperty(PROPERTY_TWO)).isEqualTo(VALUE_TWO);
    }
}