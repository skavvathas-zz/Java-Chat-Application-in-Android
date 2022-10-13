package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyLoginActivity extends AppCompatActivity {
    private TextInputEditText editTextEmail, editTextPassword;
    private Button buttonSignin, buttonSignup;
    private TextView textViewForget;
    FirebaseAuth mAuth;
    FirebaseUser user;
    //ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_my_login);


        editTextEmail = findViewById(R.id.EditTextEmail);
        editTextPassword = findViewById(R.id.EditTextPassword);
        buttonSignin = findViewById(R.id.ButtonSignin);
        buttonSignup = findViewById(R.id.ButtonSignup);
        textViewForget = findViewById(R.id.textViewForget);

        mAuth = FirebaseAuth.getInstance();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyLoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if(!email.equals("") && !password.equals("")){ // Here i can add more if statements
                    signin(email, password);
                }
                else{
                    Toast.makeText(MyLoginActivity.this, "Please enter an email and a password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = mAuth.getCurrentUser();
        if(user!=null) {
            Log.d("FirebaseUser user", "" + user.getUid());
            startActivity(new Intent(MyLoginActivity.this, MainActivity.class));
        }
        //This live exactly down this comment has to be removed
//        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    public void signin(String email, String password){
        // sign in
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // if the sign in is successfull
                    Intent intent = new Intent(MyLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(MyLoginActivity.this, "Sign in is successful!", Toast.LENGTH_SHORT).show();

                }
                else{
                    // if the sign in is not successfull, then show Text that tells the user that
                    Toast.makeText(MyLoginActivity.this, "Sign in is not successful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}