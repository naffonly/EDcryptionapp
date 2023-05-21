package org.d3if0012.edcryptionapp.ui.histori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*
import org.d3if0012.edcryptionapp.R
import org.d3if0012.edcryptionapp.databinding.ItemHistoryBinding
import org.d3if0012.edcryptionapp.db.EdcDB
import org.d3if0012.edcryptionapp.db.EdcEntity
import org.d3if0012.edcryptionapp.model.onDecode
import org.d3if0012.edcryptionapp.model.onEncode
import java.text.SimpleDateFormat
import java.util.*


class HistoriAdapter(val fragment: Fragment): ListAdapter<EdcEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

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

        fun bind(item : EdcEntity,view: View, fragment: Fragment) = with(binding){

            val rs = if (item.isEncode) item.onEncode() else item.onDecode()

                encodeText.text = rs.encode
                decodeText.text = rs.decode
                tglText.text = dataFormatter.format(Date(item.tanggal))
                idBtn.setOnClickListener {
                    hapusData(item.id,view.context)
                }
        }


        private fun hapusData(id: Long, context: Context){
            val db = EdcDB.getInstance(context)
            val EdcDao = db.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(context.getString(R.string.konfirmasi_hapus))
                .setPositiveButton(context.getString(R.string.hapus)) {_, _ ->
                    CoroutineScope(Dispatchers.IO).launch{
                        withContext(Dispatchers.IO) {
                            EdcDao.deleteDataById(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position),holder.itemView,fragment)
    }





}