package com.sharath.emin3m.antiragging;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactDialog extends DialogFragment{
    ArrayList<String> alist = new ArrayList();
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    String setnum="";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.set_contact, null);
        builder.setView(view)
        .setTitle("Set Contact")
        .setIcon(R.mipmap.ic_contact);
        final MultiAutoCompleteTextView localMultiAutoCompleteTextview=(MultiAutoCompleteTextView)view.findViewById(R.id.multiAutoCompleteTextView1);
        Cursor localCursor=getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, "display_name ASC");
        if(localCursor.moveToFirst()){
            do{
                String str1=localCursor.getString(localCursor.getColumnIndex("display_name"));
                String str2=localCursor.getString(localCursor.getColumnIndex("data1"));
                String str3=str1+"%"+str2;
                alist.add(str3);
            }while(localCursor.moveToNext());
        ArrayAdapter localArrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,alist);
            localMultiAutoCompleteTextview.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            localMultiAutoCompleteTextview.setAdapter(localArrayAdapter);
        }

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        })

                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = localMultiAutoCompleteTextview.getText().toString();
                        String[] arrayOfString = str.split(",");
                        for (int i = 0; i < arrayOfString.length; i++) {
                            if (arrayOfString[i].contains("%")) {
                                String[] s2 = arrayOfString[i].split("%");
                                String s1 = s2[1];
                                setnum = setnum + s1 + ",";
                            } else {
                                String sd = arrayOfString[i];
                                setnum = setnum + sd;
                            }
                        }
                        System.out.println("************************" + setnum);
                        Toast.makeText(getActivity(), setnum, Toast.LENGTH_SHORT).show();
                        sp = getActivity().getSharedPreferences("sdat", 2);
                        ed = sp.edit();
                        ed.putString("snum", setnum);
                        ed.commit();
                        setnum = "";
                        getActivity().finish();
                    }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

}