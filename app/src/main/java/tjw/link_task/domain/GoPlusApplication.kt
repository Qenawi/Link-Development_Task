package tjw.link_task.domain

import android.app.Application
import android.content.Context
import timber.log.Timber
import tjw.link_task.BuildConfig
import tjw.link_task.dagger.AppComponent
import tjw.link_task.dagger.modules.AppModule
import tjw.link_task.dagger.DaggerAppComponent

class GoPlusApplication:Application()
{
    override fun onCreate() {
        super.onCreate()
        setupTimber()
        initDagger()
    }
    private fun setupTimber(){
        if (BuildConfig.DEBUG)
        {
           Timber.plant(Timber.DebugTree())
        }
    }
    private fun initDagger() {
        appComponent =
            DaggerAppComponent.builder().application(this).appModule(AppModule(this)).build()
        appComponent.inject(this)

    }
    companion object {
        lateinit var appComponent: AppComponent private set
        var context: Context? = null

    }
}