package tjw.link_task.dagger.modules

import dagger.Module
import tjw.link_task.dagger.ViewModelFactoryBindingModule

@Module(
    includes = [ViewModelFactoryBindingModule::class
            , NetworkModule::class,HomeModule::class
    ]
)
class AppFeatures {
    companion object {
        const val UseCaseScopeName = "useCaseScope"
        const val DispatcherUseCaseName = "dispatcherName"
    }
}