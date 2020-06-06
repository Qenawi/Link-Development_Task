package tjw.link_task.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import tjw.link_task.dagger.modules.AppFeatures
import tjw.link_task.dagger.modules.AppModule
import tjw.link_task.domain.GoPlusApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        AppFeatures::class]
)
interface AppComponent {
    companion object{
       const val DaggerHomeModule="DaggerHomeModule"
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }
    fun inject(app: GoPlusApplication)
}