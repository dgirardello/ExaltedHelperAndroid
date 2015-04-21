package lordnano.exaltedhelper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentTabDice extends Fragment {
    private DiceRoller diceRoller = new DiceRoller();
    private View diceView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        diceView = inflater.inflate(R.layout.fragment_layout_dice, container, false);


        Button btnRemDice = (Button) diceView.findViewById(R.id.btnRemDice);
        btnRemDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remOneDice();
            }
        });

        Button btnAddDice = (Button) diceView.findViewById(R.id.btnAddDice);
        btnAddDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneDice();
            }
        });

        Button btnRoll = (Button) diceView.findViewById(R.id.btnRollDice);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDices();
            }
        });

        Button btnClear = (Button) diceView.findViewById(R.id.btnClearResults);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearResults();
            }
        });





        return diceView;
    }

    private void addOneDice(){
        EditText edTx = (EditText) diceView.findViewById(R.id.diceCount);
        if(edTx.getText().length()<1) edTx.setText("0");

        int qty = Integer.parseInt(edTx.getText().toString());
        qty++;
        if (qty>999) qty=999;
        edTx.setText(Integer.toString(qty));
    }

    private void remOneDice(){
        EditText edTx = (EditText) this.diceView.findViewById(R.id.diceCount);
        if(edTx.getText().length()<1) edTx.setText("0");

        int qty = Integer.parseInt(edTx.getText().toString());
        qty--;
        if (qty<0) qty=0;
        edTx.setText(Integer.toString(qty));
    }

    private void setResults(int[] results){
        TextView txtView;

        txtView = (TextView) this.diceView.findViewById(R.id.resultText0);
        txtView.setText(Integer.toString(results[0]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText9);
        txtView.setText(Integer.toString(results[9]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText8);
        txtView.setText(Integer.toString(results[8]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText7);
        txtView.setText(Integer.toString(results[7]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText6);
        txtView.setText(Integer.toString(results[6]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText5);
        txtView.setText(Integer.toString(results[5]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText4);
        txtView.setText(Integer.toString(results[4]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText3);
        txtView.setText(Integer.toString(results[3]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText2);
        txtView.setText(Integer.toString(results[2]));

        txtView = (TextView) this.diceView.findViewById(R.id.resultText1);
        txtView.setText(Integer.toString(results[1]));
    }

    private void clearResults(){
        int[] myResults = {0,0,0,0,0,0,0,0,0,0};
        setResults(myResults);
    }

    public void rollDices(){
        EditText edTx = (EditText) this.diceView.findViewById(R.id.diceCount);
        if(edTx.getText().length()<1) edTx.setText("0");
        int qty = Integer.parseInt(edTx.getText().toString());

        int[] myResults = diceRoller.rollDice(qty);
        setResults(myResults);
    }

}