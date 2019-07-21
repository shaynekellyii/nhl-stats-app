package com.shaynek.hockey.common.di

import com.shaynek.hockey.HockeyActivity
import com.shaynek.hockey.schedule.ScheduleFragment
import com.shaynek.hockey.selectteam.SelectTeamFragment
import com.shaynek.hockey.standings.StandingsFragment
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SelectTeamFragment)
    fun inject(fragment: StandingsFragment)
    fun inject(fragment: ScheduleFragment)
    fun inject(activity: HockeyActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun appModule(appModule: AppModule): Builder
    }
}