package tjw.link_task.ui.home;

import tjw.go_plus_meeting.domain.network.Failure
import tjw.link_task.dagger.modules.NetworkHandler
import tjw.link_task.domain.base.Either
import tjw.link_task.domain.data.Article
import tjw.link_task.domain.network.RetrofitService
import tjw.link_task.domain.network.request
import tjw.link_task.extentions.*
import javax.inject.Inject

interface HomeRepo {
    fun articleOne(params: HomeUseCase.Params): Either<Failure, ArrayList<Article>>
    fun articleTwo(params: HomeUseCase.Params): Either<Failure, ArrayList<Article>>

    class NetWork @Inject constructor(
        val service: RetrofitService,
        val network: NetworkHandler
    ) : HomeRepo {
        override fun articleOne(params: HomeUseCase.Params): Either<Failure, ArrayList<Article>> {
            return when (network.isConnected) {
                false, null -> Either.Left(Failure.NetworkConnection)
                else -> {
                    request(
                        service.articleOne(),
                        { j ->
                           val data=j.asJsonObject.get("articles").asJsonArray
                            data.mMapToArrayListFix<Article>()
                        },
                        { v -> v.validateArrayList() },
                        ArrayList<Article>()
                    )
                }
            } //when
        }
        override fun articleTwo(params: HomeUseCase.Params): Either<Failure, ArrayList<Article>> {
            return when (network.isConnected) {
                false, null -> Either.Left(Failure.NetworkConnection)
                else -> {
                    request(
                        service.articleTwo(),
                        { j ->
                            val data=j.asJsonObject.get("articles").asJsonArray
                            data.mMapToArrayListFix<Article>()
                        },
                        { v -> v.validateArrayList() },
                        ArrayList()
                    )
                }
            } //when
        }

    }
}