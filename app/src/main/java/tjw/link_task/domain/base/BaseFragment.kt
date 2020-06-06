package tjw.link_task.domain.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import tjw.link_task.extentions.binding

abstract class BaseFragment: Fragment()
{
    abstract fun layoutId(): Int
    abstract fun view_life_cycle_owner(): LifecycleOwner
    lateinit var binding: ViewDataBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = binding(container!!)
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }
    open fun onBackPressed() {}
    override fun onResume()
    {
        super.onResume()
    }

}