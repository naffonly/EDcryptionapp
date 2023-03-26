package org.d3if0012.edcryptionapp

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if0012.edcryptionapp.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
            encodeReset()
        }
        binding.decodeResetBotton.setOnClickListener {
            decodeReset()
        }

    }

    private fun encode(){
        val encodeText = binding.encodeInput.text.toString()
        if (TextUtils.isEmpty(encodeText)){
            Toast.makeText(this, "Text tidak Kosong", Toast.LENGTH_SHORT).show()
        }

        try {
        val text = encodeText
        val encoder: Base64.Encoder = Base64.getEncoder()
        val encoded: String = encoder.encodeToString(text.toByteArray())
        binding.decodeInput.setText(encoded)
        }catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this, "Text tidak valid untuk di Encode", Toast.LENGTH_SHORT).show()
        }
    }

    private fun decode(){
        val decodeText = binding.decodeInput.text.toString()
        if (TextUtils.isEmpty(decodeText)){
            Toast.makeText(this, "Text tidak Kosong", Toast.LENGTH_SHORT).show()
        }

    try {
        val dtext = decodeText
        val decoder: Base64.Decoder = Base64.getMimeDecoder()
        val decoded  = String(decoder.decode(dtext))
        binding.encodeInput.setText(decoded)
    }catch (e: Exception){
        e.printStackTrace()
        Toast.makeText(this, "Text tidak valid untuk di Decode", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun encodeCopy(){

        val encodeText = binding.encodeInput.text.toString()

        if (TextUtils.isEmpty(encodeText)){
            Toast.makeText(this, "Isian Masih Kosong,Tidak dapat disalin", Toast.LENGTH_SHORT).show()
        }else{
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("text label", encodeText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Text Disalin", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun decodeCopy(){


        val decodeText = binding.decodeInput.text.toString()

        if (TextUtils.isEmpty(decodeText)){
            Toast.makeText(this, "Isian Masih Kosong,Tidak dapat disalin", Toast.LENGTH_SHORT).show()
        }else{

            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("text label", decodeText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Text Disalin", Toast.LENGTH_SHORT).show()
        }

    }

    private fun encodeReset(){
        binding.encodeInput.text = null
    }

    private fun decodeReset(){
        binding.decodeInput.text = null
    }


}