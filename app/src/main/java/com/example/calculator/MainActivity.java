package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    private TextView Dis ;
    private TextView Res ;
    private TextView Sign ;
    private String Action;
    private BigDecimal Resfloat = new BigDecimal(0);;
    private BigDecimal Disfloat = new BigDecimal(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dis = findViewById(R.id.solution);
        Res = findViewById(R.id.result);
        Sign = findViewById(R.id.sign);
        Action = "";
        if(savedInstanceState != null){
            Dis.setText(savedInstanceState.getString("Dis"));
            Res.setText(savedInstanceState.getString("Res"));
            Sign.setText(savedInstanceState.getString("Sign"));

            Action = savedInstanceState.getString("Sign");
            Resfloat = new BigDecimal(Res.getText().toString());
            Disfloat= new BigDecimal(Dis.getText().toString());
        }
    }

    protected void onSaveInstanceState(Bundle icicle) {
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
                    Toast.makeText(this,  "ILLEGAL",  Toast.LENGTH_LONG).show();
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
                    Toast.makeText(this,  "exponent is too large must be < 7",  Toast.LENGTH_LONG).show();
                break;
            }
        }
     }
}