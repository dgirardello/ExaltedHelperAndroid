package lordnano.exaltedhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentTabEssence extends Fragment {
    private Essence essenceMeter = new Essence();
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_layout_essence, container, false);

        View.OnKeyListener textEditIntro = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    initializeCurrentEssence();
                    return true;
                }
                return false;
            }
        };

        View.OnFocusChangeListener textEditChangeFocus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) initializeCurrentEssence();
            }
        };



        EditText editMaxPersonalEssence = (EditText) v.findViewById(R.id.personalEssenceMax);
        editMaxPersonalEssence.setOnKeyListener(textEditIntro);
        editMaxPersonalEssence.setOnFocusChangeListener(textEditChangeFocus);

        EditText editMaxPheripheralEssence = (EditText) v.findViewById(R.id.pheripheralEssenceMax);
        editMaxPheripheralEssence.setOnKeyListener(textEditIntro);
        editMaxPheripheralEssence.setOnFocusChangeListener(textEditChangeFocus);

        EditText editCommitedEssence = (EditText) v.findViewById(R.id.commitedEssence);
        editCommitedEssence.setOnKeyListener(textEditIntro);
        editCommitedEssence.setOnFocusChangeListener(textEditChangeFocus);


        return v;
    }

    public void initializeCurrentEssence(){
        EditText editText;
        ProgressBar progressBar;

        editText = (EditText) v.findViewById(R.id.personalEssenceMax);
        this.essenceMeter.setMaxPersonal(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) v.findViewById(R.id.pheripheralEssenceMax);
        this.essenceMeter.setMaxPheripheral(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) v.findViewById(R.id.commitedEssence);
        this.essenceMeter.setCommited(Integer.parseInt(editText.getText().toString()));

        this.essenceMeter.calculateMaxEssence();

        this.updateCurrentEssence();

        progressBar = (ProgressBar) v.findViewById(R.id.personalEssenceProgress);
        progressBar.setMax(this.essenceMeter.getMaxPersonal());
        progressBar.setProgress(this.essenceMeter.getCurrentPersonal());

        progressBar = (ProgressBar) v.findViewById(R.id.pheripheralEssenceProgress);
        progressBar.setMax(this.essenceMeter.getMaxPheripheral());
        progressBar.setProgress(this.essenceMeter.getCurrentPheripheral());
    }

    public void updateCurrentEssence(){
        ProgressBar


    }
}