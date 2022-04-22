package com.example.myapplication.ui.recipe;

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

import com.example.myapplication.databinding.FragmentRecipeBinding;
import com.example.myapplication.ui.notifications.KcalViewModel;

public class RecipeFragment extends Fragment {

    private Button spaghettiButton;
    private Button chipsButton;
    private FragmentRecipeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        KcalViewModel kcalViewModel =
                new ViewModelProvider(this).get(KcalViewModel.class);

        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spaghettiButton = binding.spaghettiButton;
        chipsButton = binding.chipsbutton;

        spaghettiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetLayoutsVisible(binding.SpaghettiLayout,binding.ChipsLayout);
            }
        });

        chipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetLayoutsVisible(binding.ChipsLayout,binding.SpaghettiLayout);
            }
        });



        return root;
    }

    private void SetLayoutsVisible(RelativeLayout layoutVisible,RelativeLayout layoutInvisible )
    {
        layoutVisible.setVisibility(View.VISIBLE);
        layoutInvisible.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}