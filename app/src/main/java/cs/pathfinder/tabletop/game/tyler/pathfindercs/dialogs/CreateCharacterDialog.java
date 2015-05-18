package cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;

/**
 * Created by Tyler on 4/15/2015.
 */
public class CreateCharacterDialog
        extends DialogFragment
        implements DialogInterface.OnClickListener,
            AdapterView.OnItemSelectedListener,
            Button.OnClickListener {

    private CreateDialogCommunicator communicator;

    private EditText nameField;

    private CharacterInfo characterInfo;

    private String method;
    private Button rollAbilityBtn;

    private View myView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        myView = inflater.inflate(R.layout.create_character_dialog, null);

        characterInfo = new CharacterInfo();

        nameField = (EditText) myView.findViewById(R.id.create_dialog_name);
        rollAbilityBtn = (Button) myView.findViewById(R.id.create_dialog_ability_btn);

        setupSpinner(R.id.dialog_spinner_race, R.array.races);
        setupSpinner(R.id.dialog_spinner_class, R.array.classes);
        setupSpinner(R.id.dialog_spinner_roll_method, R.array.ability_gen);
        setupSpinner(R.id.dialog_spinner_gender, R.array.gender);

        rollAbilityBtn.setOnClickListener(this);

        builder.setView(myView)
                .setPositiveButton("Create", this)
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing
                    }
                });

        return builder.create();
    }

    public void setupSpinner(int spinnerResource, int arrayResource) {
        Spinner spinner = (Spinner) myView.findViewById(spinnerResource);
        ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(getActivity(), arrayResource, R.layout.spinner_custom);
        spinner.setAdapter(array);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        View myView = getView();

        characterInfo.setName(nameField.getText().toString());

        communicator.onCreateCharacter(characterInfo);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (CreateDialogCommunicator) activity;
    }

    public void onClick(View view) {
        if (method.equals("manual")) {
            // TODO...
            return; // Don't roll for stats normally...
        } else if (method.equals("standard")) {
            characterInfo.rollForStats("standard");
        } else if (method.equals("classic")) {
            characterInfo.rollForStats("classic");
        } else if (method.equals("heroic")) {
            characterInfo.rollForStats("heroic");
        }

        int[] stats = characterInfo.getAbilities();
        int[] statMods = characterInfo.getAbilityMods();

        for (int i = 0; i < 6; i++) {
            System.out.println("Stat " + i + ": " + stats[i] + "    Stat mod: " + statMods[i]);
        }

        setStats(stats, statMods);
    }

    private void setStats(int[] ability, int[] mods) {
        TextView stat = (TextView) myView.findViewById(R.id.create_dialog_str_val);
        stat.setText(Integer.toString(ability[0]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_str_mod);
        String text = Integer.toString(mods[0]);
        if (mods[0] >= 0) { text = "+" + text; }
        stat.setText(text);

        stat = (TextView) myView.findViewById(R.id.create_dialog_dex_val);
        stat.setText(Integer.toString(ability[1]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_dex_mod);
        text = Integer.toString(mods[1]);
        if (mods[1] >= 0) { text = "+" + text; }
        stat.setText(text);

        stat = (TextView) myView.findViewById(R.id.create_dialog_con_val);
        stat.setText(Integer.toString(ability[2]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_con_mod);
        text = Integer.toString(mods[2]);
        if (mods[2] >= 0) { text = "+" + text; }
        stat.setText(text);

        stat = (TextView) myView.findViewById(R.id.create_dialog_int_val);
        stat.setText(Integer.toString(ability[3]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_int_mod);
        text = Integer.toString(mods[3]);
        if (mods[3] >= 0) { text = "+" + text; }
        stat.setText(text);

        stat = (TextView) myView.findViewById(R.id.create_dialog_wis_val);
        stat.setText(Integer.toString(ability[4]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_wis_mod);
        text = Integer.toString(mods[4]);
        if (mods[4] >= 0) { text = "+" + text; }
        stat.setText(text);

        stat = (TextView) myView.findViewById(R.id.create_dialog_cha_val);
        stat.setText(Integer.toString(ability[5]));
        stat = (TextView) myView.findViewById(R.id.create_dialog_cha_mod);
        text = Integer.toString(mods[5]);
        if (mods[5] >= 0) { text = "+" + text; }
        stat.setText(text);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.dialog_spinner_race) {
            characterInfo.setRace(((TextView) view).getText().toString());
        } else if (parent == myView.findViewById(R.id.dialog_spinner_class)) {
            characterInfo.setClass(((TextView) view).getText().toString());
        } else if (parent == myView.findViewById(R.id.dialog_spinner_roll_method)) {
            method = ((TextView) view).getText().toString().toLowerCase();
            method = (method.split(" ", 2))[0]; // Extracts the first word.
            System.out.println("onItemSelected method: " + method);
        } else if (parent == myView.findViewById(R.id.dialog_spinner_gender)) {
            characterInfo.setGender(((TextView) view).getText().toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface CreateDialogCommunicator {
        void onCreateCharacter(CharacterInfo characterInfo);
    }
}
