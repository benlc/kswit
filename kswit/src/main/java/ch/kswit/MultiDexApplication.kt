package ch.kswit

import androidx.multidex.MultiDexApplication

class MultiDexApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        KswitManager(this).start()
    }
}