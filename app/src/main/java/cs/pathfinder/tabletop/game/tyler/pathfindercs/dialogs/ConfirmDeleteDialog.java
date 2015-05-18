package cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;

/**
 * Created by Tyler on 5/9/2015.
 */
public class ConfirmDeleteDialog extends DialogFragment {
    private Communicator communicator;
    private int character;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete this Character?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        communicator.onConfirmDelete(character);
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

    public void setCharacter(int position) {
        character = position;
    }

    public interface Communicator {
        public void onConfirmDelete(int character);
    }
}
