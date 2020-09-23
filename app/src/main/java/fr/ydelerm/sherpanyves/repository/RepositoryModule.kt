package fr.ydelerm.sherpanyves.repository

import android.app.Application
import dagger.Module
import dagger.Provides
import fr.ydelerm.sherpanyves.datasource.SlaveDataSource
import fr.ydelerm.sherpanyves.persistence.DbDataSourceImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repository(application: Application): Repository {
        return MixedRepositoryImpl(application)
    }

}