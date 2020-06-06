package tjw.link_task.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import tjw.link_task.R
import tjw.link_task.dagger.ViewModelFactory
import tjw.link_task.dagger.ViewModelFactory_Factory
import tjw.link_task.domain.LikTaskApplication
import tjw.link_task.domain.base.BaseFragment
import tjw.link_task.extentions.observe
import tjw.link_task.extentions.toast
import tjw.link_task.extentions.viewModel
import javax.inject.Inject

class FragmentHome:BaseFragment()
{

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel:HomeViewModel
    override fun layoutId()= R.layout.fragment_home
    override fun view_life_cycle_owner()=viewLifecycleOwner

    companion object{
     fun  newInstance()=FragmentHome()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LikTaskApplication.appComponent.inject(this)
        viewModel=viewModel(factory){
          observe(toastMutable){fail-> fail?.let {ff-> context.toast(ff.f_causeSt)}}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
}