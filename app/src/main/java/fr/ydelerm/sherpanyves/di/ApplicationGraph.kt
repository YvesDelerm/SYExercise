package fr.ydelerm.sherpanyves.di

import dagger.Component
import fr.ydelerm.sherpanyves.repositories.NetworkModule
import fr.ydelerm.sherpanyves.viewmodel.AllViewModel

@Component(modules = [NetworkModule::class])
interface ApplicationGraph {
    fun inject(viewModel: AllViewModel)
}