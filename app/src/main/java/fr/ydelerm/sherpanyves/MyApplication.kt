package fr.ydelerm.sherpanyves

import android.app.Application
import fr.ydelerm.sherpanyves.di.DaggerApplicationGraph

class MyApplication : Application() {
    lateinit var appGraph: DaggerApplicationGraph

    override fun onCreate() {
        super.onCreate()
        appGraph =
            DaggerApplicationGraph.builder().application(this).build() as DaggerApplicationGraph
    }
}