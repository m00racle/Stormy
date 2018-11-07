package com.mooracle.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class NetworkDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.network_unavailable_title)
                .setMessage(R.string.network_unavailable_message)
                .setPositiveButton(R.string.error_button_ok_text, null);
        return builder.create();
    }
}
