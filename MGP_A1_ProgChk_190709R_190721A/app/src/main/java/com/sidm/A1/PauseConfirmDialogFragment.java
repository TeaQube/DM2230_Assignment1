package com.sidm.A1;

import android.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;



public class PauseConfirmDialogFragment extends DialogFragment {
    // To check if the dialog is shown.
    public static boolean IsShown = false;

    // Dialog is a pop page but it has is written API. "onCreateDialog"
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        IsShown = true;  //Boolean will become true upon Dialog to be used
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User triggered pause
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the pause
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    public void show(FragmentManager fragmentManager, String pauseConfirm) {
    }
}
