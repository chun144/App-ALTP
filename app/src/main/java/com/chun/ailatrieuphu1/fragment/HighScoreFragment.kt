package com.chun.ailatrieuphu1.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chun.ailatrieuphu1.R
import com.chun.ailatrieuphu1.activity.MainActivity
import com.chun.ailatrieuphu1.adapter.HighScoreAdapter
import com.chun.ailatrieuphu1.databinding.HighScoreFragmentBinding
import com.chun.ailatrieuphu1.datasqlife.ALTPDao
import com.chun.ailatrieuphu1.model.HighScore

class HighScoreFragment : BaseFragment(), HighScoreAdapter.IHighScore{
    private lateinit var binding: HighScoreFragmentBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var dao: ALTPDao
    private var highScores = mutableListOf<HighScore>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HighScoreFragmentBinding.inflate(inflater, container, false)
        mediaPlayer = MediaPlayer.create(context, R.raw.background_music_c)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        initData()

        binding.ibBack.setOnClickListener {
            (activity as MainActivity).openFirstFragment()
            mediaPlayer.release()
        }

        val adapter = HighScoreAdapter(this)
        binding.rcScore.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        //đổ adapter len
        binding.rcScore.adapter = adapter

        return binding.root
    }

    override fun getCount(): Int {
        return highScores.size
    }

    override fun getData(position: Int): HighScore {
        return highScores[position]
    }

    override fun onCLickItem(position: Int) {
        TODO("Not yet implemented")
    }

    private fun initData() {
        dao = ALTPDao(requireContext())
        highScores = dao.query20HighScore()
    }
}