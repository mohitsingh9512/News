package com.example.tnews.ui.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tnews.R
import com.example.tnews.databinding.SourcesItemViewBinding
import com.example.tnews.network.response.NewsSource
import com.example.tnews.utlis.BindingAdapter

class SourcesAdapter(var listener : SourcesListener) : RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder>() , BindingAdapter<List<NewsSource>> {

    private var list = emptyList<NewsSource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        val binding = DataBindingUtil.inflate<SourcesItemViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.sources_item_view,
            parent,
            false
        )
        return SourcesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun setData(data: List<NewsSource>?) {
        list = data ?: emptyList()
        if(list.isNotEmpty())
            notifyDataSetChanged()
    }

    inner class SourcesViewHolder(private val binding: SourcesItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(newsSource : NewsSource){
            binding.model = newsSource
            binding.root.setOnClickListener{
                listener.sourceClicked(newsSource)
            }
            binding.executePendingBindings()
        }
    }

}

interface SourcesListener {
    fun sourceClicked(newsSource : NewsSource)
}