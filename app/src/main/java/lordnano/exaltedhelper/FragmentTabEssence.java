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

public class FragmentTabEssence extends Fragment {
    private Essence essenceMeter = new Essence();
    private View essenceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        essenceView = inflater.inflate(R.layout.fragment_layout_essence, container, false);

        Button setMaxEssence = (Button) essenceView.findViewById(R.id.buttonSetMaxEssence);
        setMaxEssence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeCurrentEssence();
            }
        });

        Button useEssenceMotes = (Button) essenceView.findViewById(R.id.buttonRemEssence);
        useEssenceMotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editMotes = (EditText) essenceView.findViewById(R.id.variableEssence);
                if(editMotes.getText().length()<1) editMotes.setText("0");

                int motes = Integer.parseInt(editMotes.getText().toString());
                essenceMeter.useEssence(motes);
                updateCurrentEssence();
            }
        });

        Button gainEssenceMotes = (Button) essenceView.findViewById(R.id.buttonAddEssence);
        gainEssenceMotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editMotes = (EditText) essenceView.findViewById(R.id.variableEssence);
                if(editMotes.getText().length()<1) editMotes.setText("0");

                int motes = Integer.parseInt(editMotes.getText().toString());
                essenceMeter.gainEssence(motes);
                updateCurrentEssence();
            }
        });

        Button remMote = (Button) essenceView.findViewById(R.id.btnRemMote);
        remMote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editMotes = (EditText) essenceView.findViewById(R.id.variableEssence);
                if(editMotes.getText().length()<1) editMotes.setText("0");

                int qty = Integer.parseInt(editMotes.getText().toString());
                qty--;
                if(qty<0) qty = 0;
                editMotes.setText(Integer.toString(qty));
            }
        });

        Button addMote = (Button) essenceView.findViewById(R.id.btnAddMote);
        addMote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editMotes = (EditText) essenceView.findViewById(R.id.variableEssence);
                if(editMotes.getText().length()<1) editMotes.setText("0");

                int qty = Integer.parseInt(editMotes.getText().toString());
                qty++;
                if(qty>essenceMeter.getTotalRemainingEssence()) qty = essenceMeter.getTotalRemainingEssence();
                editMotes.setText(Integer.toString(qty));
            }
        });

        essenceView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) initializeCurrentEssence();
            }
        });


        return essenceView;
    }

    public void initializeCurrentEssence(){
        EditText editText;
        ProgressBar progressBar;

        editText = (EditText) essenceView.findViewById(R.id.personalEssenceMax);
        if(editText.getText() == null) editText.setText("0");
        this.essenceMeter.setMaxPersonal(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) essenceView.findViewById(R.id.pheripheralEssenceMax);
        if(editText.getText() == null) editText.setText("0");
        this.essenceMeter.setMaxPheripheral(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) essenceView.findViewById(R.id.commitedEssence);
        if(editText.getText() == null) editText.setText("0");
        this.essenceMeter.setCommited(Integer.parseInt(editText.getText().toString()));

        this.essenceMeter.calculateMaxEssence();

        progressBar = (ProgressBar) essenceView.findViewById(R.id.currentPersonalEssenceProgress);
        progressBar.setMax(this.essenceMeter.getMaxPersonal());

        progressBar = (ProgressBar) essenceView.findViewById(R.id.currentPheripheralEssenceProgress);
        progressBar.setMax(this.essenceMeter.getMaxPheripheral());

        this.updateCurrentEssence();
    }

    public void updateCurrentEssence(){
        TextView textView;
        ProgressBar progressBar;

        textView = (TextView) essenceView.findViewById(R.id.currentPersonalEssenceText);
        textView.setText(Integer.toString(essenceMeter.getCurrentPersonal()));

        textView = (TextView) essenceView.findViewById(R.id.currentPeripheralEssenceText);
        textView.setText(Integer.toString(essenceMeter.getCurrentPheripheral()));

        progressBar = (ProgressBar) essenceView.findViewById(R.id.currentPersonalEssenceProgress);
        progressBar.setProgress(this.essenceMeter.getCurrentPersonal());

        progressBar = (ProgressBar) essenceView.findViewById(R.id.currentPheripheralEssenceProgress);
        progressBar.setProgress(this.essenceMeter.getCurrentPheripheral());

        textView = (TextView) essenceView.findViewById(R.id.animaEfectText);
        textView.setText(essenceMeter.getAnimaText());

    }
}