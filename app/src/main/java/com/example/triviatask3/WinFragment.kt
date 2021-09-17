package com.example.triviatask3

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.triviatask3.databinding.FragmentWinningBinding

class WinFragment:BaseFragment<FragmentWinningBinding>() {
    override val LOG_TAG: String
        get()= "win fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentWinningBinding = FragmentWinningBinding::inflate
    override fun setup() {
        binding?.buttonSong?.setOnClickListener{
            val url = "https://www.youtube.com/watch?v=0aUav1lx3rA"
            val intent= Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun addCallBack() {
    }
}