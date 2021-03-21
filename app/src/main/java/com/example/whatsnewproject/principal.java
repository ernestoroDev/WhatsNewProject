package com.example.whatsnewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class principal extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button bt_registrar,bt_login,bt_cerrar;
    private EditText text_email,text_contra;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        auth= FirebaseAuth.getInstance();

          bt_registrar = (Button)findViewById(R.id.bt_registrar);
          bt_login = (Button)findViewById(R.id.bt_login);
          bt_cerrar = (Button)findViewById(R.id.bt_cerrar);

          text_email = (EditText) findViewById(R.id.text_email);
          text_contra = (EditText) findViewById(R.id.text_contra);

        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("message","Integracion de Firebase completada");
        analytics.logEvent("InitScreen",bundle);

        Setup();
    }


    //Setup
    private void Setup(){

        setTitle("Autenticacion");
        FirebaseUser esteUsuario = auth.getCurrentUser();


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                    auth.createUserWithEmailAndPassword(text_email.getText().toString(),text_contra.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Log.w("TAG","Creado correctamente");

                            }else{
                                Log.w("TAG","Error al crear el usuario con contraseña",task.getException());
                                Toast.makeText(principal.this,"Autenticacion Fallida",Toast.LENGTH_SHORT);

                            }
                        }
                    });
            };
        });



    }
}