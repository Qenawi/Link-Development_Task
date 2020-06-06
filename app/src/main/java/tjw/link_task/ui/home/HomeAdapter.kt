package tjw.link_task.ui.home

import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter
import tjw.link_task.R
import tjw.link_task.databinding.ArticleItemBinding
import tjw.link_task.domain.base.BaseDataBindingAdapter
import tjw.link_task.domain.base.BaseViewModel
import tjw.link_task.domain.base.autoNotify
import tjw.link_task.domain.base.binding
import tjw.link_task.domain.data.Article

class HomeAdapter(private var adapterData: ArrayList<Article>) :
    BaseDataBindingAdapter<ArticleItemBinding, Article>() {
    private lateinit var bind: ArticleItemBinding
    override fun layoutId() = R.layout.article_item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        bind = binding(parent)
        val viewHolder = ArticleViewHolder(bind)
        return viewHolder
    }

    override fun getItemCount() = adapterData.size
    override fun onBindViewHolder(holder: ViewHolder<ArticleItemBinding, Article>, position: Int) {
        holder.bind(adapterData[position])
    }

    inner class ArticleViewHolder(val bindView: ArticleItemBinding) :
        ViewHolder<ArticleItemBinding, Article>(bindView) {
        override fun <T : Article> bind(t: T) {
            bindView.data = t
        }
    }

    fun update(data: ArrayList<Article>) {
        print("After ${adapterData.size}")
        autoNotify(adapterData, data, ::campare)
        print("Before ${adapterData.size}")

    }

    private fun campare(article: Article, article1: Article): Boolean
    {
        return article.url.equals(article1.url)
    }
    //todo Searhc Bu name and date
}