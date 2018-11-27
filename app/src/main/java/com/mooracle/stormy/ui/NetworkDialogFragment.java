package com.mooracle.stormy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.mooracle.stormy.R;

public class NetworkDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.network_unavailable_title)
                .setMessage(R.string.network_unavailable_message)
                .setPositiveButton(R.string.error_button_ok_text, null);
        return builder.create();
    }
}
