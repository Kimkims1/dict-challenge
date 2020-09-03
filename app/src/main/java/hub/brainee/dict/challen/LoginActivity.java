package hub.brainee.dict.challen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button btnFacebook, btnGoogle, btnLogin;
    private EditText emailLogin, passwordLogin;
    private TextView forgotLoginPassword, signUpTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO: 04/09/2020 Initialize Views
        initViews();


    }

    private void initViews() {
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnLogin = findViewById(R.id.btnLogin);
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        forgotLoginPassword = findViewById(R.id.forgotLoginPassword);
        signUpTxt = findViewById(R.id.signUpTxt);
    }
}