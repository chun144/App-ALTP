package com.chun.ailatrieuphu1.fragment

import android.app.Dialog
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.chun.ailatrieuphu1.R
import com.chun.ailatrieuphu1.activity.MainActivity
import com.chun.ailatrieuphu1.databinding.SecondFragmentBinding

class SecondFragment: BaseFragment(), View.OnClickListener {
    private lateinit var binding: SecondFragmentBinding
    private lateinit var dialog: Dialog
    private lateinit var mediaPlayer: MediaPlayer
    private var level: Int? = null
    private var refresh: Boolean? = null
    private var help5050: Boolean? = null
    private var call: Boolean? = null
    private var audience: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondFragmentBinding.inflate(inflater, container, false)
        initData()
        binding.btnTt.setOnClickListener(this)
        return binding.root
    }

    private fun initData() {
        if (arguments != null){
            level = requireArguments().getInt("level")
            refresh = requireArguments().getBoolean("refresh")
            help5050 = requireArguments().getBoolean("help5050")
            call = requireArguments().getBoolean("call")
            audience = requireArguments().getBoolean("audience")
            when (level) {
                1 -> {
                    binding.tv1.setBackgroundResource(R.drawable.bg_nomal)
                }
                2 -> {
                    binding.tv2.setBackgroundResource(R.drawable.bg_nomal)
                }
                3 -> {
                    binding.tv3.setBackgroundResource(R.drawable.bg_nomal)
                }
                4 -> {
                    binding.tv4.setBackgroundResource(R.drawable.bg_nomal)
                }
                5 -> {
                    binding.tv5.setBackgroundResource(R.drawable.bg_nomal)
                }
                6 -> {
                    binding.tv6.setBackgroundResource(R.drawable.bg_nomal)
                }
                7 -> {
                    binding.tv7.setBackgroundResource(R.drawable.bg_nomal)
                }
                8 -> {
                    binding.tv8.setBackgroundResource(R.drawable.bg_nomal)
                }
                9 -> {
                    binding.tv9.setBackgroundResource(R.drawable.bg_nomal)
                }
                10 -> {
                    binding.tv10.setBackgroundResource(R.drawable.bg_nomal)
                }
                11 -> {
                    binding.tv11.setBackgroundResource(R.drawable.bg_nomal)
                }
                12 -> {
                    binding.tv12.setBackgroundResource(R.drawable.bg_nomal)
                }
                13 -> {
                    binding.tv13.setBackgroundResource(R.drawable.bg_nomal)
                }
                14 -> {
                    binding.tv14.setBackgroundResource(R.drawable.bg_nomal)
                }
            }
            mediaPlayer = MediaPlayer.create(context, R.raw.background_music_b)
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        } else {
            mediaPlayer = MediaPlayer.create(context, R.raw.luatchoi_b)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { openDialog() }
            Handler().postDelayed(
                { binding.tv5.setBackgroundResource(R.drawable.bg_nomal) },
                5000
            )
            Handler().postDelayed({
                binding.tv5.setBackgroundResource(0)
                binding.tv10.setBackgroundResource(R.drawable.bg_nomal)
            }, 5500)
            Handler().postDelayed({
                binding.tv10.setBackgroundResource(0)
                binding.tv15.setBackgroundResource(R.drawable.bg_nomal)
            }, 5800)
            Handler().postDelayed({ binding.tv15.setBackgroundResource(0) }, 6000)
        }
    }

    private fun openDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_play)
        dialog.show()
        val buttonOk: Button = dialog.findViewById(R.id.dialog_ok)
        val buttonCancel: Button = dialog.findViewById(R.id.dialog_cancel)
        buttonOk.setOnClickListener(this)
        buttonCancel.setOnClickListener(this)
    }

    private fun stopPlayer() {
        mediaPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer()
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id) {
                R.id.btn_tt->{
                    if (arguments == null) {
                        openDialog()
                    } else {
                        (activity as MainActivity).openPlayFragment(level, refresh, help5050, audience, call)
                        stopPlayer()
                    }
                }
                R.id.dialog_ok->{
                    (activity as MainActivity).openPlayFragment(level, refresh, help5050, audience, call)
                    dialog.cancel()// Context, this, etc.
                    Toast.makeText(context,"Play", Toast.LENGTH_SHORT).show()
                    stopPlayer()
                }
                R.id.dialog_cancel->{
                    Toast.makeText(context,"Cancel", Toast.LENGTH_SHORT).show()
                    onFinish()
                }
            }
        }
    }


}