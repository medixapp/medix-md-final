package com.example.medix.ui.nav.article

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medix.data.api.response.DataItem
import com.example.medix.databinding.ItemArticleBinding
import com.example.medix.ui.detailarticle.DetailArticleActivity

class ArticleAdapter: ListAdapter<DataItem, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(articleItem: DataItem){
            Glide.with(binding.root.context)
                .load(articleItem.image)
                .into(binding.ivArticle)
            binding.tvTitlemedi.text = articleItem.title
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailArticleActivity::class.java)
                intent.putExtra(DetailArticleActivity.EXTRA_ID, articleItem.id)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItem> =
            object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: DataItem,
                    newItem: DataItem
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}