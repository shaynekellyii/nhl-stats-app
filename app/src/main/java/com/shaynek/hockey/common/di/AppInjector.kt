package com.shaynek.hockey.common.di

import android.content.Context

object AppInjector {
    lateinit var appContext: Context

    val injector by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(appContext))
            .build()
    }
}