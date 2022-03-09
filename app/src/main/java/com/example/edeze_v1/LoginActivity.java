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
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    EditText et_login_email,et_login_pw;
    Button buttonLoginEmail,buttonLoginTwitter,buttonLoginFacebook,buttonLoginGoogle;
    String pw_string,email_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create");
        setContentView(R.layout.activity_login);


        // set UI elements
        buttonLoginEmail = findViewById(R.id.loginEmailButton);
        buttonLoginTwitter = findViewById(R.id.loginTwitterButton);
        buttonLoginFacebook = findViewById(R.id.loginFacebookButton);
        buttonLoginGoogle = findViewById(R.id.loginGoogleButton);
        et_login_email = findViewById(R.id.loginEmailEditText);
        et_login_pw = findViewById(R.id.loginPasswordEditText);


        // init FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"On Start");

        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"On Resume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"On Pause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"On Stop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"On Destroy");
    }

    public void loginClick(View view) {
        email_string = et_login_email.getText().toString();
        pw_string = et_login_pw.getText().toString();
        // go to home Q&A feed
        Intent intent = new Intent(this, QAActivity.class);
        startActivity(intent);
    }

    public void loginEmailClick (View view) {
        email_string = et_login_email.getText().toString();
        pw_string = et_login_pw.getText().toString();

        logIn(email_string,pw_string);
    }

    public void loginSSO(View view) {
        Toast.makeText(this, "Not Working Yet", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,QAActivity.class);
        startActivity(intent);
    }

    void logIn(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            Toast.makeText(LoginActivity.this, "Authenticated.",
                                    Toast.LENGTH_SHORT).show();
                            // if success, allow entry to app.
                            Intent intent = new Intent(LoginActivity.this,QAActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }

}