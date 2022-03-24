package bswaika96.gmail.com.mapdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdit, passwordEdit;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEdit = (EditText) findViewById(R.id.editText3);
        passwordEdit = (EditText) findViewById(R.id.editText9);

        loginButton = (Button) findViewById(R.id.button6);

        final UserLogger userLogger = new UserLogger(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if(email==""||email==null||password==""||password==null|| TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "All Fields are REQUIRED! Kindly fill them in!", Toast.LENGTH_SHORT).show();
                }else{
                    userLogger.execute(email,password);
                }

            }
        });

    }
    public void goToMapActivity(String userId){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }
}
