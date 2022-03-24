package bswaika96.gmail.com.mapdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAppointmentActivity extends AppCompatActivity {

    Button createButton;
    EditText datePicker;
    EditText timePicker;
    String type,bname,userId;
    AppointmentCreator appointmentCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        appointmentCreator = new AppointmentCreator(this);


        createButton = (Button) findViewById(R.id.button5);
        datePicker = (EditText) findViewById(R.id.editText);
        timePicker = (EditText) findViewById(R.id.editText2);

        if(getIntent().hasExtra("type")&&getIntent().hasExtra("userId")&&getIntent().hasExtra("bname")){
            type = getIntent().getExtras().getString("type");
            userId = getIntent().getExtras().getString("userId");
            bname = getIntent().getExtras().getString("bname");
        }

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = datePicker.getText().toString();
                String time = timePicker.getText().toString();
                appointmentCreator.execute(type,userId,bname,date,time);
            }
        });
    }

    public void goToMapActivity(String userId){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }
}
