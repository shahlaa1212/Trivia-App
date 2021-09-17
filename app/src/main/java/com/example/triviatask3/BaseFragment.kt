package com.example.triviatask3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>: Fragment() {
    abstract  val LOG_TAG: String
    abstract  val bindingInflater: (LayoutInflater) -> VB
    private var _binding: ViewBinding? = null
    var binding: VB?
        get() = _binding as VB?
        set(value) = TODO()

    abstract fun setup()
    abstract fun addCallBack()
    protected fun log(value:String){
        Log.v(LOG_TAG,value)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)
        return _binding?.root
    }
}