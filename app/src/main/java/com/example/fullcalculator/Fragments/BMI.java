package com.example.fullcalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fullcalculator.R;

public class BMI extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b_m_i, container, false);
        EditText editTextWeight = view.findViewById(R.id.weight);
        EditText editTextHeight = view.findViewById(R.id.height);
        Button button = view.findViewById(R.id.btnSubmit);
        TextView textViewResult = view.findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float height = Float.parseFloat(String.valueOf(editTextHeight.getText()))/100;
                float weight = Float.parseFloat(String.valueOf(editTextWeight.getText()));
                float bmi = weight / (height * height);
                textViewResult.setText(String.valueOf(bmi));
            }
        });
        return view;
    }
}