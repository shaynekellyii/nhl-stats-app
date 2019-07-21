package com.shaynek.hockey.common.di

import android.content.Context
import android.preference.PreferenceManager
import androidx.room.Room
import com.shaynek.hockey.common.AppRepository
import com.shaynek.hockey.common.db.HockeyDao
import com.shaynek.hockey.common.db.HockeyDb
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
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideDb(): HockeyDb = Room
        .databaseBuilder(appContext, HockeyDb::class.java, "hockey-db")
        .build()

    @Provides
    @Singleton
    fun provideDao(db: HockeyDb): HockeyDao = db.teamDao()

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