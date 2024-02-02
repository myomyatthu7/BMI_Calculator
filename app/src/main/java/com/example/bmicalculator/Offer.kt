package com.example.bmicalculator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Offer : AppCompatActivity() {
    lateinit var sp : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    private var count  = 0
    private lateinit var tvCount : TextView
    private lateinit var etTest : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer)
        val btnLike = findViewById<Button>(R.id.btnLike)
        val btnUnLike = findViewById<Button>(R.id.btnUnlike)
        val ivLike = findViewById<ImageView>(R.id.ivLike)
        val ivUnlike = findViewById<ImageView>(R.id.ivUnlike)
        etTest = findViewById(R.id.etTest)
        tvCount = findViewById<TextView>(R.id.tvCount)
        sp = getSharedPreferences("sp_like", MODE_PRIVATE)
        editor = sp.edit()
        tvCount.visibility = VISIBLE
        btnLike.setOnClickListener {
            count++
            ivLike.visibility = VISIBLE
            ivUnlike.visibility = INVISIBLE
            val likeMessage = "$count:likes this page"
            tvCount.text = likeMessage
        }
        btnUnLike.setOnClickListener {
            count--
            ivLike.visibility = INVISIBLE
            ivUnlike.visibility = VISIBLE
            val likeMessage = "$count:likes this page"
            tvCount.text = likeMessage
        }
    }
    @SuppressLint("SetTextI18n")
    override fun onPause() {
        super.onPause()
        tvCount.text = "$count:likes this page"
        val like = tvCount.text
        editor.putString("sp_count",like.toString())
            .commit()
    }
    override fun onResume() {
        super.onResume()
        val like = sp.getString("sp_count",null)
        tvCount.text = like
    }
}