package tjw.link_task.ui.home

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tjw.go_plus_meeting.domain.network.Failure
import tjw.link_task.domain.base.BaseUseCase
import tjw.link_task.domain.base.Either
import tjw.link_task.domain.data.Article
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val scope: CoroutineScope,
    val repo: HomeRepo
) : BaseUseCase<ArrayList<Article>, HomeUseCase.Params>(scope, dispatcher) {

    fun doWork(params: Params, call: (Either<Failure, ArrayList<Article>>) -> Unit = {}) {
        scope.launch {
            val result = repo.articleOne(params)
            withContext(dispatcher) {
                call(result)
            }
        }
    }
    private fun fetchAsync(){

    }
    private fun articleOne():ArrayList<Article>{return ArrayList()}
    private fun articleTwo():ArrayList<Article>{return ArrayList()}
    class Params
}
//todo add Room Data base Phase 2