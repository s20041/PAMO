package com.example.myapplication.ui.notifications

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
import com.example.myapplication.databinding.FragmentKcalBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class KcalFragment constructor() : Fragment() {
    private var binding: FragmentKcalBinding? = null
    private var weightTextView // shows formatted weight amount
            : TextView? = null
    private var heightTextView //  shows formatted height amount
            : TextView? = null
    private var agetTextView //  shows formatted height amount
            : TextView? = null
    private var calculateButton: Button? = null
    private var kcalTextView // shows calculated bmi
            : TextView? = null

    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val bmiViewModel: BmiViewModel = ViewModelProvider(this).get(BmiViewModel::class.java)
        binding = FragmentKcalBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        kcalTextView = binding!!.textViewResult
        weightTextView = binding!!.weight
        heightTextView = binding!!.height
        agetTextView = binding!!.age
        calculateButton = binding!!.calculatebmi
        calculateButton!!.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(view: View) {
                calculate()
            }
        })
        return root
    }

    private fun calculate() {

        //try catch for empty fields or incorrect data
        try {
            val weightValue: Double = weightTextView!!.getText().toString().toDouble()
            val heightValue: Double = heightTextView!!.getText().toString().toDouble()
            val ageValue: Double = agetTextView!!.getText().toString().toDouble()
            val male: Boolean = binding!!.male.isChecked()
            var kcalResult: Double = 0.0
            if (male) {
                kcalResult =
                    66.5 + (13.75 * weightValue) + (5.003 * heightValue) - (6.775 * ageValue)
            } else {
                kcalResult =
                    655.1 + (9.563 * weightValue) + (1.85 * heightValue) - (4.676 * ageValue)
            }

            //man  =   66,5 + (13,75 x masa cia??a [kg]) + (5,003 x wzrost [cm]) ??? (6,775 x [wiek])
            //women =  655,1 + (9,563 x masa cia??a [kg]) + (1,85 x wzrost [cm]) ??? (4,676 x [wiek])


            // display kcal
            val resultToString: String = floatFormat.format(kcalResult).toString()
            kcalTextView!!.setText("Your Daily Kcal: " + resultToString)
        } catch (e: NumberFormatException) { // if s is empty or non-numeric
            kcalTextView!!.setText("Your Daily Kcal: incorrect data")
        }
    }

    public override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val floatFormat: NumberFormat =
            NumberFormat.getNumberInstance() // float formatter objects
    }
}