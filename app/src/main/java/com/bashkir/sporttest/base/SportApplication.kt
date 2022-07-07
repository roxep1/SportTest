package com.bashkir.sporttest.base

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.bashkir.sporttest.data.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SportApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)

        startKoin {
            androidContext(this@SportApplication)

            modules(module)
        }
    }
}