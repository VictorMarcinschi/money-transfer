package transfer.db;

import dagger.Module;
import dagger.Provides;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import transfer.properties.ApplicationPropertiesModule;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

import static transfer.properties.ApplicationProperties.DATASOURCE_PASSWORD;
import static transfer.properties.ApplicationProperties.DATASOURCE_URL;
import static transfer.properties.ApplicationProperties.DATASOURCE_USER;

@Module(includes = ApplicationPropertiesModule.class)
public class DatabaseModule {

    @Provides
    @Singleton
    static DataSource provideDataSource(
            @Named(DATASOURCE_URL) String url,
            @Named(DATASOURCE_USER) String user,
            @Named(DATASOURCE_PASSWORD) String password) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);

        if (StringUtils.isNotBlank(password)) {
            dataSource.setPassword(password);
        }

        return dataSource;
    }
}
