package com.example.myapplication.ui.home

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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment constructor() : Fragment() {
    private var binding: FragmentHomeBinding? = null
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val homeViewModel: HomeViewModel = ViewModelProvider(this).get(
            HomeViewModel::class.java
        )
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        val textView: TextView = binding!!.textHome
        homeViewModel.getText()
            .observe(getViewLifecycleOwner(), Observer({ text: String? -> textView.setText(text) }))
        return root
    }

    public override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}