package web.connect.newsdemo.ui.adapters

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import web.connect.newsdemo.R
import web.connect.newsdemo.data.models.NewsResponse
import web.connect.newsdemo.databinding.ItemAllNewsBinding

enum class NewsEventAction {
    ON_DELETE,
    ON_SHOW
}

class AllNewsAdapter(val onAction: (article: NewsResponse.Article, action: NewsEventAction) -> Unit) :
    ListAdapter<NewsResponse.Article, AllNewsAdapter.NewsViewHolder>(NewsDiffCallBack) {

    inner class NewsViewHolder(private val binding: ItemAllNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val news = getItem(adapterPosition)
                onAction(news, NewsEventAction.ON_SHOW)
            }

            binding.ivDelete.setOnClickListener {
                val news = getItem(adapterPosition)
                onAction(news, NewsEventAction.ON_DELETE)
            }
        }

        fun bind(news: NewsResponse.Article) {
            binding.apply {
                ivNews.load(news.urlToImage) {
                    crossfade(true)
                    placeholder(R.mipmap.no_image)
                    transformations(RoundedCornersTransformation(5f))
                    error(R.mipmap.no_image)
                }
                tvNewsName.text = news.source.name
                tvTitle.text = news.title
                tvDescription.text = news.description
                tvAuthor.text = news.author

                var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val newDate = spf.parse(news.publishedAt)
                spf = SimpleDateFormat("dd MMM yyyy")
                tvPublishedDate.text = spf.format(newDate)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemAllNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        news?.let {
            holder.bind(news)
        }
    }


    object NewsDiffCallBack : DiffUtil.ItemCallback<NewsResponse.Article>() {
        override fun areItemsTheSame(
            oldItem: NewsResponse.Article,
            newItem: NewsResponse.Article
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NewsResponse.Article,
            newItem: NewsResponse.Article
        ): Boolean {
            return oldItem == newItem
        }
    }

}