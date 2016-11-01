package com.example.garai.starwars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.example.garai.starwars.R.id.button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveInputPage()
    }


    /**
     * startボタンが押されたとき
     */
    protected fun moveInputPage() {
        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            val intent = Intent(application, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
