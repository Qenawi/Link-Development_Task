package tjw.link_task.ui.home

import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter
import tjw.link_task.R
import tjw.link_task.databinding.MenuItemBinding
import tjw.link_task.domain.base.BaseDataBindingAdapter
import tjw.link_task.domain.base.BaseViewModel
import tjw.link_task.domain.base.autoNotify
import tjw.link_task.domain.base.binding
import tjw.link_task.domain.data.MenuItem

class MenuAdapter(private var adapterData: ArrayList<MenuItem>) :
    BaseDataBindingAdapter<MenuItemBinding, MenuItem>() {
    private lateinit var bind: MenuItemBinding
    override fun layoutId() = R.layout.menu_item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemViewHolder {
        bind = binding(parent)
        val viewHolder = MenuItemViewHolder(bind)
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
        }
    }
    fun update(data: ArrayList<MenuItem>) {
        autoNotify(adapterData, data, ::campare)
    }
    private fun campare(MenuItem: MenuItem, MenuItem1: MenuItem): Boolean
    {
        return MenuItem.name == MenuItem1.name
    }
}