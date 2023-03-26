package org.d3if0012.edcryptionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if0012.edcryptionapp.databinding.ActivityMainBinding
import java.util.Base64

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.EncodeBotton.setOnClickListener {
            encode()
        }

        binding.decodeBotton.setOnClickListener {
            decode()
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
            Toast.makeText(applicationContext, "Text tidak valid untuk di Encode", Toast.LENGTH_SHORT).show()
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
        Toast.makeText(applicationContext, "Text tidak valid untuk di Decode", Toast.LENGTH_SHORT).show()
        }
    }
}