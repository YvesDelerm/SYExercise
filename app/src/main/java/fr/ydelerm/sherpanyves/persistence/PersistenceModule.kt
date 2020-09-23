package fr.ydelerm.sherpanyves.persistence

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fr.ydelerm.sherpanyves.datasource.SlaveDataSource
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    fun slaveDataSource(db: AppDatabase): SlaveDataSource {
        return DbDataSourceImpl(db)
    }

    @Singleton
    @Provides
    fun appDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "user-database")
            .build()
    }
}