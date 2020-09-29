package com.example.projetomobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText tilName, tilUser, tilEmail, tilPassword;
    ImageButton btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mAuth = FirebaseAuth.getInstance();
        tilName = findViewById(R.id.tilName);
        tilUser = findViewById(R.id.tilUser);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btSalvar = findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCadastrar();
            }
        });
    }
    public void doCadastrar(){
        String email, password;
        email = tilEmail.getText().toString();
        password = tilPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastrarActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(CadastrarActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser firebaseUser){
        Toast.makeText(CadastrarActivity.this, "Logue para continuar", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(CadastrarActivity.this, LoginActivity.class));
    }
}
