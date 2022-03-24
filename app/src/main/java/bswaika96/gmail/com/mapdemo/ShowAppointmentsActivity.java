package bswaika96.gmail.com.mapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowAppointmentsActivity extends AppCompatActivity {

    ListView appointmentListView;
    AppointmentAdapter appointmentAdapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointments);
        appointmentListView = (ListView) findViewById(R.id.appointmentListView);
        AppointmentFetcher appointmentFetcher = new AppointmentFetcher(this);
        if(getIntent().hasExtra("userId")){
            userId = getIntent().getExtras().getString("userId");
            appointmentFetcher.execute(userId);
        }
    }
}
