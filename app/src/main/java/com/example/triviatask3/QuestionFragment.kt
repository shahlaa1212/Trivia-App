package com.example.triviatask3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.triviatask3.databinding.FragmentQuestionsBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class QuestionFragment:BaseFragment<FragmentQuestionsBinding>() {
    private val client = OkHttpClient()
    var index:Int = 0
    private val winFragment = WinFragment()
    override val LOG_TAG: String
        get() = javaClass.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentQuestionsBinding = FragmentQuestionsBinding::inflate

    override fun setup() {
        showInfo()
        getNextQuestion()
        getCorrectAnswer()
    }

    override fun addCallBack() {
    }


    private fun showInfo() {
        if (index>=10) setFragment()else {

            val url =
                "https://opentdb.com/api.php?amount=10&category=10&difficulty=easy&type=multiple"
            val request = Request.Builder().url(url).build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("FAILED , ${e.message.toString()}")
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string().let { jsonString ->
                        val homeInfo = Gson().fromJson(jsonString, TriviaQuestion::class.java)
                        val info = homeInfo.results?.toMutableList()?.get(index)
                        activity?.runOnUiThread {
                            binding?.textQuestion?.text = info?.question
                            binding?.textFirstAnswer?.text = info?.correct_answer
                            binding?.textSecondAnswer?.text = info?.incorrect_answers?.get(0)
                            binding?.textThirdAnswer?.text = info?.incorrect_answers?.get(1)
                            binding?.textFourthAnswer?.text = info?.incorrect_answers?.get(2)
                            binding?.textPoints?.text = index.toString()
                            println(index)
                            isEnabledButton(value = false)
                        }
                        index++
                    }



                }

            })
            //end of (if-else) statement
        }
    }
    private fun setFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container,winFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isEnabledButton(value:Boolean){
        when(value){
            false-> binding?.buttonNext?.isEnabled = false
            true -> binding?.buttonNext?.isEnabled = true
        }
    }

    private fun getNextQuestion() {

        binding?.buttonNext?.setOnClickListener {
            showInfo()
            getDefaultStyle()
        }
    }

    @SuppressLint("ResourceType")
    private fun getCorrectAnswer() {
        binding?.textFirstAnswer?.setOnClickListener {
            getAnswer()
        }
        binding?.textSecondAnswer?.setOnClickListener {
            getAnswer()
        }
        binding?.textThirdAnswer?.setOnClickListener {
            getAnswer()
        }
        binding?.textFourthAnswer?.setOnClickListener {
            getAnswer()
        }
    }

    private fun getAnswer() {
        binding?.textFirstAnswer?.setBackgroundResource(R.drawable.correct_answer)
        binding?.textSecondAnswer?.setBackgroundResource(R.drawable.wrong_answer)
        binding?.textThirdAnswer?.setBackgroundResource(R.drawable.wrong_answer)
        binding?.textFourthAnswer?.setBackgroundResource(R.drawable.wrong_answer)
        isEnabledButton(true)



    }
    private fun getDefaultStyle(){
        binding?.textFirstAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textSecondAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textThirdAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textFourthAnswer?.setBackgroundResource(R.drawable.text_background)
        isEnabledButton(true)

    }


}