package transfer.db;

import dagger.Component;

import javax.inject.Singleton;

@Component(modules = DbMigrationModule.class)
@Singleton
public interface DbMigrationStarterFactory {

    DbMigrationStarter starter();
}
