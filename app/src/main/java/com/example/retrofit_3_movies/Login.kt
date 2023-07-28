package com.example.retrofit_3_movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retrofit_3_movies.databinding.ActivityDetailsMovieBinding
import com.example.retrofit_3_movies.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val login = binding.eTLogin.text
        val contra = binding.EtContraseA.text

        initListener()

    }

    private fun validardatos1(id: String, contra: String): Boolean {

        if (id == "ejemplo@idat.edu.pe" && contra =="123456"){
            return true
        }else{
            Toast.makeText(this, "Id o contra invalida" , Toast.LENGTH_LONG).show()
            return false
        }

    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {

            val login: String = binding.eTLogin.text.toString()
            val contra = binding.EtContraseA.text.toString()
            if (validardatos1(login,contra)){
                navegateToMainActivity()
            }

        }
    }



    private fun navegateToMainActivity(){

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }
}