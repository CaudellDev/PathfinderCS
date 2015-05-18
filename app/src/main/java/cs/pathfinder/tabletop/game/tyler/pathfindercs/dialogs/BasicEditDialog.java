package cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class BasicEditDialog extends DialogFragment {
    private Communicator communicator;
    private String title;
    private String value;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Edit text to change the value.
        final EditText valueEdit = new EditText(getActivity());
        valueEdit.setText(value);
        valueEdit.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT));

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setView(valueEdit)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        communicator.onSave(valueEdit.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    public void setTitleAndValue(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public interface Communicator {
        void onSave(String value);
    }
}
