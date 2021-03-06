package fr.ydelerm.sherpanyves.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import fr.ydelerm.sherpanyves.network.NetworkModule
import fr.ydelerm.sherpanyves.persistence.PersistenceModule
import fr.ydelerm.sherpanyves.repository.MixedRepositoryImpl
import fr.ydelerm.sherpanyves.repository.RepositoryModule
import fr.ydelerm.sherpanyves.viewmodel.CommonViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class, PersistenceModule::class])
interface ApplicationGraph {
    fun inject(viewModel: CommonViewModel)
    fun inject(repository: MixedRepositoryImpl)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationGraph

        @BindsInstance
        fun application(application: Application): Builder
    }
}