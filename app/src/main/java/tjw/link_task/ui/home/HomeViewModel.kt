package tjw.link_task.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import tjw.go_plus_meeting.domain.network.Failure
import tjw.link_task.domain.base.BaseViewModel
import tjw.link_task.domain.data.Article
import javax.inject.Inject

class HomeViewModel @Inject constructor (app: Application, val useCase: HomeUseCase) :
    BaseViewModel<HomeUseCase>(app, useCase) {
     val mLoading = MutableLiveData<Boolean>()
    val mResult = MutableLiveData<ArrayList<Article>>()
    fun doWork() {
        mLoading.value = true
        useCase.doWork(HomeUseCase.Params()){art->
            art.either(::handleHomeFail,::handleHomeSucess)

        }
    }
    private fun handleHomeSucess(data: ArrayList<Article>) {
        mLoading.value = false
        mResult.value = data
    }
    private fun handleHomeFail(fail: Failure) {
        mLoading.value = false
        toastMutable.postValue(fail)
    }
}