package com.example.myapplication.ui.game;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;

    private com.example.myapplication.ui.game.CannonView cannonView; // custom view to display the game

    // called when Fragment's view needs to be created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // inflate the fragment_main.xml layout
        View view =
                inflater.inflate(R.layout.fragment_game, container, false);

        // get a reference to the CannonView
        cannonView = (com.example.myapplication.ui.game.CannonView) view.findViewById(R.id.cannonView);
        return view;
    }

    // set up volume control once Activity is created
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // allow volume buttons to set game volume
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    // when MainActivity is paused, terminate the game
    @Override
    public void onPause() {
        super.onPause();
        cannonView.stopGame(); // terminates the game
    }

    // when MainActivity is paused, MainActivityFragment releases resources
    @Override
    public void onDestroy() {
        super.onDestroy();
        cannonView.releaseResources();
    }
}