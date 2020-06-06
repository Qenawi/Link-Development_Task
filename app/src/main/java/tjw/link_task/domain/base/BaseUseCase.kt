package tjw.link_task.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
abstract class BaseUseCase<out Type, in Params>
    (
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) where Type : Any
{

    fun onCleared()
    {
        if (scope.isActive) {
            scope.cancel()
        }
    }
    class None
}
