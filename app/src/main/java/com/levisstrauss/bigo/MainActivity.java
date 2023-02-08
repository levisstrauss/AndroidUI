package com.levisstrauss.bigo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[][][] complexity =
            {
               {
                   {"O(log(n))", "O(log(n))", "O(log(n))"},
                   {"ϴ(log(n))", "ϴ(log(n))", "ϴ(log(n))"}
               },
               {
                   {"O(n)", "O(n)", "O(n)"},
                   {"ϴ(log(n))", "ϴ(log(n))", "ϴ(log(n))"}
               },
               {
                   {"O(n)", "O(n)", "O(n)"},
                   {"ϴ(1)", "ϴ(1)", "ϴ(1)"}
               },
               {
                   {"O(n)", "O(1)", "O(n)"},
                   {"ϴ(n)", "ϴ(1)", "ϴ(n)"}
               },
               {
                   {"O(1)", "O(log(n))", "O(n)"},
                   {"ϴ(1)", "ϴ(log(n))", "ϴ(n)"}
               }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The Spinner Configuration
        Spinner spinner = findViewById(R.id.dataStructures_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.data_structures, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void buttonClick (View view) {
        String message = "";

        // Get the email address
        EditText email = (EditText) findViewById(R.id.emailAddress);
        // Set the email AUTOFILL_HINT_EMAIL_ADDRESS
        email.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS);
        // Get the email subject
        EditText emailSubject = (EditText) findViewById(R.id.emailSubject);

        // Get the spinner value
        Spinner spinner = findViewById(R.id.dataStructures_spinner);
        int spinnerPosition = spinner.getSelectedItemPosition();

        // Get all the checkboxes
        CheckBox getMin = findViewById(R.id.getMin);
        CheckBox insert = findViewById(R.id.insert);
        CheckBox search = findViewById(R.id.search);

        // Get all the radio button
        RadioButton worstCase = findViewById(R.id.worst_case);
        RadioButton averageCase = findViewById(R.id.average_case);

        // Result text
        TextView sendTo = findViewById(R.id.sendTo);
        TextView textSubject = findViewById(R.id.textSubject);
        TextView resultContent = findViewById(R.id.content);

        // Set the result email textField
        sendTo.setText(email.getText().toString());

        // Set the result email subject
        textSubject.setText(emailSubject.getText().toString());

        int selectedCase = 0;
        if (worstCase.isChecked()){
            message = "Worst Case";
        }
        if (averageCase.isChecked()){
            message = "Average Case";
            selectedCase = 1;
        }
        message = message + " Time Complexity for " + spinner.getSelectedItem().toString() + ":\n";
        if (getMin.isChecked()){
            message = message + "    Get Minimum: " + complexity[spinnerPosition][selectedCase][0] + "\n";
        }
        if (insert.isChecked()){
            if (spinnerPosition == 2) {
                message = message + "    Insert (at the beginning): " + complexity[spinnerPosition][selectedCase][1] + "\n";
            }
            else{
                message = message + "    Insert: " + complexity[spinnerPosition][selectedCase][1] + "\n";
            }
        }
        if(search.isChecked()){
            message = message + "    Search: " + complexity[spinnerPosition][selectedCase][2] + "\n";
        }
        // Set the result of the content field
        resultContent.setText(message);
    }

    // The override methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
