package bswaika96.gmail.com.mapdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Baladitya SWaika on 25-11-2017.
 */

public class BankDetailsFetcher extends AsyncTask<String,Void,String> {

    Context ctx;

    String name;
    String address;
    String contact;
    String email;

    BankDetailsFetcher(Context c){
        ctx = c;
    }

    @Override
    protected String doInBackground(String... strings) {

        String url = "https://bswaika96.000webhostapp.com/bankdet.php";
        try {
            URL location_url = new URL(url);
            String id = strings[0];
            HttpURLConnection httpURLConnection = (HttpURLConnection) location_url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String post_data = "id="+URLEncoder.encode(id,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

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

                name = items[0];
                address = items[1];
                contact = items[2];
                email = items[3];




            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
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
        BloodBankDetailsActivity activity = (BloodBankDetailsActivity) ctx;
        if(name!=null && address!=null && contact!=null && email!=null){
            activity.setBankDetails(name,address,contact,email);
        }else if(email==null){
            activity.setBankDetails(name,address,contact);
        }

    }
}
