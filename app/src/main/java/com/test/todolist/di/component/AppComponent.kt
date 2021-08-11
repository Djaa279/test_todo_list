package com.test.todolist.di.component

import com.test.todolist.CustomApplication
import com.test.todolist.di.modules.AppModule
import com.test.todolist.di.modules.BuildersModule
import com.test.todolist.di.modules.DatabaseModule
import com.test.todolist.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        BuildersModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(app: CustomApplication)
}