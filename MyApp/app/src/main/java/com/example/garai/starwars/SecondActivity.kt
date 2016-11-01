package com.example.garai.starwars

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        backToTop()

        moveResult()

    }

    /**
     * backボタンが押されたとき
     */
    protected fun backToTop() {
        val returnButton = findViewById(R.id.button_back) as Button
        returnButton.setOnClickListener { finish() }

    }

    /**
     * resultボタンが押されたとき
     */
    protected fun moveResult() {
        val button = findViewById(R.id.button_result) as Button
        button.setOnClickListener {
            val intent = Intent(application, ResultActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
