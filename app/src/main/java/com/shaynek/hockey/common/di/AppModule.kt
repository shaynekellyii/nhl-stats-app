package com.shaynek.hockey.common.di

import android.content.Context
import android.preference.PreferenceManager
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.db.Preferences
import com.shaynek.hockey.common.network.HockeyApi
import com.shaynek.hockey.common.util.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val appContext: Context) {
    @Provides
    @Reusable
    fun provideApi(retrofit: Retrofit): HockeyApi = retrofit.create(HockeyApi::class.java)

    @Provides
    @Reusable
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @Provides
    @Reusable
    fun provideRepository(hockeyApi: HockeyApi): AppRepository = AppRepository(hockeyApi)

    @Provides
    @Reusable
    fun providePreferences(): Preferences = Preferences(PreferenceManager.getDefaultSharedPreferences(appContext))
}