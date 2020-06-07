package tjw.link_task.ui.home

import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.lifecycle.MutableLiveData
import tjw.link_task.R
import tjw.link_task.databinding.MenuItemBinding
import tjw.link_task.domain.base.BaseDataBindingAdapter
import tjw.link_task.domain.base.BaseViewModel
import tjw.link_task.domain.base.autoNotify
import tjw.link_task.domain.base.binding
import tjw.link_task.domain.data.MenuItem
import tjw.link_task.extentions.Navigation
import tjw.link_task.extentions.toast

class MenuAdapter(private var adapterData: ArrayList<MenuItem>) :
    BaseDataBindingAdapter<MenuItemBinding, MenuItem>() {

    private lateinit var bind: MenuItemBinding
    private val selection = MutableLiveData<Navigation>().apply { this.value=Navigation.NONE}
    override fun layoutId() = R.layout.menu_item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemViewHolder {
        bind = binding(parent)
        val viewHolder = MenuItemViewHolder(bind)
        viewHolder.itemView.setOnClickListener {
           selection.postValue(adapterData[viewHolder.adapterPosition].type)
           viewHolder.itemView.context.toast(adapterData.get(viewHolder.adapterPosition).type.name)
           update()
        }
        return viewHolder
    }
    override fun getItemCount() = adapterData.size
    override fun onBindViewHolder(holder: ViewHolder<MenuItemBinding, MenuItem>, position: Int) {
        holder.bind(adapterData[position])
    }
    inner class MenuItemViewHolder(val bindView: MenuItemBinding) :
        ViewHolder<MenuItemBinding, MenuItem>(bindView) {
        override fun <T : MenuItem> bind(t: T) {
            bindView.data = t
            bindView.selection=selection
        }
    }
    fun update()
    {
        notifyDataSetChanged()
      //  autoNotify(adapterData, adapterData, ::campare)
    }
    private fun campare(MenuItem: MenuItem, MenuItem1: MenuItem): Boolean
    {
        return MenuItem.name == MenuItem1.name
    }
}