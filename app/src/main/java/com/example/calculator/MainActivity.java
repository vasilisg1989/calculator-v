package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txt_input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);

        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);


        }
    }

    public void btn_zero(View view) {
        updateText("0");

    }

    public void btn_one(View view) {
        updateText("1");

    }

    public void btn_2(View view) {
        updateText("2");

    }

    public void btn_three(View view) {
        updateText("3");

    }

    public void btn_four(View view) {
        updateText("4");

    }

    public void btn_five(View view) {
        updateText("5");

    }

    public void btn_six(View view) {
        updateText("6");

    }

    public void btn_seven(View view) {
        updateText("7");

    }

    public void btn_eight(View view) {
        updateText("8");

    }

    public void btn_nine(View view) {
        updateText("9");

    }

    public void btn_dots(View view) {
        updateText(".");

    }

    public void btn_equals(View view) {
        String userexp = display.getText().toString();
        userexp = userexp.replaceAll("÷", "/");
        userexp = userexp.replaceAll("×", "*");
        Expression exp = new Expression(userexp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void btn_plus(View view) {
        updateText("+");

    }

    public void btn_minus(View view) {
        updateText("-");

    }

    public void btn_multi(View view) {
        updateText("×");

    }

    public void btn_divide(View view) {
        updateText("÷");

    }

    public void btn_backspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }

    public void btn_percentage(View view) {
        updateText("%");

    }

    public void btn_plusminus(View view) {
        updateText("-");

    }

    public void btn_clear(View view) {
        display.setText("");

    }

    public void btn_parenthesis(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int texLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }
        if (openPar == closePar || display.getText().toString().substring(texLen - 1, texLen).equals("(")) {
            updateText("(");


        } else if (closePar < openPar && !display.getText().toString().substring(texLen - 1, texLen).equals(")")) {
            updateText(")");


        }
        display.setSelection(cursorPos + 1);
    }
}