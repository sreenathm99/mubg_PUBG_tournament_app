package me.toptas.rssreader.main;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;

import me.toptas.rssreader.R;

public class htmlextractpage extends AppCompatActivity {
    static String jim;
    TextView outputTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlextractpage);
        outputTextView = (TextView) findViewById(R.id.textView2);
        new doit().execute();
    }
    public  class doit extends AsyncTask<Void,Void,Void> {

       String parsed;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://songspknew.blogspot.in/2017/10/dsdsdsd.html").get();

                Elements elementsHtml = doc.getElementsByAttributeValue("class", "jack");

                for (Element element : elementsHtml){
                    Log.i("PARSED ELEMENTS:", URLDecoder.decode(element.text(), HTTP.UTF_8));
                   parsed=URLDecoder.decode(element.text(), HTTP.UTF_8);


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
            outputTextView.setText(parsed);
        }
    }


    public static void urlstrng(String jack){
        jim=jack;
    }
}
