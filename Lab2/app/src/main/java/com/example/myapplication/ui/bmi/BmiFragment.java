package com.example.myapplication.ui.bmi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentBmiBinding;

import java.text.NumberFormat;

public class BmiFragment extends Fragment {

    private FragmentBmiBinding binding;


    private static final NumberFormat floatFormat = NumberFormat.getNumberInstance();    // float formatter objects
    private TextView weightTextView; // shows formatted weight amount
    private TextView heightTextView; //  shows formatted height amount
    private Button calculateButton;
    private TextView bmiTextView; // shows calculated bmi


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BmiViewModel bmiViewModel =
                new ViewModelProvider(this).get(BmiViewModel.class);

        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bmiTextView = binding.textViewResult;
        weightTextView = binding.weight;
        heightTextView = binding.height;
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

            double heightBy100 = heightValue/100;

            //calculate the bmi and total masa/wzrost^2
            double bmiResult = weightValue/(heightBy100*heightBy100);

            // display bmi
            String resultToString = floatFormat.format(bmiResult).toString();
            bmiTextView.setText("Your BMI: "+resultToString);

        }
        catch (NumberFormatException e) { // if s is empty or non-numeric
            bmiTextView.setText("Your BMI: : incorrect data");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}