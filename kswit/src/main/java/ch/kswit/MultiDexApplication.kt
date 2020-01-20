package ch.kswit

import androidx.multidex.MultiDexApplication

open class MultiDexApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        KswitManager(this).start()
    }
}