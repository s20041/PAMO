package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentBmiBinding;
import com.example.myapplication.databinding.FragmentKcalBinding;
import com.example.myapplication.ui.bmi.BmiViewModel;

import java.text.NumberFormat;

public class KcalFragment extends Fragment {

    private FragmentKcalBinding binding;

    private static final NumberFormat floatFormat = NumberFormat.getNumberInstance();    // float formatter objects
    private TextView weightTextView; // shows formatted weight amount
    private TextView heightTextView; //  shows formatted height amount
    private TextView agetTextView; //  shows formatted height amount
    private Button calculateButton;
    private TextView kcalTextView; // shows calculated bmi


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BmiViewModel bmiViewModel =
                new ViewModelProvider(this).get(BmiViewModel.class);

        binding = FragmentKcalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        kcalTextView = binding.textViewResult;
        weightTextView = binding.weight;
        heightTextView = binding.height;
        agetTextView = binding.age;
        calculateButton = binding.calculatebmi;

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        return root;
    }

    private void calculate() {

        //try catch for empty fields or incorrect data
        try {
            double weightValue = Double.parseDouble(weightTextView.getText().toString());
            double heightValue = Double.parseDouble(heightTextView.getText().toString());
            double ageValue = Double.parseDouble(agetTextView.getText().toString());
            boolean male = ((RadioButton)binding.male).isChecked();

            double kcalResult = 0;

            if(male)
            {
                kcalResult= 66.5 +(13.75 * weightValue) + (5.003*heightValue) - (6.775 * ageValue);
            }
            else
            {
                kcalResult = 655.1 +(9.563 * weightValue) + (1.85*heightValue) - (4.676 * ageValue);
            }

            //man  =   66,5 + (13,75 x masa ciała [kg]) + (5,003 x wzrost [cm]) – (6,775 x [wiek])
            //women =  655,1 + (9,563 x masa ciała [kg]) + (1,85 x wzrost [cm]) – (4,676 x [wiek])


            // display kcal
            String resultToString = floatFormat.format(kcalResult).toString();
            kcalTextView.setText("Your Daily Kcal: "+resultToString);
        }
        catch (NumberFormatException e) { // if s is empty or non-numeric
            kcalTextView.setText("Your Daily Kcal: incorrect data");
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}