package bswaika96.gmail.com.mapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView nameView;
    TextView bgroupView;
    TextView contactView;
    TextView emailView;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ProfileFetcher profileFetcher = new ProfileFetcher(this);


        nameView = (TextView) findViewById(R.id.textView5);
        bgroupView = (TextView) findViewById(R.id.textView6);
        contactView = (TextView) findViewById(R.id.textView7);
        emailView = (TextView) findViewById(R.id.textView8);
        nameView.setText("");
        bgroupView.setText("");
        contactView.setText("");
        emailView.setText("");
        if(getIntent().hasExtra("userId")){
            userId = getIntent().getExtras().getString("userId");
            profileFetcher.execute(userId);
        }
    }

    public void setUserDetails(String name, String bgroup,String contact, String email){
        nameView.setText(name);
        bgroupView.setText(bgroup);
        contactView.setText(contact);
        emailView.setText(email);
    }
}
