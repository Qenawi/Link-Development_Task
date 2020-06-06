package tjw.link_task.extentions
import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import tjw.link_task.domain.base.BaseFragment
inline fun FragmentManager.inTransactionWithBackStack(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().addToBackStack(null).func().commit()
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()
inline fun <reified T: ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T
{
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}
fun BaseFragment.close() = fragmentManager?.popBackStack()
fun <B: ViewDataBinding> BaseFragment.binding(container: ViewGroup):B =
    DataBindingUtil.inflate(layoutInflater, layoutId(), container, false)
val BaseFragment.appContext: Context get() = activity?.applicationContext!!