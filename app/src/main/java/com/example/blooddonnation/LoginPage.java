package com.example.blooddonnation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginPage extends AppCompatActivity {
    private Button logon;
    private TextInputEditText id;
    private TextInputEditText pwd;
    String Id,PWD;
    private TextView register;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        register = findViewById(R.id.register);
        logon = findViewById(R.id.modify);
        id = findViewById(R.id.Email);
        pwd = findViewById(R.id.bld);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignUpPage.class));
            }
        });
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id = id.getText().toString();
                PWD = pwd.getText().toString();
                if (Id.isEmpty()) {
                    id.setError("Make sure to give your email !");
                    id.requestFocus();
                    id.setText("");
                    pwd.setText("");

                } else if (PWD.isEmpty()) {
                    pwd.setError("Make sure to type your password !");
                    pwd.requestFocus();
                    id.setText("");
                    pwd.setText("");
                } else {
                    auth.signInWithEmailAndPassword(Id, PWD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(LoginPage.this, "Authentication succeeded !",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginPage.this, MainActivity2.class));
                                finish();
                            } else {
                                Toast.makeText(LoginPage.this, "Authentication failed !",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}