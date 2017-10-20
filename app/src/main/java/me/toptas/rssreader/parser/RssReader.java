package me.toptas.rssreader.parser;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import me.toptas.rssreader.base.Logger;
import me.toptas.rssreader.model.RssItem;

public class RssReader extends AsyncTask<Void, Integer, ArrayList<RssItem>> {

    private final OnRssParserListener mListener;
    private final String mRssUrl;

    public RssReader(OnRssParserListener listener, String rssUrl) {
        mListener = listener;
        mRssUrl = rssUrl;
        Logger.logv("Fetching: " + rssUrl);
    }

    private String mEncoding;


    @Override
    protected ArrayList<RssItem> doInBackground(Void... params) {
        ArrayList<RssItem> list = null;
        InputStream inputStream = null;
        try {
            java.net.URL url = new URL(mRssUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.addRequestProperty("User-Agent", "");

            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200 || responseCode == 302) {
                list = new ArrayList<>();
                inputStream = httpURLConnection.getInputStream();
                String responseString = readInputStream(inputStream, mEncoding);
                RssParser myXMLHandler = new RssParser();
                SAXParserFactory saxPF = SAXParserFactory.newInstance();
                SAXParser saxP = saxPF.newSAXParser();
                XMLReader xmlR = saxP.getXMLReader();

                /**
                 * Create the Handler to handle each of the XML tags.
                 **/
                xmlR.setContentHandler(myXMLHandler);

                InputSource inputSource = new InputSource(new StringReader(responseString));
                if (mEncoding != null) {
                    inputSource.setEncoding(mEncoding);
                }
                xmlR.parse(inputSource);
                ArrayList<RssItem> items = myXMLHandler.getItems();
                list.addAll(items);

                return list;
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    @Override
    protected void onPostExecute(ArrayList<RssItem> rssItemModels) {
        super.onPostExecute(rssItemModels);
        if (rssItemModels != null && rssItemModels.size() > 0) {
            mListener.onSuccess(rssItemModels, mRssUrl);
        } else {
            mListener.onFail(mRssUrl);
        }
    }

    public void setEncoding(String encoding) {
        mEncoding = encoding;
    }

    private String readInputStream(InputStream stream, @Nullable String encoding) throws IOException {
        BufferedReader reader;
        if (encoding != null) {
            reader = new BufferedReader(new InputStreamReader(stream, encoding));
        } else {
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

}