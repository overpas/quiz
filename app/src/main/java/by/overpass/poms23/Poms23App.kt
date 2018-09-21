package by.overpass.poms23

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

class Poms23App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: Poms23App
        fun getAppContext(): Context = instance.applicationContext
    }

}