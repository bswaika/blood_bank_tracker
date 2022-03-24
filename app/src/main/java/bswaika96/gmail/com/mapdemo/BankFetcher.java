package bswaika96.gmail.com.mapdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Baladitya SWaika on 25-11-2017.
 */

public class BankFetcher extends AsyncTask<Void,Void,String> {
    Context ctx;
    ArrayList ids;
    ArrayList names;
    ArrayList lats;
    ArrayList lngs;

    BankFetcher(Context c){
        ctx = c;
        ids = new ArrayList();
        names = new ArrayList();
        lats = new ArrayList();
        lngs = new ArrayList();
    }

    @Override
    protected String doInBackground(Void... params) {
        String url = "https://bswaika96.000webhostapp.com/bankloc.php";
        try {
            URL location_url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) location_url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String line;
            while((line=bufferedReader.readLine())!=null){
                result+=line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            String[] items = result.split("<br>");
            for (String item : items){
                String[] details = item.split("=>");
                ids.add(details[0]);
                names.add(details[1]);
                lats.add(details[2]);
                lngs.add(details[3]);
            }


            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MapsActivity activity = (MapsActivity) ctx;

        activity.setUpMap(ids,lats,lngs,names);
    }
}
