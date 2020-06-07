package tjw.link_task.ui.home

import kotlinx.coroutines.*
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
       fetchAsync(params,call)
    }
    private fun fetchAsync(params: Params,call: (Either<Failure, ArrayList<Article>>) -> Unit = {})
    {
    scope.launch {
        val f1=  async {repo.articleOne(params)}
        val f2=  async {repo.articleTwo(params)}
        val result=computeResult(f1.await(),f2.await())
        withContext(dispatcher){
            call(result)
        }
        }
        }

    private fun computeResult(art1:Either<Failure,ArrayList<Article>>,art2:Either<Failure,ArrayList<Article>>)=merge(art1, art2)

    private fun merge(art1:Either<Failure,ArrayList<Article>>,art2:Either<Failure,ArrayList<Article>>):Either<Failure,ArrayList<Article>>
    {

         var fail:Failure?=null
         val result=ArrayList<Article>()
         fun set_fail(failure: Failure){fail=failure}
         // combine

         art1.either({f->set_fail(f)},{r->result.addAll(r)})
         art2.either({f->set_fail(f)},{r->result.addAll(r)})

        // return
        return if (fail!=null&&result.isNullOrEmpty()) Either.Left(fail?:Failure.RequestError)
        else Either.Right(result)
    }
    class Params
}
//todo add Room Data base Phase 2