package me.mubg.tournament.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import me.mubg.tournament.R;

public class splashscreen extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        super.onCreate(icicle);
        setContentView(R.layout.activity_splashscreen);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Connection Successful", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(splashscreen.this, MainActivity.class);
                        splashscreen.this.startActivity(mainIntent);
                        splashscreen.this.finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }
        }else
            {
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(splashscreen.this);
                mBuilder.setTitle("NO INTERNET");
                mBuilder.setCancelable(false);
                mBuilder.setMessage("Please Connect To Internet And Try Again");
                mBuilder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent j = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(j);
                    }
                });

                AlertDialog alertDialog=mBuilder.create();
                alertDialog.show();
            }


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        }

}
