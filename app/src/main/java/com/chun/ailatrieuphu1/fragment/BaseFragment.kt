package com.chun.ailatrieuphu1.fragment

import android.media.MediaPlayer
import androidx.fragment.app.Fragment
import com.chun.ailatrieuphu1.activity.BaseActivity

open class BaseFragment : Fragment(){
    open fun onBackPressForFragment(){
        if ( activity is BaseActivity){
            (activity as BaseActivity).onBackRoot()
        }
    }

    open fun onFinish(){
        if ( activity is BaseActivity){
            (activity as BaseActivity).onFinish()
        }
    }
}