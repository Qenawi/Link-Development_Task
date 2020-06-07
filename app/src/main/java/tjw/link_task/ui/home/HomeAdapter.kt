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
import javax.security.auth.callback.Callback

class HomeAdapter(private var adapterData: ArrayList<Article>,val callback: (Article)->Unit={}) :
    BaseDataBindingAdapter<ArticleItemBinding, Article>() {
    private lateinit var bind: ArticleItemBinding
    override fun layoutId() = R.layout.article_item
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        bind = binding(parent)
        val viewHolder = ArticleViewHolder(bind)
        viewHolder.itemView.setOnClickListener {
        callback(adapterData.get(viewHolder.adapterPosition))
        }
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
        /*
        print("After ${adapterData.size}")
        autoNotify(adapterData, data, ::campare)
        print("Before ${adapterData.size}")
       */
        adapterData.clear()
        adapterData.addAll(data)
        notifyDataSetChanged()
    }

    private fun campare(article: Article, article1: Article): Boolean
    {
        article
        return article.url.equals(article1.url)
    }
    //todo Searhc Bu name and date
}