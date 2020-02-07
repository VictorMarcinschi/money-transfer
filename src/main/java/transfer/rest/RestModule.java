package transfer.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Named;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import transfer.config.properties.ApplicationPropertiesModule;
import transfer.server.RouteDefinition;
import transfer.server.SparkRouteDefinition;

import static transfer.config.properties.ApplicationProperties.DATE_PATTERN;
import static transfer.config.properties.ApplicationProperties.TIMESTAMP_PATTERN;

@Module(includes = ApplicationPropertiesModule.class)
public class RestModule {

    @Provides
    static ObjectMapper provideObjectMapper(@Named(DATE_PATTERN) String datePattern,
            @Named(TIMESTAMP_PATTERN) String timestampPattern) {

        var mapper = new ObjectMapper();
        var timeModule = new JavaTimeModule();

        var dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        timeModule.addSerializer(new LocalDateSerializer(dateFormatter));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

        var timestampFormatter = DateTimeFormatter.ofPattern(timestampPattern);
        timeModule.addSerializer(new LocalDateTimeSerializer(timestampFormatter));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(timestampFormatter));

        mapper.registerModule(timeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Provides
    @IntoSet
    @RouteDefinition
    static SparkRouteDefinition provideErrorRouteDefinition(ObjectMapper mapper) {
        return new ErrorRouteDefinition(mapper);
    }
}
