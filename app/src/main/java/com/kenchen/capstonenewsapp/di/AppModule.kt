package com.kenchen.capstonenewsapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PlanetModule {

}



/**
 * 1. what exactly is the dependency graph mean (generated by Hilt?)
 * 1. what does @Inject constructor means? Does it mean that the class can be injected through
 * constructor?
 * 2. what if there are no interface for binding? Initially my RemoteApi does not have a interface
 * 2. why does prefsStoreImp need a @Inject constructor, since it is using @ApplicationContext
 * 3. Source converter initially use Gson() in App.kt. When I try to use injection it occurs an
 * error. Seem it cannot use a constructor for Room converter
 * 4. proper naming and what can be put together in the same module and what should be separated?
 * 5. should we use DI in workmanager?
 * 6. Currently I am using SingletonComponent(Singleton scope), where can I use ActivityScope
 * (Activity scope) based on Component
 * hierarchy and does install every module in SingletonComponent effect the efficiency?
 * 7. Is it ok to use @Singleton scope every where, or we should use @ActivityScoped,
 * @ViewModelScoped. Document mentioned it can be costly, why?
 * 8. when I was looking at @EntryPoint it mentioned that it is used for android classes that is
 * not supported ex: content provider. What is content provider and where is it used?
 * 9. Testing ...
 *
 * Other:
 * 1. How do I format the Readme of the CapstoneNews App if I want to show it to the interviewer?
 * 2. view, presenter(feed models into the view), how does this fit into MVVM, MVP
 *
 *
 * what the app does
 * architecture, why?
 * UI compose
 * repository(local, remote)
 * testing (Unit test, instrimented test)
 * future improvement
 *
 * */
