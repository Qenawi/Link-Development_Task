package tjw.link_task.domain.base
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class  BaseDataBindingAdapter<type:ViewDataBinding,item:Parcelable>(): RecyclerView.Adapter<BaseDataBindingAdapter.ViewHolder<type,item>>()
   {
     abstract fun layoutId(): Int
     abstract  class ViewHolder<T:ViewDataBinding,item:Parcelable>( vBind:T):RecyclerView.ViewHolder(vBind.root){
     abstract fun <T:item>bind(t:T)
    }
}
fun <B: ViewDataBinding,T:Parcelable> BaseDataBindingAdapter<B,T>.binding(container: ViewGroup):B =
    DataBindingUtil.inflate(LayoutInflater.from(container.context), layoutId(), container, false)


fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>,
                                           newList: List<T>,
                                           compare: (T, T) -> Boolean){
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback()
    {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
        {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(oldList[oldItemPosition], newList[newItemPosition])
        }
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    }
    )
    diff.dispatchUpdatesTo(this)
}