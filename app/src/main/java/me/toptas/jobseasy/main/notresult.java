package me.toptas.jobseasy.main;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;

import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;

import me.toptas.jobseasy.R;

public class notresult extends AppCompatActivity {
    static String jim;
    static String title1;
    String applynowlink;
    TextView companyText;
    TextView descriptionText;
    TextView joblistText;
    FloatingActionButton fab;
    String descript;
    private static String LOG_TAG = "EXAMPLE";

    NativeExpressAdView mAdView;
    VideoController mVideoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_htmlextractpage);
        Button btn = (Button) findViewById(R.id.button);
        TextView maintitle=(TextView)findViewById(R.id.title);
        companyText = (TextView) findViewById(R.id.company);
        descriptionText=(TextView)findViewById(R.id.description);
        joblistText=(TextView)findViewById(R.id.joblist);
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
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton) ;
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
        });
        maintitle.setText(title1);
        doit d1=new doit();
        d1.execute();

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button btn = (Button) findViewById(R.id.button);
                //change the button initial text
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(applynowlink));
                    startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(applynowlink));
                    startActivity(i);
                }
            }
        });
    }
    public  class doit extends AsyncTask<Void,Void,Void> {

        String company;
        String desc;
        String jobs;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(jim).get();

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


                Elements description=doc.getElementsByAttributeValue("class","description");
                for(Element element:description)
                {
                    desc=URLDecoder.decode(element.text(),HTTP.UTF_8);
                    descript = desc;
                }
                Elements joblist=doc.getElementsByAttributeValue("class","joblist");
                for(Element element:joblist)
                {
                    jobs=URLDecoder.decode(element.text(),HTTP.UTF_8);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            companyText.setText(company);
            descriptionText.setText(desc);
            jobs=jobs.replace(",","\n");
            joblistText.setText(jobs);

        }
    }


    public static void urlpass(String jack,String title){

        jim=jack;
        title1 = title;
    }
}
