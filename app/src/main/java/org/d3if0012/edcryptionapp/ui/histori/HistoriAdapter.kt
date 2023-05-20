package org.d3if0012.edcryptionapp.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0012.edcryptionapp.databinding.ItemHistoryBinding
import org.d3if0012.edcryptionapp.db.EdcEntity
import org.d3if0012.edcryptionapp.model.onDecode
import org.d3if0012.edcryptionapp.model.onEncode
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter: ListAdapter<EdcEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<EdcEntity>(){
                override fun areItemsTheSame(oldItem: EdcEntity, newItem: EdcEntity): Boolean {
                   return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: EdcEntity, newItem: EdcEntity): Boolean {
                  return oldItem == newItem
                }

            }
    }


    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dataFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id","ID"))

        fun bind(item : EdcEntity) = with(binding){

            val rs = if (item.isEncode) item.onEncode() else item.onDecode()

                encodeText.text = rs.encode
                decodeText.text = rs.decode
                tglText.text = dataFormatter.format(Date(item.tanggal))


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position))

    }

}