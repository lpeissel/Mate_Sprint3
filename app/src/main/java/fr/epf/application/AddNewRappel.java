package fr.epf.application;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import fr.epf.application.Utils.DatabaseHandler2;
import fr.epf.application.models.ToDoModel2;

public class AddNewRappel extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    private EditText newRappelText;
    private EditText newRappelTitre;
    private Button newRappelSaveButton;

    private DatabaseHandler2 db;

    public static AddNewRappel newInstance(){
        return new AddNewRappel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_rappel, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newRappelTitre = Objects.requireNonNull(getView()).findViewById(R.id.newRappelTitre);
        newRappelText = Objects.requireNonNull(getView()).findViewById(R.id.newRappelText);
        newRappelSaveButton = getView().findViewById(R.id.newRappelButton);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String rappelTitre = bundle.getString("rappelTitre");
            String rappelText = bundle.getString("rappelText");
            newRappelTitre.setText(rappelTitre);
            assert rappelTitre != null;
            assert rappelText != null;
            if(rappelText.length()>0)
                newRappelSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.purple_500));
            if(rappelTitre.length()>0)
                newRappelSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.purple_500));
        }

        db = new DatabaseHandler2(getActivity());
        db.openDatabase();

        newRappelText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    newRappelSaveButton.setEnabled(false);
                    newRappelSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newRappelSaveButton.setEnabled(true);
                    newRappelSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.purple_500));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        newRappelTitre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    newRappelSaveButton.setEnabled(false);
                    newRappelSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newRappelSaveButton.setEnabled(true);
                    newRappelSaveButton.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.purple_500));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        newRappelSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text2 = newRappelTitre.getText().toString();
                String text = newRappelText.getText().toString();
                Log.d("caca", String.valueOf(newRappelTitre));
                if(finalIsUpdate){
                    db.updateRappel(bundle.getInt("id"), text2, text);
                }
                else {
                    ToDoModel2 rappel = new ToDoModel2();
                    rappel.setRappelTitre(text2);
                    rappel.setRappelText(text);
                    rappel.setStatus(0);
                    db.insertRappel(rappel);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }
}
