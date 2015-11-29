package com.sharath.emin3m.antiragging;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactViewDialog extends DialogFragment {
    SharedPreferences sp;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.to_view_contact, null);
        builder.setView(view)
        .setTitle("View Contact")
        .setIcon(R.mipmap.ic_message);
        TextView localTextView =(TextView)view.findViewById(R.id.textView);
        sp = getActivity().getSharedPreferences("sdat", 1);
        String savedContact = sp.getString("snum", "");
        localTextView.setText(savedContact);
        builder.setPositiveButton(R.string.okay,null);
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
