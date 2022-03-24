package bswaika96.gmail.com.mapdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BloodBankDetailsActivity extends AppCompatActivity {

    TextView nameView;
    TextView addressView;
    TextView contactView;
    TextView emailView;
    String userId;
    String bname;
    Button requestButton, donateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details);
        BankDetailsFetcher bankDetailsFetcher = new BankDetailsFetcher(this);

        nameView = (TextView) findViewById(R.id.textView2);
        addressView = (TextView) findViewById(R.id.textView3);
        contactView = (TextView) findViewById(R.id.textView);
        emailView = (TextView) findViewById(R.id.textView4);

        requestButton = (Button) findViewById(R.id.button);
        donateButton = (Button) findViewById(R.id.button3);




        nameView.setText("");
        addressView.setText("");
        contactView.setText("");
        emailView.setText("");
        if(getIntent().hasExtra("bankname")&&getIntent().hasExtra("userId")){
            bname = getIntent().getExtras().getString("bankname");
            userId = getIntent().getExtras().getString("userId");
            bankDetailsFetcher.execute(bname);
        }

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAppointmentActivity.class);
                intent.putExtra("type","request");
                intent.putExtra("userId",userId);
                intent.putExtra("bname",bname);
                startActivity(intent);
            }
        });

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAppointmentActivity.class);
                intent.putExtra("type","donate");
                intent.putExtra("userId",userId);
                intent.putExtra("bname",bname);
                startActivity(intent);
            }
        });
    }

    public void setBankDetails(String name, String address,String contact, String email){
        nameView.setText(name);
        addressView.setText(address);
        contactView.setText(contact);
        emailView.setText(email);
    }
    public void setBankDetails(String name, String address,String contact){
        nameView.setText(name);
        addressView.setText(address);
        contactView.setText(contact);
    }
}
