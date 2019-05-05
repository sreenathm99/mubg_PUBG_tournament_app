package me.mubg.tournament.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;

import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;

import me.mubg.tournament.R;

public class register extends AppCompatActivity {
    static String jim;
    static String title1;

    /*String applynowlink;
    TextView companyText;
    TextView descriptionText;
    TextView joblistText;
    FloatingActionButton fab;
    String descript;
    */
    TextView jmyserver;
    TextView jmymap;
    TextView jmymode;
    TextView jmytype;
    TextView jmytiming;
    TextView jmyversion;
    TextView jmyentry;
    TextView jmywinning;

    TextView jmymatchid;
    TextView jpayment;
    WebView jregdesc;

    private static String LOG_TAG = "EXAMPLE";

    NativeExpressAdView mAdView;
    VideoController mVideoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.registration);



        //setting up the action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();
        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });







        jmymap=(TextView)findViewById(R.id.mymap);
        jmyserver=(TextView)findViewById(R.id.myserver);
        jmymode=(TextView)findViewById(R.id.mymode);
        jmytype=(TextView)findViewById(R.id.mytype);
        jmytiming=(TextView)findViewById(R.id.mytiming);
        jmyversion=(TextView)findViewById(R.id.myversion);
        jmywinning=(TextView)findViewById(R.id.mywinning);
        jmyentry=(TextView)findViewById(R.id.myentry);
        jmymatchid=(TextView)findViewById(R.id.rmatchid);
        jpayment=(TextView)findViewById(R.id.payment);
        jregdesc=(WebView) findViewById(R.id.regdesc);

        //webview settings
        jregdesc.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = jregdesc.getSettings();
        float scale = 100f*getResources().getConfiguration().fontScale;
        settings.setTextZoom((int)scale);

  /*      TextView maintitle=(TextView)findViewById(R.id.title);
        companyText = (TextView) findViewById(R.id.company);
        descriptionText=(TextView)findViewById(R.id.description);
        joblistText=(TextView)findViewById(R.id.joblist);*/

        /*
        // Locate the NativeExpressAdView.
        mAdView = (NativeExpressAdView) findViewById(R.id.adView);

// Set its video options.
        mAdView.setVideoOptions(new VideoOptions.Builder()
                .setStartMuted(true)
                .build());

// The VideoController can be used to get lifecycle events and info about an ad's video
// asset. One will always be returned by getVideoController, even if the ad has no video
// asset.
        mVideoController = mAdView.getVideoController();
        mVideoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                Log.d(LOG_TAG, "Video playback is finished.");
                super.onVideoEnd();
            }
        });

// Set an AdListener for the AdView, so the Activity can take action when an ad has finished
// loading.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mVideoController.hasVideoContent()) {
                    Log.d(LOG_TAG, "Received an ad that contains a video asset.");
                } else {
                    Log.d(LOG_TAG, "Received an ad that does not contain a video asset.");
                }
            }
        });

        mAdView.loadAd(new AdRequest.Builder().build());

*/

      /*  fab=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody="JobEASY";
                String shareSub="New Job Opportunity!"+descript+applynowlink;
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareSub);
                startActivity(Intent.createChooser(myIntent,"Share Using"));
            }
        }); */

        doit d1=new doit();
        d1.execute();


    }
    public  class doit extends AsyncTask<Void,Void,Void> {

        String smap;
        String sserver;
        String smode;
        String stype;
        String stiming;
        String sversion;
        String swinning;
        String sentry;
        String smatchdesc;
        String smatchid;
        String spayment;
        String formurl;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(jim).get();

                Elements map = doc.getElementsByAttributeValue("id", "map");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(map.text(), HTTP.UTF_8));
                smap=URLDecoder.decode(map.text(), HTTP.UTF_8);

                Elements server = doc.getElementsByAttributeValue("id", "server");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(server.text(), HTTP.UTF_8));
                sserver=URLDecoder.decode(server.text(), HTTP.UTF_8);

                Elements mode = doc.getElementsByAttributeValue("id", "mode");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(mode.text(), HTTP.UTF_8));
                smode=URLDecoder.decode(mode.text(), HTTP.UTF_8);

                Elements type = doc.getElementsByAttributeValue("id", "type");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(type.text(), HTTP.UTF_8));
                stype=URLDecoder.decode(type.text(), HTTP.UTF_8);

                Elements timing = doc.getElementsByAttributeValue("id", "timing");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(timing.text(), HTTP.UTF_8));
                stiming=URLDecoder.decode(timing.text(), HTTP.UTF_8);

                Elements version = doc.getElementsByAttributeValue("id", "version");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(version.text(), HTTP.UTF_8));
                sversion=URLDecoder.decode(version.text(), HTTP.UTF_8);

                Elements entry = doc.getElementsByAttributeValue("id", "entry");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(entry.text(), HTTP.UTF_8));
                sentry=URLDecoder.decode(entry.text(), HTTP.UTF_8);

                Elements winning = doc.getElementsByAttributeValue("id", "winning");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(winning.text(), HTTP.UTF_8));
                swinning=URLDecoder.decode(winning.text(), HTTP.UTF_8);



                Elements matchid = doc.getElementsByAttributeValue("id", "matchid");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(matchid.text(), HTTP.UTF_8));
                smatchid=URLDecoder.decode(matchid.text(), HTTP.UTF_8);

                Elements payment = doc.getElementsByAttributeValue("id", "payment");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(payment.text(), HTTP.UTF_8));
                spayment=URLDecoder.decode(payment.text(), HTTP.UTF_8);

                Elements form = doc.getElementsByAttributeValue("id", "formurl");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(form.text(), HTTP.UTF_8));
                formurl=URLDecoder.decode(form.text(), HTTP.UTF_8);

                /*
                Elements elementsHtml = doc.getElementsByAttributeValue("class", "company");
                for (Element element : elementsHtml){
                    Log.i("PARSED ELEMENTS:", URLDecoder.decode(element.text(), HTTP.UTF_8));
                   company=URLDecoder.decode(element.text(), HTTP.UTF_8);


                }


                //start link extract 
                Elements links = doc.getElementsByAttributeValue("id", "applink");
                for (Element link : links)
                {
                    // get the value from href attribute
                    System.out.println("\nlink : " + link.attr("href"));
                    System.out.println("text : " + link.text());
                    String applylink = link.attr("href");
                    applynowlink=applylink;
                    String applylinktext = link.text();
                }
                //end link extratc


                Elements description=doc.getElementsByAttributeValue("class","formm");

                for(Element element:description)
                {
                    desc=URLDecoder.decode(element.text(),HTTP.UTF_8);
                    descript = desc;

                }
                Elements joblist=doc.getElementsByAttributeValue("class","company");
                for(Element element:joblist)
                {
                    jobs=URLDecoder.decode(element.text(),HTTP.UTF_8);
                }

                */

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
           /* companyText.setText(company);
            descriptionText.setText(desc);
            jobs=jobs.replace(",","\n");
            joblistText.setText(jobs);
            */
            jmymap.setText(smap);
            jmyserver.setText(sserver);
            jmymode.setText(smode);
            jmytype.setText(stype);
            jmytiming.setText(stiming);
            jmyversion.setText(sversion);
            jmyentry.setText(sentry);
            jmywinning.setText(swinning);
            jmymatchid.setText(smatchid);
            jpayment.setText(spayment);
            jregdesc.loadUrl(formurl);
        }
    }



    //start code for menu inflater on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_aboutus) {
            Intent intent = new Intent(register.this, aboutus.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.support)
        {
            Intent intent = new Intent(register.this, support.class);
            startActivity(intent);
            return true;

        }
        return  true;
    }
    // end code for menu inflater on action bar


    public static void urlstrng(String jack,String title){

        jim=jack;
        title1 = title;
    }
}
