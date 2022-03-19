//Bartosz Bizoń s20041 BMI calculator


package com.example.tipper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing the tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text
import java.text.NumberFormat; // for currency formatting


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // integer formatter objects
    private static final NumberFormat numberFormat =
            NumberFormat.getIntegerInstance();

    // float formatter objects
    private static final NumberFormat floatFormat =
            NumberFormat.getNumberInstance();

    private double bmi = 0.0; // bill amount entered by the user
    private double weightAmount = 0.0; // weight amount entered by the user
    private double heightAmount = 0.0; // height amount entered by the user

    private TextView weightTextView; // shows formatted weight amount
    private TextView heightTextView; //  shows formatted height amount
    private TextView bmiTextView; // shows calculated bmi
    private Button calculateButton;


    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        bmiTextView = (TextView) findViewById(R.id.bmiTextView);
        calculateButton = (Button)findViewById(R.id.button);


        // set weightEditText's TextWatcher
        EditText weightEditText =
                (EditText) findViewById(R.id.editWeight);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        // set heightEditText's TextWatcher
        EditText heightEditText =
                (EditText) findViewById(R.id.editHeight);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    // calculate and display bmi and total amounts
    private void calculate() {

        double heightBy100 = heightAmount/100;
        // calculate the bmi and total masa/wzrost^2
        double total = weightAmount/(heightBy100*heightBy100);

        // display bmi
        bmiTextView.setText(floatFormat.format(total));

    }

    // listener object for the EditText's text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the weight amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get weight amount and display weight value
                weightAmount = Double.parseDouble(s.toString());
                weightTextView.setText(floatFormat.format(weightAmount));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                weightTextView.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };


    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        // called when the user modifies the height amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get height amount and display height value
                heightAmount = Double.parseDouble(s.toString());
                heightTextView.setText(floatFormat.format(heightAmount));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                heightTextView.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };


    @Override
    public void onClick(View view) {

    }
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
