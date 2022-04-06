package com.example.dicegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class SettingsDialog extends DialogFragment {

    //apparently I'm not going for consistency in naming conventions.
    private View _dialogView;
    private Button _cancelButton;
    private Button _saveButton;
    private CheckBox _doublesCheckbox;
    private CheckBox _triplesCheckbox;
    private DiceGameOptions _diceOptions;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        _dialogView = inflater.inflate(R.layout.settings_dialog, null);
        builder.setView(_dialogView);
        MainActivity mainActivity = (MainActivity) getActivity();

        initUiComponents(Objects.requireNonNull(mainActivity));

        _cancelButton.setOnClickListener(view -> dismiss());
        _saveButton.setOnClickListener(view -> save(mainActivity));
        _triplesCheckbox.setOnClickListener(view -> _diceOptions.setTripleEnabled(_triplesCheckbox.isChecked()));
        _doublesCheckbox.setOnClickListener(view -> _diceOptions.setDoubleEnabled(_doublesCheckbox.isChecked()));

        return builder.create();
    }

    private void save(MainActivity mainActivity) {
        mainActivity.setDiceOptions(_diceOptions);
        dismiss();
    }

    private void initUiComponents(MainActivity mainActivity)
    {
        _cancelButton = _dialogView.findViewById(R.id.cancel_button);
        _saveButton = _dialogView.findViewById(R.id.save_button);
        _doublesCheckbox = _dialogView.findViewById(R.id.doubles_checkbox);
        _triplesCheckbox = _dialogView.findViewById(R.id.triples_checkbox);
        _diceOptions = mainActivity.getDiceOptions();
        _doublesCheckbox.setChecked(_diceOptions.getDoubleEnabled());
        _triplesCheckbox.setChecked(_diceOptions.getTripleEnabled());
    }
}
