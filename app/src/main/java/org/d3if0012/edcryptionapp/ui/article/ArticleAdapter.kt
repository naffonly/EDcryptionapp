package org.d3if0012.edcryptionapp.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0012.edcryptionapp.R
import org.d3if0012.edcryptionapp.databinding.ItemArticleBinding
import org.d3if0012.edcryptionapp.model.Article
import org.d3if0012.edcryptionapp.network.ArticleApi

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val data =  mutableListOf<Article>()

    fun updateData(newData : List<Article>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ItemArticleBinding
    ) :RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article) = with(binding){
            tittleArticle.text = article.judul
            descArticle.text = article.desc

            Glide.with(imgArticle.context)
                .load(ArticleApi.getImgUrl(article.imgId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imgArticle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val binding = ItemArticleBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}