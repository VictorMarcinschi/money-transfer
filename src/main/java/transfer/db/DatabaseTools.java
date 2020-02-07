package transfer.db;

import dagger.Subcomponent;
import transfer.config.properties.ApplicationPropertiesModule;

@Subcomponent(modules = DatabaseModule.class)
public interface DatabaseTools {

    DbMigrationStarter migration();

    ReladomoStarter reladomo();

    @Subcomponent.Builder
    interface Builder {

        Builder applicationPropertiesModule(ApplicationPropertiesModule module);

        DatabaseTools build();
    }
}
