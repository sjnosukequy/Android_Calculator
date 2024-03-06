package com.example.fullcalculator.Fragments;

import android.content.res.Configuration;
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
import java.math.BigInteger;
import java.math.RoundingMode;

public class Calc extends Fragment {
    private TextView Dis ;
    private TextView Res ;
    private TextView Sign ;
    private String Action;
    private BigDecimal Resfloat = new BigDecimal(0);;
    private BigDecimal Disfloat = new BigDecimal(0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calc, container, false);
        Dis = view.findViewById(R.id.solution);
        Res = view.findViewById(R.id.result);
        Sign = view.findViewById(R.id.sign);
        Action = "";
        if(savedInstanceState != null){
            Dis.setText(savedInstanceState.getString("Dis"));
            Res.setText(savedInstanceState.getString("Res"));
            Sign.setText(savedInstanceState.getString("Sign"));

            Action = savedInstanceState.getString("Sign");
            Resfloat = new BigDecimal(Res.getText().toString());
            Disfloat= new BigDecimal(Dis.getText().toString());
        }
        SetFunc(view);
        return view;
    }

    public  void SetFunc(View view){
        view.findViewById(R.id.Butt_C).setOnClickListener(this::DelButt);
        view.findViewById(R.id.Butt_Percent).setOnClickListener(this::ActionButt);
        view.findViewById(R.id.Butt_00).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_Dev).setOnClickListener(this::ActionButt);

        view.findViewById(R.id.Butt_7).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_8).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_9).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_Multiply).setOnClickListener(this::ActionButt);

        view.findViewById(R.id.Butt_4).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_5).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_6).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_Plus).setOnClickListener(this::ActionButt);

        view.findViewById(R.id.Butt_1).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_2).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_3).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_Minus).setOnClickListener(this::ActionButt);

        view.findViewById(R.id.Butt_AC).setOnClickListener(this::ClearButt);
        view.findViewById(R.id.Butt_0).setOnClickListener(this::numButt);
        view.findViewById(R.id.Butt_Power).setOnClickListener(this::ActionButt);
        view.findViewById(R.id.Butt_Equal).setOnClickListener(this::Equal);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view.findViewById(R.id.Butt_sin).setOnClickListener(this::ActionButt);
            view.findViewById(R.id.Butt_cos).setOnClickListener(this::ActionButt);
            view.findViewById(R.id.Butt_tan).setOnClickListener(this::ActionButt);
            view.findViewById(R.id.Butt_cot).setOnClickListener(this::ActionButt);
            view.findViewById(R.id.Butt_log).setOnClickListener(this::ActionButt);
        }
    }

    public void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putString("Dis", Dis.getText().toString());
        icicle.putString("Res", Res.getText().toString() );
        icicle.putString("Sign", Sign.getText().toString());
    }

    public String Round(String temp){
        if(temp.contains(".0"))
        {
            String[] temp2 = temp.split("\\.");
            return temp2[0];
        }
        return  temp;
    }

    public void numButt(View v) {
        Button b = (Button)  v;
        if(Disfloat.equals(new BigDecimal(0)))
            Disfloat = new BigDecimal( b.getText().toString() );
        else
        {
            String temp = Round(Dis.getText().toString() ) + b.getText().toString();
            if(temp.length() < 10)
                Disfloat = new BigDecimal(temp);
        }

        Dis.setText(Round( String.valueOf(Disfloat)) );
    }

    public void ActionButt(View v){
        if(Action.equals("")){
            Button b = (Button)  v;

            BigDecimal test = new BigDecimal(Dis.getText().toString());
            if(!test.equals(new BigDecimal(0)))
                Resfloat = test;
            Dis.setText("0");
            Disfloat= new BigDecimal(0);

            Sign.setText(b.getText().toString());
            Action = b.getText().toString();

            String temp =  Round( String.valueOf(Resfloat) );
            Res.setText(temp);
        }
    }

    public void ClearButt(View V){
        Action = "";
        Resfloat = new BigDecimal(0);
        Disfloat = new BigDecimal(0);
        Dis.setText("0");
        Res.setText("0");
        Sign.setText("");
    }

    public void DelButt(View v){
        String temp = Dis.getText().toString();
        if(temp.length() > 1)
        {
            String temp2 = temp.substring(0, temp.length() - 1);
            Disfloat = new BigDecimal(temp2);
            Dis.setText(temp2);
        }
        else {
            Disfloat = new BigDecimal(0);
            Dis.setText("0");
        }
    }

    public void SoftReset(){
        Action = "";
        Sign.setText("");
        Dis.setText("0");
        Disfloat = new BigDecimal(0);
    }

    public void Equal(View v){
        switch (Action){
            case "cot": {
                try{
                    Resfloat = BigDecimal.valueOf(1/ Math.tan(Disfloat.doubleValue() * Math.PI/180));
                }
                catch (Exception  e){
                    Resfloat = new BigDecimal(0);
                    Toast.makeText(getContext(),  "ILLEGAL",  Toast.LENGTH_LONG).show();
                }
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "log": {
                Resfloat = BigDecimal.valueOf( Math.log10(Disfloat.doubleValue()));
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "tan": {
                Resfloat = BigDecimal.valueOf( Math.tan(Disfloat.doubleValue() * Math.PI/180));
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "cos": {
                Resfloat = BigDecimal.valueOf( Math.cos(Disfloat.doubleValue() * Math.PI/180));
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "sin": {
                Resfloat = BigDecimal.valueOf( Math.sin(Disfloat.doubleValue() * Math.PI/180));
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "": {
                Resfloat = Disfloat;
                Res.setText(String.valueOf(Resfloat));
                SoftReset();
                break;
            }
            case "+": {
                BigDecimal ass = Resfloat.add(Disfloat);
                Resfloat = ass;
                Res.setText(String.valueOf(ass));
                SoftReset();
                break;
            }
            case "-": {
                BigDecimal ass = Resfloat.subtract(Disfloat);
                Resfloat = ass;
                Res.setText(String.valueOf(ass));
                SoftReset();
                break;
            }
            case "*": {
                BigDecimal ass = Resfloat.multiply(Disfloat);
                String txt = Round( String.valueOf(ass));
                Resfloat = new BigDecimal(txt);
                Res.setText(txt);
                SoftReset();
                break;
            }
            case "/": {
                BigDecimal ass = Resfloat.divide(Disfloat, 2, RoundingMode.CEILING);
                String txt = Round( String.valueOf(ass));
                Resfloat = new BigDecimal(txt);
                Res.setText(txt);
                SoftReset();
                break;
            }
            case "%": {
                BigDecimal ass = Resfloat.remainder(Disfloat);
                Resfloat = ass;
                Res.setText(String.valueOf(ass));
                SoftReset();
                break;
            }
            case "^": {
                BigInteger check = Disfloat.toBigInteger();
                if(check.bitLength() < 4) {
                    BigDecimal ass = Resfloat.pow(check.intValue());
                    Resfloat = ass;
                    Res.setText(String.valueOf(ass));
                    SoftReset();
                }
                else
                    Toast.makeText(getContext(),  "exponent is too large must be < 7",  Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

}