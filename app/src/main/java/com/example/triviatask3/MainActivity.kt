package com.example.triviatask3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.triviatask3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private  val fragmentQuestion = QuestionFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.container,fragmentQuestion).commit()
    }
}