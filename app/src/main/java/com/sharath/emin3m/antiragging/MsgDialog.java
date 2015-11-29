package com.sharath.emin3m.antiragging;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MsgDialog extends DialogFragment {
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.set_message,null);
        builder.setView(view)
        .setTitle("Set Message")
        .setIcon(R.mipmap.ic_message);
        final EditText localEditText =(EditText)view.findViewById(R.id.editText);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sp = getActivity().getSharedPreferences("demo", 2);
                        ed = sp.edit();
                        ed.putString("aaa", localEditText.getText().toString());
                        ed.commit();
                        Toast.makeText(getActivity(), "Message Saved", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
