package tjw.link_task.dagger.modules;

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import tjw.link_task.dagger.AppComponent.Companion.DaggerHomeModule
import tjw.link_task.dagger.modules.AppFeatures.Companion.DispatcherUseCaseName
import tjw.link_task.dagger.modules.AppFeatures.Companion.UseCaseScopeName
import tjw.link_task.ui.home.HomeRepo
import tjw.link_task.ui.home.HomeUseCase
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class HomeModule {

    @Named(DaggerHomeModule + UseCaseScopeName)
    @Provides
    fun providesCoroutinesScope(): CoroutineScope = object : CoroutineScope {
        private val job = Job()
        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO
    }

    @Named(DaggerHomeModule + DispatcherUseCaseName)
    @Provides
    @Singleton
    fun provideCoroutinesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    fun providesHomeRepo(network: HomeRepo.NetWork): HomeRepo {
        return network
    }

    @Provides
    fun provideHomeModuleUseCase(
        repo: HomeRepo,
        @Named(DaggerHomeModule + UseCaseScopeName)
        scope: CoroutineScope,
        @Named(DaggerHomeModule + DispatcherUseCaseName)
        dispatcher: CoroutineDispatcher
    ): HomeUseCase {
        return HomeUseCase(dispatcher, scope, repo)
    }

}