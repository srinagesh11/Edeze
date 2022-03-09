package com.example.edeze_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// db
public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";

    Button buttonSignUpEmail, buttonSignUpTwitter, buttonSignUpFacebook, buttonSignUpGoogle;
    EditText signup_email,signup_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        // set UI elements
        buttonSignUpEmail = findViewById(R.id.signUpEmailButton);
        buttonSignUpTwitter = findViewById(R.id.signUpTwitterButton);
        buttonSignUpFacebook = findViewById(R.id.signUpFacebookButton);
        buttonSignUpGoogle = findViewById(R.id.signUpGoogleButton);
        signup_email = findViewById(R.id.signupEmailEditText);
        signup_pw = findViewById(R.id.signupPasswordEditText);

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy");
    }

    public void signupClick(View view) {
        Intent intent = new Intent(this, QAActivity.class);
        startActivity(intent);
    }
    public void signupEmailClick(View view) {
        // go to log in page
        createAccount(signup_email.getText().toString(),signup_pw.getText().toString());
    }

    public void signupSSO(View view) {
        Toast.makeText(this, "Not Working Yet", Toast.LENGTH_LONG).show();
    }


    void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).

        addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete (@NonNull Task< AuthResult > task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);

                    // if success, allow entry to app.
                    Intent intent = new Intent(SignUpActivity.this,QAActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    if (password.length()<6 || password.length()==6) {
                        Toast.makeText(SignUpActivity.this, "Failed. Password too short.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    }
                    //updateUI(null);
                }

                // ...
            }
        });
    }
}