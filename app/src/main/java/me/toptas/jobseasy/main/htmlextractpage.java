package me.toptas.jobseasy.main;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;

import me.toptas.jobseasy.R;

public class htmlextractpage extends AppCompatActivity {
    static String jim;
    static String title1;
    TextView companyText;
    TextView descriptionText;
    TextView joblistText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(),jim,Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlextractpage);
        TextView maintitle=(TextView)findViewById(R.id.title);
        companyText = (TextView) findViewById(R.id.company);
        descriptionText=(TextView)findViewById(R.id.description);
        joblistText=(TextView)findViewById(R.id.joblist);

        maintitle.setText(title1);
        doit d1=new doit();
        d1.execute();
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
                    String applylinktext = link.text();
                }
                //end link extratc


                Elements description=doc.getElementsByAttributeValue("class","description");
                for(Element element:description)
                {
                    desc=URLDecoder.decode(element.text(),HTTP.UTF_8);
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
            joblistText.setText(jobs);

        }
    }


    public static void urlstrng(String jack,String title){

        jim=jack;
        title1 = title;
    }
}
