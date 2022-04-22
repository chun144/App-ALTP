package com.chun.ailatrieuphu1.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils.loadAnimation
import com.chun.ailatrieuphu1.R
import com.chun.ailatrieuphu1.activity.MainActivity
import com.chun.ailatrieuphu1.databinding.FirstFragmentBinding

class FirstFragment: BaseFragment(), View.OnClickListener{
    private lateinit var binding: FirstFragmentBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstFragmentBinding.inflate(inflater, container, false)
        binding.btPlay.setOnClickListener(this)
        binding.btHighScore.setOnClickListener(this)

        applyRotate()
        startPlayer()
        return binding.root
    }

    private fun startPlayer() {
        mediaPlayer = MediaPlayer.create(context, R.raw.bgmusic)
        mediaPlayer.start()
        mediaPlayer.isLooping = true
    }

    private fun stopPlayer() {
        mediaPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlayer()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.bt_play->{
                (activity as MainActivity).openSecondFragment(null, null, null, null, null)
                stopPlayer()
            }

            R.id.bt_high_score->{
                (activity as MainActivity).openHighScoreFragment()
                stopPlayer()
            }
        }
    }

    private fun applyRotate() {
        val ani = loadAnimation(context, R.anim.rotate_anim)
        binding.iv.startAnimation(ani)
    }

}