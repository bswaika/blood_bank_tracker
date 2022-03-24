package bswaika96.gmail.com.mapdemo;

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
import java.util.ArrayList;

/**
 * Created by Baladitya SWaika on 25-11-2017.
 */

public class AppointmentFetcher extends AsyncTask<String,Void,String> {

    String userId;
    Context ctx;
    ArrayList names;
    ArrayList addresses;
    ArrayList contacts;
    ArrayList dates;
    ArrayList times;
    ArrayList types;

    AppointmentFetcher(Context c){
        ctx = c;
        names = new ArrayList();
        addresses = new ArrayList();
        contacts = new ArrayList();
        dates = new ArrayList();
        times = new ArrayList();
        types = new ArrayList();

    }

    @Override
    protected String doInBackground(String... strings) {
        String url = "https://bswaika96.000webhostapp.com/userfetchappointment.php";
        try {
            userId = strings[0];
            URL location_url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) location_url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String post_data = "id="+ URLEncoder.encode(userId,"UTF-8");
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
            for (String item : items){
                String[] details = item.split("=>");
                names.add(details[0]);
                addresses.add(details[1]);
                contacts.add(details[2]);
                dates.add(details[3]);
                times.add(details[4]);
                types.add(details[5]);
            }


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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ShowAppointmentsActivity activity = (ShowAppointmentsActivity)ctx;
        if(names!=null&&addresses!=null&&contacts!=null&&dates!=null&&times!=null&&types!=null){
            activity.appointmentAdapter = new AppointmentAdapter(ctx,names,addresses,contacts,dates,times,types);
            activity.appointmentListView.setAdapter(activity.appointmentAdapter);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
