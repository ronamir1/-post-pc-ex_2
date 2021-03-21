package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView textOutput = findViewById(R.id.textViewCalculatorOutput);
    TextView buttonEquals = findViewById(R.id.buttonEquals);
    buttonEquals.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertEquals();
        textOutput.setText(calculator.output());
      }
    });
    TextView buttonPlus = findViewById(R.id.buttonPlus);
    buttonPlus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertPlus();
        textOutput.setText(calculator.output());
      }
    });
    TextView buttonMinus = findViewById(R.id.buttonMinus);
    buttonMinus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertMinus();
        textOutput.setText(calculator.output());
      }
    });
    TextView button0 = findViewById(R.id.button0);
    button0.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(0);
        textOutput.setText(calculator.output());
      }
    });
    TextView button1 = findViewById(R.id.button1);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(1);
        textOutput.setText(calculator.output());
      }
    });
    TextView button2 = findViewById(R.id.button2);
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(2);
        textOutput.setText(calculator.output());
      }
    });
    TextView button3 = findViewById(R.id.button3);
    button3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(3);
        textOutput.setText(calculator.output());
      }
    });
    TextView button4 = findViewById(R.id.button4);
    button4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(4);
        textOutput.setText(calculator.output());
      }
    });
    TextView button5 = findViewById(R.id.button5);
    button5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(5);
        textOutput.setText(calculator.output());
      }
    });
    TextView button6 = findViewById(R.id.button6);
    button6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(6);
        textOutput.setText(calculator.output());
      }
    });
    TextView button7 = findViewById(R.id.button7);
    button7.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(7);
        textOutput.setText(calculator.output());
      }
    });
    TextView button8 = findViewById(R.id.button8);
    button8.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(8);
        textOutput.setText(calculator.output());
      }
    });
    TextView button9 = findViewById(R.id.button9);
    button9.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.insertDigit(9);
        textOutput.setText(calculator.output());
      }
    });
    TextView buttonClear = findViewById(R.id.buttonClear);
    buttonClear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.clear();
        textOutput.setText(calculator.output());
      }
    });
    View buttonBack = findViewById(R.id.buttonBackSpace);
    buttonBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculator.deleteLast();
        textOutput.setText(calculator.output());
      }
    });
    textOutput.setText(calculator.output());
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    Serializable state = calculator.saveState();
    outState.putSerializable("current state", state);
    // todo: save calculator state into the bundle
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
    TextView textOutput = findViewById(R.id.textViewCalculatorOutput);
    if (savedInstanceState == null){
      calculator = new SimpleCalculatorImpl();
      String output = calculator.output();
      textOutput.setText(output);


    }
    Serializable prevState = savedInstanceState.getSerializable("current state");
    calculator.loadState(prevState);
    textOutput.setText(calculator.output());
  }
}