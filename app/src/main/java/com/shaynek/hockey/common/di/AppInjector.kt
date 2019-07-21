package com.shaynek.hockey.common.di

import android.content.Context

object AppInjector {
    val injector by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(appContext))
            .build()
    }

    lateinit var appContext: Context
}