package org.d3if0012.edcryptionapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0012.edcryptionapp.R
import org.d3if0012.edcryptionapp.databinding.FragmentHomeBinding
import org.d3if0012.edcryptionapp.model.DataEncryption

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.menu_about){
            findNavController().navigate(
                R.id.nav_home_to_about
            )
            return true
        }
        return  super.onOptionsItemSelected(item)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getDataEncrytion().observe(requireActivity(), { showResult(it) })



        binding.historiButton.setOnClickListener {
            findNavController().navigate(
                R.id.nav_home_to_histori
            )
        }
        binding.EncodeBotton.setOnClickListener {
            encode()
        }

        binding.encodeCopyBotton.setOnClickListener {
            encodeCopy()
        }

        binding.decodeBotton.setOnClickListener {
            decode()
        }

        binding.decodeCopyBotton.setOnClickListener {
            decodeCopy()
        }


        binding.encodeResetBotton.setOnClickListener {
            binding.encodeInput.text = null
        }
        binding.decodeResetBotton.setOnClickListener {
            binding.decodeInput.text = null

        }
    }

    private  fun encodeCopy(){

        val encodeText = binding.encodeInput.text.toString()

        if (TextUtils.isEmpty(encodeText)){
            Toast.makeText(context, "Isian Masih Kosong,Tidak dapat disalin", Toast.LENGTH_SHORT).show()
        }else{
            var clipboard = getSystemService(context!!, ClipboardManager::class.java) as ClipboardManager
            val clip = ClipData.newPlainText("text label", encodeText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Text Disalin", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun decodeCopy(){

        val decodeText = binding.decodeInput.text.toString()

        if (TextUtils.isEmpty(decodeText)){
            Toast.makeText(context, "Isian Masih Kosong,Tidak dapat disalin", Toast.LENGTH_SHORT).show()
        }else{

            var clipboard = getSystemService(context!!, ClipboardManager::class.java) as ClipboardManager
            val clip = ClipData.newPlainText("text label", decodeText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Isian Text Disalin", Toast.LENGTH_SHORT).show()
        }

    }


    private fun encode(){
        val encodeText = binding.encodeInput.text.toString()
        val decodeText = binding.decodeInput.text.toString()
        if (TextUtils.isEmpty(encodeText)){
            Toast.makeText(context, "Text Kosong", Toast.LENGTH_SHORT).show()
        }

        try {
           viewModel.onEncode(encodeText,decodeText)
        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(context, "Text tidak valid untuk di Encode", Toast.LENGTH_SHORT).show()
        }
    }

    private fun decode(){
        val encodeText = binding.encodeInput.text.toString()
        val decodeText = binding.decodeInput.text.toString()
        if (TextUtils.isEmpty(decodeText)){
            Toast.makeText(context, "Isian Text Kosong", Toast.LENGTH_SHORT).show()
        }

        try {
            viewModel.onDecode(encodeText,decodeText)
        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(context, "Text tidak valid untuk di Decode", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResult(result: DataEncryption?){
        if (result== null) return
        binding.encodeInput.setText(result.encode)
        binding.decodeInput.setText(result.decode)
    }


}