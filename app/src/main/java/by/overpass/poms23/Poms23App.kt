package by.overpass.poms23

import android.app.Application
import android.content.Context

class Poms23App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: Poms23App
        fun getAppContext(): Context = instance.applicationContext
    }

}