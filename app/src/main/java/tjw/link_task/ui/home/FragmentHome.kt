package tjw.link_task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.main_fragment_content.*
import tjw.link_task.R
import tjw.link_task.databinding.FragmentHomeBinding
import tjw.link_task.domain.LikTaskApplication
import tjw.link_task.domain.base.BaseActivity
import tjw.link_task.domain.base.BaseFragment
import tjw.link_task.domain.data.Article
import tjw.link_task.domain.data.getMenu
import tjw.link_task.extentions.*
import tjw.link_task.ui.details.FragmentDetails
import javax.inject.Inject

class FragmentHome : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: HomeViewModel
    override fun layoutId() = R.layout.fragment_home
    override fun view_life_cycle_owner() = viewLifecycleOwner
    private var menuAdapter = MenuAdapter(getMenu())
    private var newsAdapter = HomeAdapter(ArrayList(), ::navigate)

    companion object {
        fun newInstance() = FragmentHome()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LikTaskApplication.appComponent.inject(this)
        //not the best way but its clean hack
        (activity as BaseActivity<*>?)?.let { act ->
            observe(act.navState) { st ->
                st?.let {
                    if (st) {
                        viewModel.mNavState.postValue(viewModel.mNavState.value.revert())
                    }
                }
            }
        }
        viewModel = viewModel(factory) {
            doWork()
            observe(toastMutable) { fail -> fail?.let { ff -> context.toast(ff.f_causeSt) } }
            observe(mResult) { result ->
                result?.let { rr ->
                    newsAdapter.update(rr)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (binding as FragmentHomeBinding).viewModel=viewModel
        (binding as FragmentHomeBinding).lContainer.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        l_menu_rv.mLinearLayoutManager()
        l_menu_rv.adapter = menuAdapter
        l_news_recycler.mLinearLayoutManager()
        l_news_recycler.adapter = newsAdapter
    }

    private fun navigate(article: Article) {
        viewModel.mNavState.postValue(null)
        delay150 {
            mAddFragment(addToBackStack = true, activity = activity) {
                FragmentDetails.newInstance(
                    article
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity<*>?)?.toolbarTitle?.postValue(getString(R.string.home))
    }
}