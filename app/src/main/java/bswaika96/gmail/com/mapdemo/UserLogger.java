package bswaika96.gmail.com.mapdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by Baladitya SWaika on 25-11-2017.
 */

public class UserLogger extends AsyncTask<String,Void,String> {
    Context ctx;

    UserLogger(Context c){
        ctx = c;
    }

    @Override
    protected String doInBackground(String... strings) {

        String url = "https://bswaika96.000webhostapp.com/loginuser.php";
        try {
            URL location_url = new URL(url);

            String email = strings[0];
            String password = strings[1];

            HttpURLConnection httpURLConnection = (HttpURLConnection) location_url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String post_data = "email="+URLEncoder.encode(email,"UTF-8")+"&password="+URLEncoder.encode(password,"UTF-8");
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
        int result = Integer.parseInt(s);
        if(result==0){
            Toast.makeText(ctx, "Login Unsuccessful! Try again...", Toast.LENGTH_SHORT).show();
        }else{
            String userId = s;
            LoginActivity activity = (LoginActivity) ctx;
            activity.goToMapActivity(userId);
        }
    }
}
