package fr.ydelerm.sherpanyves

import android.app.Application
import fr.ydelerm.sherpanyves.di.DaggerApplicationGraph

class MyApplication : Application() {
    val appGraph = DaggerApplicationGraph.create()
}