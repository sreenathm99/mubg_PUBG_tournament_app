package me.toptas.rssreader.main;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import me.toptas.rssreader.R;
import me.toptas.rssreader.model.RssItem;

public class contents extends AppCompatActivity {
    static String jim;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);



        GetContents gc=new GetContents();
        gc.execute(jim,"a=b");
    }
    public static void urlstrng(String jack){
        jim=jack;
    }




    private  class GetContents extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... p) {
            String targetURL = p[0];
            String urlParameters = p[1];
            URL url;
            HttpURLConnection connection = null;
            try {
                //Create connection
                url = new URL(targetURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                connection.setRequestProperty("Content-Length", "" +
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                //Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                return response.toString();

            } catch (Exception e) {

                e.printStackTrace();
                return null;

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }


        }

        protected void onPostExecute(String result) {

            //TextView awesome=(TextView)findViewById(R.id.textView);
            //awesome.setText(result);

        }
    }
}
