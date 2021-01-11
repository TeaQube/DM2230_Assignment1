package com.sidm.A1;

import android.app.AlertDialog;
import android.app.Dialog;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.content.DialogInterface;

//by qing bing
public class PauseConfirmDialogFragment extends DialogFragment
{
    //to check if dialog is shown
    public static boolean IsShown = false;

    //dialog is pop-up page but has written API
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        IsShown = true;
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

    @Override
    public void onCancel(DialogInterface dialog) {
        IsShown = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        IsShown = false;
    }

    public void show(FragmentManager fragmentManager, String pauseConfirm) {
    }


}
