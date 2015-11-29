package com.sharath.emin3m.antiragging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar mToolbar=(Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        public void showdialog(View view){
        MsgDialog msgdialog= new MsgDialog();
        msgdialog.show(getFragmentManager(), "Msg Dialog");
    }
    public void showdialog1(View view){
       ContactDialog contactdialog=new ContactDialog();
        contactdialog.show(getFragmentManager(),"Contact Dialog");
    }
}
