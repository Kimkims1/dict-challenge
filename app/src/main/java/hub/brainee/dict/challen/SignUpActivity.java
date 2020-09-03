package hub.brainee.dict.challen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {


    private EditText emailSignUp, passwordSignUp;
    private Button btnSignUp;
    private TextView loginTxtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // TODO: 04/09/2020 init views
        initViews();
    }

    private void initViews() {

        emailSignUp = findViewById(R.id.emailSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        loginTxtSignUp = findViewById(R.id.loginTxtSignUp);
    }
}