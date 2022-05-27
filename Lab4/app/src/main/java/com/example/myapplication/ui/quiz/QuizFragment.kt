package com.example.myapplication.ui.quiz

import androidx.lifecycle.ViewModelProvider.get
import androidx.navigation.ui.AppBarConfiguration.Builder.build
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import android.widget.TextView
import android.os.Bundle
import com.example.myapplication.ui.bmi.BmiViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.bmi.BmiFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.myapplication.ui.game.CannonView
import com.example.myapplication.ui.game.GameElement
import com.example.myapplication.ui.game.Cannonball
import com.example.myapplication.ui.game.CannonView.CannonThread
import android.app.Activity
import com.example.myapplication.ui.game.Cannon
import android.media.SoundPool
import android.util.SparseIntArray
import androidx.annotation.RequiresApi
import android.os.Build
import com.example.myapplication.R
import android.content.DialogInterface
import android.media.AudioAttributes
import android.graphics.drawable.VectorDrawable
import android.graphics.drawable.Drawable
import android.media.AudioManager
import com.example.myapplication.ui.home.HomeViewModel
import android.widget.RadioButton
import com.example.myapplication.ui.notifications.KcalFragment
import com.example.myapplication.ui.quiz.QuizViewModel
import com.example.myapplication.ui.notifications.KcalViewModel
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.NavController
import android.content.Intent
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentQuizBinding
import java.text.NumberFormat

class QuizFragment constructor() : Fragment() {
    private var binding: FragmentQuizBinding? = null
    private var questionText // shows calculated bmi
            : TextView? = null
    private var nextQuestionButton: Button? = null
    private var currentQuestion: Int = 0
    private var score: Int = 0
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val kcalViewModel: QuizViewModel = ViewModelProvider(this).get(
            QuizViewModel::class.java
        )
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        questionText = binding!!.questionContent
        nextQuestionButton = binding!!.nextQuestion
        setNextQuestion()
        nextQuestionButton!!.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                if (currentQuestion == 6) {
                    resetQuiz()
                    setNextQuestion()
                } else {
                    checkAnswer()
                    currentQuestion++
                    setNextQuestion()
                }
            }
        })
        val root: View = binding!!.getRoot()
        return root
    }

    fun checkAnswer() {
        val trueValue: Boolean = binding!!.trueValue.isChecked()
        val falseValue: Boolean = binding!!.falseValue.isChecked()
        var textId: Int = 0
        var answer: Boolean = false
        if (currentQuestion > 5) return
        when (currentQuestion) {
            0 -> textId = R.string.answer1
            1 -> textId = R.string.answer2
            2 -> textId = R.string.answer3
            3 -> textId = R.string.answer4
            4 -> textId = R.string.answer5
            5 -> textId = R.string.answer6
        }
        val answerValue: String = getResources().getString(textId)
        answer = java.lang.Boolean.parseBoolean(answerValue)
        if (answer && trueValue) {
            score++
        } else if (!answer && falseValue) {
            score++
        }
    }

    fun setNextQuestion() {
        var textId: Int = 0
        var targetText: String? = ""
        when (currentQuestion) {
            0 -> textId = R.string.question1
            1 -> textId = R.string.question2
            2 -> textId = R.string.question3
            3 -> textId = R.string.question4
            4 -> textId = R.string.question5
            5 -> textId = R.string.question6
            6 -> textId = R.string.finalScore
        }
        targetText = getResources().getString(textId)
        if (currentQuestion == 6) {
            targetText += " " + score
            nextQuestionButton!!.setText("Try Again")
        }
        questionText!!.setText(targetText)
    }

    fun resetQuiz() {
        score = 0
        currentQuestion = 0
        nextQuestionButton!!.setText("Confirm")
    }

    public override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val intFormat: NumberFormat =
            NumberFormat.getNumberInstance() // float formatter objects
    }
}