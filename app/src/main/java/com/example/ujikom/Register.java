package com.example.ujikom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private String email, password, rePassword;
    private EditText etEmail, etPassword, etRePassword;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(Register.this);
        Register = (Button) findViewById(R.id.buttonRegister);
        etEmail = (EditText) findViewById(R.id.editTextUsernameRegister);
        etPassword = (EditText) findViewById(R.id.editTextPasswordRegister);
        etRePassword = (EditText) findViewById(R.id.editTextRe_PasswordRegister);

        firebaseAuth = FirebaseAuth.getInstance();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                rePassword = etRePassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(rePassword)) {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Intent mIntent = new Intent(Register.this, MainPenumpang.class);
                                        startActivity(mIntent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else{
                    Toast.makeText(Register.this,"Password Didn't Match", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}
