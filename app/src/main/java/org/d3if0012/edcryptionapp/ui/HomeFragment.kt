package org.d3if0012.edcryptionapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if0012.edcryptionapp.databinding.FragmentHomeBinding
import org.d3if0012.edcryptionapp.model.DataEncryption

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getDataEncrytion().observe(this,{
            showResult(it)
        })

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
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//
//
//    }

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
        Log.d("ACtivity",result.encode)
        Log.d("DECtivity",result.decode)
        binding.encodeInput.setText(result.encode)
        binding.decodeInput.setText(result.decode)
    }




}