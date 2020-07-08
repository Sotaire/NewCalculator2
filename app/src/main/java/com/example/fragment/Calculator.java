package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity implements ICalculatorFragment{
    String savedText;
    private ArrayList<Integer> id = new ArrayList<>();
    public static final String RETURN_RESULT = "return_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getData();
    }
    public void getData () {
        Intent intent = getIntent();
        if (intent!=null){
            String text = intent.getStringExtra(MainActivity.RESULT_KEY);
            CalculatorFragment.savedText = text;
            savedText = text;
        }

    }



    public void onClick_buttons(View view) {
        if (CalculatorFragment.isOperation) {
            CalculatorFragment.panel.setText("");
            CalculatorFragment.result = 0;
            CalculatorFragment.operation = null;
            CalculatorFragment.secondNumber = 0;
            CalculatorFragment.firstNumber = 0;
            CalculatorFragment.intermediateNumber = "";
            CalculatorFragment.isOperation = false;
        }
        switch (view.getId()) {
            case R.id.button0:
                CalculatorFragment.intermediateNumber += "0";
                break;
            case R.id.button1:
                CalculatorFragment.intermediateNumber += "1";
                break;

            case R.id.button2:
                CalculatorFragment.intermediateNumber += "2";
                break;

            case R.id.button3:
                CalculatorFragment.intermediateNumber += "3";
                break;
            case R.id.Button4:
                CalculatorFragment.intermediateNumber += "4";
                break;

            case R.id.button5:
                CalculatorFragment.intermediateNumber += "5";
                break;

            case R.id.button6:
                CalculatorFragment.intermediateNumber += "6";
                break;

            case R.id.button7:
                CalculatorFragment.intermediateNumber += "7";
                break;

            case R.id.button8:
                CalculatorFragment.intermediateNumber += "8";
                break;

            case R.id.button9:
                CalculatorFragment.intermediateNumber += "9";
                break;

            case R.id.comma:
                CalculatorFragment.intermediateNumber += ".";
                break;
        }
        CalculatorFragment.panel.setText(CalculatorFragment.intermediateNumber);
    }
    public void onClick_buttonLogic(View view) {
        switch (view.getId()) {
            case R.id.plus:
                CalculatorFragment.firstNumber = Double.parseDouble(CalculatorFragment.intermediateNumber);
                CalculatorFragment.operation = "+";
                CalculatorFragment.intermediateNumber = "";
                CalculatorFragment.panel.setText("+");
                break;
            case R.id.minus:
                CalculatorFragment.firstNumber = Double.parseDouble(CalculatorFragment.intermediateNumber);
                CalculatorFragment.operation = "-";
                CalculatorFragment.intermediateNumber = "";
                CalculatorFragment.panel.setText("-");
                break;
            case R.id.multiply:
                CalculatorFragment.firstNumber = Double.parseDouble(CalculatorFragment.intermediateNumber);
                CalculatorFragment.operation = "*";
                CalculatorFragment.intermediateNumber = "";
                CalculatorFragment.panel.setText("*");
                break;
            case R.id.division:
                CalculatorFragment.firstNumber = Double.parseDouble(CalculatorFragment.intermediateNumber);
                CalculatorFragment.operation = "/";
                CalculatorFragment.intermediateNumber = "";
                CalculatorFragment.panel.setText("/");
                break;
            case R.id.equal:
                CalculatorFragment.secondNumber = Double.parseDouble(CalculatorFragment.intermediateNumber);

                switch (CalculatorFragment.operation) {
                    case "+":
                        CalculatorFragment.result = CalculatorFragment.firstNumber + CalculatorFragment.secondNumber;
                        break;
                    case "-":
                        CalculatorFragment.result = CalculatorFragment.firstNumber - CalculatorFragment.secondNumber;
                        break;
                    case "*":
                        CalculatorFragment.result = CalculatorFragment.firstNumber * CalculatorFragment.secondNumber;
                        break;
                    case "/":
                        CalculatorFragment.result = CalculatorFragment.firstNumber / CalculatorFragment.secondNumber;
                        break;
                }
                CalculatorFragment.panel.setText(CalculatorFragment.firstNumber + " " +CalculatorFragment.operation + " " + CalculatorFragment.secondNumber + "=" +  CalculatorFragment.result + "");
                CalculatorFragment.isOperation = true;
                break;
        }
    }

    @Override
    public void onSendResult() {
        String newText = CalculatorFragment.savedText;
        Intent intent = new Intent();
        intent.putExtra(RETURN_RESULT,newText +CalculatorFragment.panel.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}