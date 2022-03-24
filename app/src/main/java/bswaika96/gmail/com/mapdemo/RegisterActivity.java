package bswaika96.gmail.com.mapdemo;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText nameEdit, ageEdit, addressEdit, contactEdit, emailEdit, passwordEdit, bgroupEdit;
    Button registerButton;
    RadioButton femaleRadio, maleRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final UserSetter userSetter = new UserSetter(this);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        ageEdit = (EditText) findViewById(R.id.ageEdit);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        contactEdit = (EditText) findViewById(R.id.contactEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        bgroupEdit = (EditText) findViewById(R.id.bgroupEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        femaleRadio = (RadioButton) findViewById(R.id.femaleRadio);
        maleRadio = (RadioButton) findViewById(R.id.maleRadio);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String age = ageEdit.getText().toString();
                String address = addressEdit.getText().toString();
                String contact = contactEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String bgroup = bgroupEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String gender = "M";
                if(femaleRadio.isChecked()){
                    gender = "F";
                }

                if(TextUtils.isEmpty(name) || name=="" || name==null || TextUtils.isEmpty(age) || TextUtils.isEmpty(address) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(email) || TextUtils.isEmpty(bgroup) || TextUtils.isEmpty(password) || age=="" || age==null || address=="" || address==null || contact=="" || contact=="null" || email=="" || email==null || bgroup=="" || bgroup==null || password=="" || password==null){
                    Toast.makeText(RegisterActivity.this, "All Fields are REQUIRED! Kindly fill them in!", Toast.LENGTH_SHORT).show();
                }else{
                    userSetter.execute(name,age,address,contact,email,bgroup,password,gender);
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
