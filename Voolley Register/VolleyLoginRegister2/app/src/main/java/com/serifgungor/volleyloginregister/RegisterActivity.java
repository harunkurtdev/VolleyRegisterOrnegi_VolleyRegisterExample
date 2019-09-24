package com.serifgungor.volleyloginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button btnKayit;
    EditText etAdSoyad,etEmail,etPassword,etSehir,etDogumTarihi;

    RequestQueue istekKuyrugu;

    public void kayitol(final String email,final String adsoyad,final String password,final String sehir,final String dogumtarihi){
        StringRequest istek = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android_haftasonu/register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("WEB_RESPONSE",response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("adsoyad",adsoyad);
                map.put("pass",password);
                map.put("sehir",sehir);
                map.put("dogumtarihi",dogumtarihi);
                return map;
            }
        };
        istekKuyrugu.add(istek);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        istekKuyrugu = Volley.newRequestQueue(getApplicationContext());

        btnKayit = findViewById(R.id.btnSignUp);
        etEmail = findViewById(R.id.etRegisterEmail);
        etAdSoyad = findViewById(R.id.etRegisterAdSoyad);
        etDogumTarihi = findViewById(R.id.etRegisterDogumTarihi);
        etPassword = findViewById(R.id.etRegisterSifre);
        etSehir = findViewById(R.id.etRegisterSehir);

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitol(
                     etEmail.getText().toString(),
                     etAdSoyad.getText().toString(),
                     etPassword.getText().toString(),
                         etSehir.getText().toString(),
                        etDogumTarihi.getText().toString()
                );
            }
        });

    }
}
