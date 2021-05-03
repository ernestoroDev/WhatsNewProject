package com.example.whatsnewproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whatsnewproject.*;
import com.google.rpc.context.AttributeContext;

public class ventana2 extends AppCompatActivity {

    EditText nombre;
    TextView texto1,texto2,texto3;
    Button env;

    RequestQueue request;

    private static final String URL=" https://www.googleapis.com/books/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);

        texto1  = findViewById(R.id.txt1);
        texto2 = findViewById(R.id.txt2);
        texto3 = findViewById(R.id.txt3);
        nombre = findViewById(R.id.nombre_buscado);
        env = findViewById(R.id.enviar);

        request = Volley.newRequestQueue(this);




        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringRequest();

            }
        });

    }

    private void stringRequest(){

        StringRequest req = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nombre.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.add(req);

    }


}