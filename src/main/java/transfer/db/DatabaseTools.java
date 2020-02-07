package transfer.db;

import dagger.Component;

import javax.inject.Singleton;

@Component(modules = DatabaseModule.class)
public interface DatabaseTools {

    DbMigrationStarter migration();

    ReladomoStarter reladomo();
}
