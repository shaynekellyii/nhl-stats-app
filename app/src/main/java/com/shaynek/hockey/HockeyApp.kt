package com.shaynek.hockey

import android.app.Application
import com.shaynek.hockey.common.di.AppInjector

class HockeyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppInjector.appContext = this
    }
}