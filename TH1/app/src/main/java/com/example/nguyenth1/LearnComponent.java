package com.example.nguyenth1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class LearnComponent extends AppCompatActivity {

    private CheckBox checkBox;
    private RadioGroup radioGroup;

    private ImageButton btnPickDate;
    private EditText edtDate;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_component);
        init();
    }

    private void init(){
        checkBox = findViewById(R.id.checkBox);
        handleCheckbox();

        radioGroup = findViewById(R.id.radioGroup);
        handleRadioGroup();

        btnPickDate = findViewById(R.id.btnPickDate);
        edtDate = findViewById(R.id.etDate);

        handlePickDate();

        spinner = findViewById(R.id.spinner);
        handleSpinner();
    }

    private void handleSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tech));
        spinner.setAdapter(adapter);
    }

    private void handleCheckbox(){
        checkBox.setOnClickListener(v -> {
            if(checkBox.isChecked()){
                // Toast
                Toast.makeText(this, "Checkbox cheked", Toast.LENGTH_SHORT).show();
            }else{
                // do something
                Toast.makeText(this, "Checkbox uncheked", Toast.LENGTH_SHORT).show();
            }
            System.out.println("Checkbox: " + checkBox.isChecked());
        });

    }

    private void handleRadioGroup(){
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.radioButtonBoy:
                    Toast.makeText(this, "Boy click", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButtonGirl:
                    Toast.makeText(this, "Girl click", Toast.LENGTH_SHORT).show();
                    break;

            }
        });
    }

    private void handlePickDate(){
       // handle pick date
        btnPickDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(LearnComponent.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                    edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, year, month, day);

            dialog.show();
        });
    }
}