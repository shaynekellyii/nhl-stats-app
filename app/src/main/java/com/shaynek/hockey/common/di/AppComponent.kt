package com.shaynek.hockey.common.di

import com.shaynek.hockey.HockeyActivity
import com.shaynek.hockey.selectteam.SelectTeamFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SelectTeamFragment)
    fun inject(activity: HockeyActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
    }
}