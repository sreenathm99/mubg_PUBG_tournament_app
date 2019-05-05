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

public class more extends AppCompatActivity {
    static String jim;
    static String title1;

    WebView jmoredesc;
    TextView jmoretitle;

    private static String LOG_TAG = "EXAMPLE";

    NativeExpressAdView mAdView;
    VideoController mVideoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more);



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



        jmoretitle=(TextView) findViewById(R.id.moretitle);
        jmoredesc=(WebView) findViewById(R.id.moreweb);
        //webview settings
        jmoredesc.getSettings().setJavaScriptEnabled(true);





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


        String smoretitle;
        String smoredesc;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(jim).get();


                Elements moredesc = doc.getElementsByAttributeValue("id", "moredesc");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(moredesc.text(), HTTP.UTF_8));
                smoredesc=URLDecoder.decode(moredesc.html(), HTTP.UTF_8);

                Elements moretitle = doc.getElementsByAttributeValue("id", "moretitle");
                Log.i("PARSED ELEMENTS:", URLDecoder.decode(moretitle.text(), HTTP.UTF_8));
                smoretitle=URLDecoder.decode(moretitle.text(), HTTP.UTF_8);

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

            jmoredesc.loadData(smoredesc, "text/html", "UTF-8");
            jmoretitle.setText(smoretitle);



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
            Intent intent = new Intent(more.this, aboutus.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.support)
        {
            Intent intent = new Intent(more.this, support.class);
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
