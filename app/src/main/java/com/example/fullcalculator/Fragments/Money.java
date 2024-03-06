package com.example.fullcalculator.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fullcalculator.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money extends Fragment {
    private TextView solutionTextView;
    private StringBuilder amountBuilder;

    private TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money, container, false);
        solutionTextView = view.findViewById(R.id.solution);
        resultTextView = view.findViewById(R.id.result);
        amountBuilder = new StringBuilder();
        function(view);
        return view;
    }

    private void function(View view){
        view.findViewById(R.id.Butt_My).setOnClickListener(this::ActionButt);
        view.findViewById(R.id.Butt_Nhat).setOnClickListener(this::ActionButt);
        view.findViewById(R.id.Butt_Anh).setOnClickListener(this::ActionButt);

        view.findViewById(R.id.Butt_7).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_8).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_9).setOnClickListener(this::numButt);

        view.findViewById(R.id.Butt_4).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_5).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_6).setOnClickListener(this::numButt);

        view.findViewById(R.id.Butt_1).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_2).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_3).setOnClickListener(this::numButt);

        view.findViewById(R.id.Butt_AC).setOnClickListener(this::ClearButt);
        view.findViewById(R.id.Butt_0).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_000).setOnClickListener(this::numButt);
    }

    // Method to handle number buttons
    public void numButt(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("000")) {
            amountBuilder.append("000");
        } else {
            amountBuilder.append(buttonText);
        }

        updateAmountDisplay();
    }

    // Method to handle currency buttons
    public void ActionButt(View view) {
        Button button = (Button) view;
        String currency = button.getText().toString();

        // Convert amount to desired currency and display
        if (!amountBuilder.toString().isEmpty()) {
            BigDecimal amount = new BigDecimal(amountBuilder.toString());
            convertCurrency(amount, currency);
        } else {
            Toast.makeText(getContext(), "Enter an amount first!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to clear the amount
    public void ClearButt(View view) {
        amountBuilder.setLength(0);
        updateAmountDisplay();
    }

    // Method to update the displayed amount
    private void updateAmountDisplay() {
        solutionTextView.setText(amountBuilder.toString() + " VND") ;
        resultTextView.setText( " 0" );
    }

    // Method to convert currency
    private void convertCurrency(BigDecimal amount, String currency) {
        BigDecimal conversionRate;

        switch (currency) {
            case "$": // Convert to USD
                conversionRate = new BigDecimal("0.000043");
                break;
            case "¥": // Convert to JPY
                conversionRate = new BigDecimal("0.0046");
                break;
            case "£": // Convert to GBP
                conversionRate = new BigDecimal("0.000031");
                break;
            default:
                conversionRate = BigDecimal.ONE;
        }

        // Perform conversion and round to 2 decimal places
        BigDecimal convertedAmount = amount.multiply(conversionRate).setScale(2, RoundingMode.HALF_UP);
        resultTextView.setText(convertedAmount.toString() + " " + currency);
    }
}