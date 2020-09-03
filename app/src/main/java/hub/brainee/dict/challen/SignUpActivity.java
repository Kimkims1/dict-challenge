package hub.brainee.dict.challen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {


    private EditText emailSignUp, passwordSignUp;
    private Button btnSignUp;
    private TextView loginTxtSignUp;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private CollectionReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        reference = firestore.collection("users");

        // TODO: 04/09/2020 init views
        initViews();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailSignUp.getText().toString().trim();
                String password = passwordSignUp.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailSignUp.setError("Invalid Email");
                    emailSignUp.setFocusable(true);
                } else if (password.length() < 6) {
                    passwordSignUp.setError("Password length at least 6 characters");
                    passwordSignUp.setFocusable(true);
                } else {
                    signUpUser(email, password);
                }
            }
        });

    }

    private void signUpUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();

                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);

                            reference.add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(SignUpActivity.this, "Account created.", Toast.LENGTH_SHORT).show();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignUpActivity.this, "An error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            Toast.makeText(SignUpActivity.this, "Registered.....\n" + user.getEmail(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUpActivity.this, Dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Account creation failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initViews() {

        emailSignUp = findViewById(R.id.emailSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        loginTxtSignUp = findViewById(R.id.loginTxtSignUp);
    }
}