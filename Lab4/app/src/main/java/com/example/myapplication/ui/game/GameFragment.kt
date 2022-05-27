package com.example.myapplication.ui.game


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
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentGameBinding

class GameFragment constructor() : Fragment() {
    private val binding: FragmentGameBinding? = null
    private var cannonView // custom view to display the game
            : CannonView? = null

    // called when Fragment's view needs to be created
    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // inflate the fragment_main.xml layout
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)

        // get a reference to the CannonView
        cannonView = view.findViewById<View>(R.id.cannonView) as CannonView?
        return view
    }

    // set up volume control once Activity is created
    public override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // allow volume buttons to set game volume
        getActivity()!!.setVolumeControlStream(AudioManager.STREAM_MUSIC)
    }

    // when MainActivity is paused, terminate the game
    public override fun onPause() {
        super.onPause()
        cannonView!!.stopGame() // terminates the game
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    public override fun onDestroy() {
        super.onDestroy()
        cannonView!!.releaseResources()
    }
}