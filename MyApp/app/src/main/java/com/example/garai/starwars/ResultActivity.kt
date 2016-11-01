package com.example.garai.starwars

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        backToTop()

    }

    /**
     * topボタンが押されたとき
     */
    protected fun backToTop() {
        val returnButton = findViewById(R.id.button_return) as Button
        returnButton.setOnClickListener { finish() }
    }
}
