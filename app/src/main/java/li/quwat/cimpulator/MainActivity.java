package li.quwat.cimpulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText resultField;
    private EditText newNumber;
    private TextView displayOperation;

    // variables to hold the operands:

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultField = (EditText)findViewById(R.id.editText3);
        newNumber = (EditText)findViewById(R.id.editText4);
        displayOperation = (TextView)findViewById(R.id.textView3);
        displayOperation.setText("");


        //Number buttons
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button buttonDot = (Button)findViewById(R.id.buttonDot);

        //Operation buttons
        Button sum = (Button)findViewById(R.id.sum);
        Button divide = (Button)findViewById(R.id.divide);
        Button sub = (Button)findViewById(R.id.sub);
        Button multiply = (Button)findViewById(R.id.multiply);
        Button result = (Button)findViewById(R.id.result);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                switch (b.getText().toString()) {

                    case "zero":
                        newNumber.append(String.valueOf(0));
                        break;
                    case "one":
                        newNumber.append(String.valueOf(1));
                        break;
                    case "two":
                        newNumber.append(String.valueOf(2));
                        break;
                    case "three":
                        newNumber.append(String.valueOf(3));
                        break;
                    case "four":
                        newNumber.append(String.valueOf(4));
                        break;
                    case "five":
                        newNumber.append(String.valueOf(5));
                        break;
                    case "six":
                        newNumber.append(String.valueOf(6));
                        break;
                    case "seven":
                        newNumber.append(String.valueOf(7));
                        break;
                    case "eight":
                        newNumber.append(String.valueOf(8));
                        break;
                    case "nine":
                        newNumber.append(String.valueOf(9));
                        break;
                    case "dot":
                        newNumber.append(".");
                        break;
                    default:
                        break;
                }
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        //Had this been a desktop-size keyboard or input method, best practice is to declare an array of buttons and loop through it


        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String operation = "";
                switch (b.getText().toString()) {
                    case "plus":
                        operation = "+";
                        break;
                    case "divby":
                        operation = "/";
                        break;
                    case "minus":
                        operation = "-";
                        break;
                    case "times":
                        operation = "*";
                        break;
                    case "reslt":
                        operation = "=";
                        break;
                    default:
                        break;
                }
                String value = newNumber.getText().toString();

                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, operation);

                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = operation;
                displayOperation.setText(pendingOperation);
            }
        };

        sub.setOnClickListener(opListener);
        sum.setOnClickListener(opListener);
        divide.setOnClickListener(opListener);
        multiply.setOnClickListener(opListener);
        result.setOnClickListener(opListener);

    }

    private void performOperation (Double value, String operation) {
        if (null == operand1) {
            operand1 = value;
        } else {
            operand2 = value;

            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }

            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1/=operand2;
                    }
                    break;
                case "*":
                    operand1*=operand2;
                    break;
                case "-":
                    operand1-=operand2;
                    break;
                case "+":
                    operand1+=operand2;
                    break;

            }
        }

        resultField.setText(operand1.toString());
        newNumber.setText("");


    }
}
