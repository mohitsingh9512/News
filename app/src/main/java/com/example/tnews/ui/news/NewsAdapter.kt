package com.example.tnews.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tnews.R
import com.example.tnews.databinding.NewsItemViewBinding
import com.example.tnews.network.response.Article
import com.example.tnews.utlis.BindingAdapter

class NewsAdapter(var listener : NewsListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() , BindingAdapter<List<Article>> {

    private var list = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = DataBindingUtil.inflate<NewsItemViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item_view,
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun setData(data: List<Article>?) {
        list = data ?: emptyList()
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: NewsItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(article : Article){
            binding.model = article
            binding.root.setOnClickListener{
                listener.newsClicked(article)
            }
            binding.executePendingBindings()
        }
    }

}

interface NewsListener {
    fun newsClicked(article : Article)
}