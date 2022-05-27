package com.example.myapplication.ui.bmi


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
import com.example.myapplication.databinding.FragmentBmiBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class BmiFragment : Fragment() {
    private var binding: FragmentBmiBinding? = null
    private var weightTextView // shows formatted weight amount
            : TextView? = null
    private var heightTextView //  shows formatted height amount
            : TextView? = null
    private var calculateButton: Button? = null
    private var bmiTextView // shows calculated bmi
            : TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val bmiViewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        bmiTextView = binding!!.textViewResult
        weightTextView = binding!!.weight
        heightTextView = binding!!.height
        calculateButton = binding!!.calculatebmi
        calculateButton!!.setOnClickListener { calculate() }
        return root
    }

    private fun calculate() {

        //try catch for empty fields or incorrect data
        try {
            val weightValue = weightTextView!!.text.toString().toDouble()
            val heightValue = heightTextView!!.text.toString().toDouble()
            val heightBy100 = heightValue / 100

            //calculate the bmi and total masa/wzrost^2
            val bmiResult = weightValue / (heightBy100 * heightBy100)

            // display bmi
            val resultToString = floatFormat.format(bmiResult).toString()
            bmiTextView!!.text = "Your BMI: $resultToString"
        } catch (e: NumberFormatException) { // if s is empty or non-numeric
            bmiTextView!!.text = "Your BMI: : incorrect data"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val floatFormat = NumberFormat.getNumberInstance() // float formatter objects
    }
}