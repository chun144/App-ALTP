package com.chun.ailatrieuphu1.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import com.chun.ailatrieuphu1.R
import com.chun.ailatrieuphu1.activity.MainActivity
import com.chun.ailatrieuphu1.databinding.PlayFragmentBinding
import com.chun.ailatrieuphu1.datasqlife.ALTPDao
import com.chun.ailatrieuphu1.model.Question
import com.skydoves.progressview.ProgressView

class PlayFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: PlayFragmentBinding
    private lateinit var dao: ALTPDao
    private lateinit var questions: MutableList<Question>
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var dialogCall: Dialog
    private lateinit var dialogAudience: Dialog
    private lateinit var dialogEnd: Dialog
    private lateinit var asyn: AsyncTask<Int, String, String>
    private var level: Int = 0
    private var chooseCase: Int = 0
    private var refresh: Boolean = true
    private var help5050: Boolean = true
    private var call: Boolean = true
    private var audience: Boolean = true
    private var score: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayFragmentBinding.inflate(inflater, container, false)
        setOnClick()
        initData()
        binding.iv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_anim))
        return binding.root
    }

    private fun queryQuestion() {
        dao = ALTPDao(requireContext())
        questions = dao.query15Question()
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion() {
        binding.level.text = "Câu ${level + 1}"
        binding.question.text = questions[level].question
        binding.caseA.text = "A: ${questions[level].caseA}"
        binding.caseB.text = "B: ${questions[level].caseB}"
        binding.caseC.text = "C: ${questions[level].caseC}"
        binding.caseD.text = "D: ${questions[level].caseD}"
    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        intAsyn()
        queryQuestion()
        if (arguments != null) {
            level = requireArguments().getInt("level")
            refresh = requireArguments().getBoolean("refresh")
            help5050 = requireArguments().getBoolean("help5050")
            call = requireArguments().getBoolean("call")
            audience = requireArguments().getBoolean("audience")
        }
        if (!refresh) {
            binding.ibRefresh.setImageResource(R.drawable.player_button_image_help_change_question_x)
            binding.ibRefresh.isClickable = false
        }
        if (!help5050) {
            binding.ib5050.setImageResource(R.drawable.player_button_image_help_5050_x)
            binding.ib5050.isClickable = false
        }
        if (!call) {
            binding.ibCall.setImageResource(R.drawable.player_button_image_help_call_x)
            binding.ibCall.isClickable = false
        }
        if (!audience) {
            binding.ibAudience.setImageResource(R.drawable.player_button_image_help_audience_x)
            binding.ibAudience.isClickable = false
        }

        updateQuestion()

        when (level) {
            0 -> {
                binding.score.text = "200,000"
                startPlayer(R.raw.ques1)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            1 -> {
                binding.score.text = "400,000"
                startPlayer(R.raw.ques2)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            2 -> {
                binding.score.text = "600,000"
                startPlayer(R.raw.ques3)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            3 -> {
                binding.score.text = "1,000,000"
                startPlayer(R.raw.ques4)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            4 -> {
                binding.score.text = "2,000,000"
                startPlayer(R.raw.ques5)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            5 -> {
                score = "2,000,000"
                binding.score.text = "3,000,000"
                startPlayer(R.raw.ques6)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            6 -> {
                score = "3,000,000"
                binding.score.text = "6,000,000"
                startPlayer(R.raw.ques7)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            7 -> {
                score = "6,000,000"
                binding.score.text = "10,000,000"
                startPlayer(R.raw.ques8)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            8 -> {
                score = "10,000,000"
                binding.score.text = "14,000,000"
                startPlayer(R.raw.ques9)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            9 -> {
                score = "14,000,000"
                startPlayer(R.raw.ques10)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
                binding.score.text = "22,000,000"
            }
            10 -> {
                score = "22,000,000"
                binding.score.text = "30,000,000"
                startPlayer(R.raw.ques11)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            11 -> {
                score = "30,000,000"
                binding.score.text = "40,000,000"
                startPlayer(R.raw.ques12)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            12 -> {
                score = "40,000,000"
                binding.score.text = "60,000,000"
                startPlayer(R.raw.ques13)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            13 -> {
                score = "60,000,000"
                binding.score.text = "85,000,000"
                startPlayer(R.raw.ques14)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
            14 -> {
                score = "85,000,000"
                binding.score.text = "150,000,000"
                startPlayer(R.raw.ques15)
                mediaPlayer.setOnCompletionListener {
                    startPlayerBackground()
                }
            }
        }
    }

    private fun intAsyn() {
        //p1: tham so truyen vao
        //p2: kieu du lieu cap nhat tren main thread
        //p3: dieu du lieu ket qua cuoi cung
        asyn = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Int, String, String>() {
            var count = 31
            override fun doInBackground(vararg values: Int?): String {
                for (i in values[0]!!..values[1]!!) {
                    //chuyen noi dung den main thread, de main thread cap nhat giao dien
                    publishProgress(count.toString())
                    if (count > 0) {
                        count--
                    }
                    SystemClock.sleep(1000)
                }
                return "0"
            }

            //chay tren main thread
            override fun onProgressUpdate(vararg values: String?) {
                binding.time.text = count.toString()
            }

            // chay tren main thread
            override fun onPostExecute(result: String) {
                mediaPlayer.release()
                openDialogEnd(true)
            }
        }
        //chay asyntask
        //khi execute thi doInBackground se chay tren thread (khac main thread)
        asyn.execute(0, 30, 1000)

    }

    private fun startPlayer(raw: Int) {
        mediaPlayer = MediaPlayer.create(context, raw)
        mediaPlayer.start()
    }

    private fun startPlayerBackground() {
        mediaPlayer.release()
        startPlayer(R.raw.background_music)
        mediaPlayer.isLooping = true
    }

    private fun setOnClick() {
        binding.caseA.setOnClickListener(this)
        binding.caseB.setOnClickListener(this)
        binding.caseC.setOnClickListener(this)
        binding.caseD.setOnClickListener(this)
        binding.ibStop.setOnClickListener(this)
        binding.ibRefresh.setOnClickListener(this)
        binding.ib5050.setOnClickListener(this)
        binding.ibAudience.setOnClickListener(this)
        binding.ibCall.setOnClickListener(this)
        binding.caseA.isClickable = true
        binding.caseB.isClickable = true
        binding.caseC.isClickable = true
        binding.caseD.isClickable = true
    }

    private fun checkChooseCase(chooseCase: Int, trueCase: Int) {
        if (chooseCase == trueCase) {
            level++
            when (trueCase) {
                1 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.true_a)
                    mediaPlayer1.start()
                    binding.caseA.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseA.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseA.setBackgroundResource(R.drawable.bg_true2)
                        if (level == 15) {
                            val mediaPlayer2 = MediaPlayer.create(context, R.raw.best_player)
                            mediaPlayer1.start()
                            mediaPlayer2.setOnCompletionListener {
                                mediaPlayer2.release()
                                openDialogEnd(false)
                            }
                        } else {
                            (activity as MainActivity).openSecondFragment(level, refresh, help5050, audience, call)
                        }
                    }
                }
                2 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.true_b2)
                    mediaPlayer1.start()
                    binding.caseB.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseB.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseB.setBackgroundResource(R.drawable.bg_true2)
                        if (level == 15) {
                            val mediaPlayer2 = MediaPlayer.create(context, R.raw.best_player)
                            mediaPlayer1.start()
                            mediaPlayer2.setOnCompletionListener {
                                mediaPlayer2.release()
                                openDialogEnd(false)
                            }
                        } else {
                            (activity as MainActivity).openSecondFragment(level, refresh, help5050, audience, call)
                        }
                    }
                }
                3 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.true_c3)
                    mediaPlayer1.start()
                    binding.caseC.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseC.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseC.setBackgroundResource(R.drawable.bg_true2)
                        if (level == 15) {
                            val mediaPlayer2 = MediaPlayer.create(context, R.raw.best_player)
                            mediaPlayer1.start()
                            mediaPlayer2.setOnCompletionListener {
                                mediaPlayer2.release()
                                openDialogEnd(false)
                            }
                        } else {
                            (activity as MainActivity).openSecondFragment(level, refresh, help5050, audience, call)
                        }
                    }
                }
                4 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.true_d2)
                    mediaPlayer1.start()
                    binding.caseD.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseD.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseD.setBackgroundResource(R.drawable.bg_true2)
                        if (level == 15) {
                            val mediaPlayer2 = MediaPlayer.create(context, R.raw.best_player)
                            mediaPlayer1.start()
                            mediaPlayer2.setOnCompletionListener {
                                mediaPlayer2.release()
                                openDialogEnd(false)
                            }
                        } else {
                            (activity as MainActivity).openSecondFragment(level, refresh, help5050, audience, call)
                        }
                    }
                }
            }
        } else {
            when (chooseCase) {
                1 -> {
                    binding.caseA.setBackgroundResource(R.drawable.bg_faile1)
                }
                2 -> {
                    binding.caseB.setBackgroundResource(R.drawable.bg_faile1)
                }
                3 -> {
                    binding.caseC.setBackgroundResource(R.drawable.bg_faile1)
                }
                4 -> {
                    binding.caseD.setBackgroundResource(R.drawable.bg_faile1)
                }
            }
            when (trueCase) {
                1 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.lose_a)
                    mediaPlayer1.start()
                    binding.caseA.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseA.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseA.setBackgroundResource(R.drawable.bg_true2)
                        openDialogEnd(true)
                    }
                }
                2 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.lose_b2)
                    mediaPlayer1.start()
                    binding.caseB.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseB.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseB.setBackgroundResource(R.drawable.bg_true2)
                        openDialogEnd(true)
                    }
                }
                3 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.lose_c)
                    mediaPlayer1.start()
                    binding.caseC.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseC.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseC.setBackgroundResource(R.drawable.bg_true2)
                        openDialogEnd(true)
                    }
                }
                4 -> {
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.lose_d2)
                    mediaPlayer1.start()
                    binding.caseD.setBackgroundResource(R.drawable.bg_true1)
                    binding.caseD.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_anim))
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        binding.caseD.setBackgroundResource(R.drawable.bg_true2)
                        openDialogEnd(true)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openAudienceDialog() {
        dialogAudience = Dialog(requireContext())
        dialogAudience.setContentView(R.layout.audience_dialog)
        val buttonCancel: Button = dialogAudience.findViewById(R.id.bt_back)
        val textView1: TextView = dialogAudience.findViewById(R.id.tv_1)
        val textView2: TextView = dialogAudience.findViewById(R.id.tv_2)
        val textView3: TextView = dialogAudience.findViewById(R.id.tv_3)
        val textView4: TextView = dialogAudience.findViewById(R.id.tv_4)
        val progressView1: ProgressView = dialogAudience.findViewById(R.id.progress_1)
        val progressView2: ProgressView = dialogAudience.findViewById(R.id.progress_2)
        val progressView3: ProgressView = dialogAudience.findViewById(R.id.progress_3)
        val progressView4: ProgressView = dialogAudience.findViewById(R.id.progress_4)

        when (questions[level].trueCase) {
            1 -> {
                textView1.text = "80%"
                textView2.text = "10%"
                textView3.text = "4%"
                textView4.text = "6%"
                progressView1.progress = 80F
                progressView2.progress = 10F
                progressView3.progress = 4F
                progressView4.progress = 6F
            }
            2 -> {
                textView1.text = "6%"
                textView2.text = "82%"
                textView3.text = "8%"
                textView4.text = "4%"
                progressView1.progress = 6F
                progressView2.progress = 82F
                progressView3.progress = 8F
                progressView4.progress = 4F
            }
            3 -> {
                textView1.text = "2%"
                textView2.text = "10%"
                textView3.text = "84%"
                textView4.text = "4%"
                progressView1.progress = 2F
                progressView2.progress = 10F
                progressView3.progress = 84F
                progressView4.progress = 4F
            }
            4 -> {
                textView1.text = "6%"
                textView2.text = "12%"
                textView3.text = "4%"
                textView4.text = "78%"
                progressView1.progress = 6F
                progressView2.progress = 12F
                progressView3.progress = 4F
                progressView4.progress = 78F
            }
        }

        dialogAudience.setCancelable(false)
        dialogAudience.show()
        mediaPlayer.release()
        val mediaPlayer1 = MediaPlayer.create(context, R.raw.khan_gia)
        mediaPlayer1.start()
        mediaPlayer1.setOnCompletionListener {
            mediaPlayer1.release()
            startPlayerBackground()
        }

        buttonCancel.setOnClickListener {
            mediaPlayer1.release()
            mediaPlayer.release()
            startPlayerBackground()
            dialogAudience.cancel()
        }
    }

    private fun openDialogCall() {
        dialogCall = Dialog(requireContext())
        dialogCall.setContentView(R.layout.call_dialog)
        dialogCall.setCancelable(false)
        dialogCall.show()
        val buttonCancel: Button = dialogCall.findViewById(R.id.bt_cancel)
        val ronaldo: ImageView = dialogCall.findViewById(R.id.ronaldo)
        val messi: ImageView = dialogCall.findViewById(R.id.messi)
        val congvinh: ImageView = dialogCall.findViewById(R.id.congvinh)
        val suarez: ImageView = dialogCall.findViewById(R.id.suarez)
        buttonCancel.setOnClickListener(this)
        ronaldo.setOnClickListener(this)
        messi.setOnClickListener(this)
        congvinh.setOnClickListener(this)
        suarez.setOnClickListener(this)

        val mediaPlayer1 = MediaPlayer.create(context, R.raw.help_call)
        mediaPlayer1.start()
        mediaPlayer1.setOnCompletionListener {
            mediaPlayer1.release()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openDialogEnd(fail: Boolean) {
        val score: String
        var lv = 0
        dialogEnd = Dialog(requireContext())
        dialogEnd.setContentView(R.layout.end_dialog)
        val buttonHuy: Button = dialogEnd.findViewById(R.id.bt_huy)
        val buttonSave: Button = dialogEnd.findViewById(R.id.bt_save)
        val tvScore: TextView = dialogEnd.findViewById(R.id.tv_score)
        val name: EditText = dialogEnd.findViewById(R.id.edt_name)
        if (fail) {
            if (level < 5) {
                score = "0"
                tvScore.text = "0"
            } else if (level in 5..9) {
                lv = 5
                score = "2,000,000"
                tvScore.text = "2,000,000"
            } else {
                lv = 10
                score = "22,000,000"
                tvScore.text = "22,000,000"
            }
        } else {
            if (level < 5) {
                score = "0"
                tvScore.text = "0"
            } else if (level == 15) {
                lv = 15
                score = "150,000,000"
                tvScore.text = "150,000,000"
            } else {
                lv = level
                score = this.score
                tvScore.text = this.score
            }
        }
        dialogEnd.setCancelable(false)
        dialogEnd.show()
        buttonHuy.setOnClickListener {
            dialogEnd.cancel()
            (activity as MainActivity).openFirstFragment()
        }
        buttonSave.setOnClickListener {
            if (level < 5) {
                Toast.makeText(context,"Điểm của bạn quá thấp không thể lưu", Toast.LENGTH_SHORT).show()
            } else {
                dao.insertHighScore(name.text.toString(), score, lv)
                dialogEnd.cancel()
                Toast.makeText(context,"Lưu điểm thành công", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).openFirstFragment()
            }
        }
    }

    private fun getAnswer(): String {
        var answer = ""
        when (questions[level].trueCase) {
            1 -> {
                answer = "A"
            }
            2 -> {
                answer = "B"
            }
            3 -> {
                answer = "C"
            }
            4 -> {
                answer = "D"
            }
        }
        return answer
    }

    private fun setOffClick() {
        binding.caseA.isClickable = false
        binding.caseB.isClickable = false
        binding.caseC.isClickable = false
        binding.caseD.isClickable = false
        binding.ib5050.isClickable = false
        binding.ibCall.isClickable = false
        binding.ibAudience.isClickable = false
        binding.ibRefresh.isClickable = false
        binding.ibStop.isClickable = false
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.caseA -> {
                    asyn.cancel(true)
                    setOffClick()
                    chooseCase = 1
                    mediaPlayer.release()
                    startPlayer(R.raw.ans_a)
                    binding.caseA.setBackgroundResource(R.drawable.bg_choose1)
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                        val mediaPlayer1 = MediaPlayer.create(context, R.raw.ans_now1)
                        mediaPlayer1.start()
                        binding.caseA.setBackgroundResource(R.drawable.bg_choose2)
                        mediaPlayer1.setOnCompletionListener {
                            mediaPlayer1.release()
                            checkChooseCase(chooseCase, questions[level].trueCase)
                        }
                    }
                }
                R.id.caseB -> {
                    asyn.cancel(true)
                    setOffClick()
                    chooseCase = 2
                    mediaPlayer.release()
                    startPlayer(R.raw.ans_b2)
                    binding.caseB.setBackgroundResource(R.drawable.bg_choose1)
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                        val mediaPlayer1 = MediaPlayer.create(context, R.raw.ans_now2)
                        mediaPlayer1.start()
                        binding.caseB.setBackgroundResource(R.drawable.bg_choose2)
                        mediaPlayer1.setOnCompletionListener {
                            mediaPlayer1.release()
                            checkChooseCase(chooseCase, questions[level].trueCase)
                        }
                    }
                }
                R.id.caseC -> {
                    asyn.cancel(true)
                    setOffClick()
                    chooseCase = 3
                    mediaPlayer.release()
                    startPlayer(R.raw.ans_c)
                    binding.caseC.setBackgroundResource(R.drawable.bg_choose1)
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                        val mediaPlayer1 = MediaPlayer.create(context, R.raw.ans_now3)
                        mediaPlayer1.start()
                        binding.caseC.setBackgroundResource(R.drawable.bg_choose2)
                        mediaPlayer1.setOnCompletionListener {
                            mediaPlayer1.release()
                            checkChooseCase(chooseCase, questions[level].trueCase)
                        }
                    }
                }
                R.id.caseD -> {
                    asyn.cancel(true)
                    setOffClick()
                    chooseCase = 4
                    mediaPlayer.release()
                    startPlayer(R.raw.ans_d2)
                    binding.caseD.setBackgroundResource(R.drawable.bg_choose1)
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer.release()
                        val mediaPlayer1 = MediaPlayer.create(context, R.raw.ans_now1)
                        mediaPlayer1.start()
                        binding.caseD.setBackgroundResource(R.drawable.bg_choose2)
                        mediaPlayer1.setOnCompletionListener {
                            mediaPlayer1.release()
                            checkChooseCase(chooseCase, questions[level].trueCase)
                        }
                    }
                }
                R.id.ib_stop -> {
                    mediaPlayer.release()
                    openDialogEnd(false)
                }
                R.id.ib_refresh -> {
                    binding.caseA.isClickable = true
                    binding.caseB.isClickable = true
                    binding.caseC.isClickable = true
                    binding.caseD.isClickable = true
                    refresh = false
                    binding.ibRefresh.setImageResource(R.drawable.player_button_image_help_change_question_x)
                    queryQuestion()
                    updateQuestion()
                    binding.ibRefresh.isClickable = false
                }
                R.id.ib_5050 -> {
                    help5050 = false
                    binding.ib5050.setImageResource(R.drawable.player_button_image_help_5050_x)
                    binding.ib5050.isClickable = false
                    mediaPlayer.release()
                    val mediaPlayer1 = MediaPlayer.create(context, R.raw.sound5050)
                    mediaPlayer1.start()
                    mediaPlayer1.setOnCompletionListener {
                        mediaPlayer1.release()
                        startPlayerBackground()
                    }

                    Handler().postDelayed({
                        val case1: Int
                        val case2: Int
                        while (true) {
                            val randomNumber = (1..4).random()
                            if (randomNumber != questions[level].trueCase) {
                                case1 = randomNumber
                                break
                            }
                        }
                        while (true) {
                            val randomNumber = (1..4).random()
                            if (randomNumber != questions[level].trueCase && randomNumber != case1) {
                                case2 = randomNumber
                                break
                            }
                        }
                        when (case1) {
                            1 -> {
                                binding.caseA.text = null
                                binding.caseA.isClickable = false
                            }
                            2 -> {
                                binding.caseB.text = null
                                binding.caseB.isClickable = false
                            }
                            3 -> {
                                binding.caseC.text = null
                                binding.caseC.isClickable = false
                            }
                            4 -> {
                                binding.caseD.text = null
                                binding.caseD.isClickable = false
                            }
                        }
                        when (case2) {
                            1 -> {
                                binding.caseA.text = null
                                binding.caseA.isClickable = false
                            }
                            2 -> {
                                binding.caseB.text = null
                                binding.caseB.isClickable = false
                            }
                            3 -> {
                                binding.caseC.text = null
                                binding.caseC.isClickable = false
                            }
                            4 -> {
                                binding.caseD.text = null
                                binding.caseD.isClickable = false
                            }
                        }
                    }, 3000)
                }
                R.id.ib_audience -> {
                    audience = false
                    binding.ibAudience.setImageResource(R.drawable.player_button_image_help_audience_x)
                    binding.ibAudience.isClickable = false
                    openAudienceDialog()
                }
                R.id.ib_call -> {
                    openDialogCall()
                    call = false
                    binding.ibCall.setImageResource(R.drawable.player_button_image_help_call_x)
                    binding.ibCall.isClickable = false
                }
                R.id.bt_cancel -> {
                    dialogCall.cancel()
                }
                R.id.ronaldo -> {
                    val textView: TextView = dialogCall.findViewById(R.id.tv)
                    textView.text = "Câu trả lời của Ronaldo là ${getAnswer()}"
                }
                R.id.messi -> {
                    val textView: TextView = dialogCall.findViewById(R.id.tv)
                    textView.text = "Câu trả lời của Messi là ${getAnswer()}"
                }
                R.id.congvinh -> {
                    val textView: TextView = dialogCall.findViewById(R.id.tv)
                    textView.text = "Câu trả lời của Công Vinh là ${getAnswer()}"
                }
                R.id.suarez -> {
                    val textView: TextView = dialogCall.findViewById(R.id.tv)
                    textView.text = "Câu trả lời của Suarez là ${getAnswer()}"
                }
            }
        }
    }
}