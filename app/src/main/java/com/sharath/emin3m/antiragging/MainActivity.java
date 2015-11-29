package com.sharath.emin3m.antiragging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String[] arrr1;
    private Button ib1;
    private double latitude, longitude;
    private LocationManager lm;
    private SharedPreferences sp, sp1;
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mDrawer=(NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        ib1=(Button)findViewById(R.id.send_button);
        lm=(LocationManager)getSystemService(LOCATION_SERVICE);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences("demo", 1);
                final String str1 = sp.getString("aaa", "");
                sp1 = getSharedPreferences("sdat", 1);
                String str2 = sp1.getString("snum", "");
                arrr1 = str2.split(",");
                Toast.makeText(getApplicationContext(), str2 + " - " + str1, Toast.LENGTH_SHORT).show();
                System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL" + str2 +""+ str1);
                try{
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, new LocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        Toast.makeText(getApplicationContext(),"GPS is Enabled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Toast.makeText(getApplicationContext(),"GPS is not Enabled", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onLocationChanged(Location location) {
                            //location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Toast.makeText(getApplicationContext(), "Latitude:" + latitude + "\n" + "Longitude:" + longitude, Toast.LENGTH_SHORT).show();
                            String str = "http://maps.google.com/maps?q=" + latitude + "," + longitude;
                        for (int i = 0;i<arrr1.length ; i++) {
                            SmsManager sm = SmsManager.getDefault();
                            Toast.makeText(getApplicationContext(),str1+" "+arrr1[i], Toast.LENGTH_SHORT).show();
                            sm.sendTextMessage(arrr1[i], null, str1 + " Come to this loaction "+str, null, null);
                            Toast.makeText(getApplicationContext(),"SMS Sent with Location", Toast.LENGTH_SHORT).show();
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + arrr1);
                        }

                    }

                });
                }
                catch(Exception e){
                    for (int i = 0;i< arrr1.length ; i++) {
                        SmsManager sm = SmsManager.getDefault();
                        Toast.makeText(getApplicationContext(),str1+" "+arrr1[i], Toast.LENGTH_SHORT).show();
                        sm.sendTextMessage(arrr1[i], null, str1 + "Come at this loaction", null, null);
                        Toast.makeText(getApplicationContext(), "SMS Sent without Location", Toast.LENGTH_SHORT).show();
                        System.out.println("^^^^^^^^^^^^^^^^^^^^" + arrr1);
                   }
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent=null;
        if(menuItem.getItemId()==R.id.see_message){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            MsgViewDialog msgviewdialog= new MsgViewDialog();
            msgviewdialog.show(getFragmentManager(), "Msg View Dialog");
            return true;
        }
        if(menuItem.getItemId()==R.id.see_contact){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            ContactViewDialog contactviewdialog= new ContactViewDialog();
            contactviewdialog.show(getFragmentManager(), "Contact View Dialog");
            return true;
        }
        if(menuItem.getItemId()==R.id.settings_activity){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if(menuItem.getItemId()==R.id.help_activity) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        if(menuItem.getItemId()==R.id.about){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent=new Intent(this,AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
    public void onBackPressed(){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
