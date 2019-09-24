package com.serifgungor.volleyloginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin,btnRegister;
    EditText etEmail,etPassword;

    RequestQueue queue; //Volley sınıfı istek kuyruğu


    public void login(final String email,final String password){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android_haftasonu/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if("ok".equals(response)){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else if("fail".equals(response)){
                            Toast.makeText(
                                    getApplicationContext(),"Mail yada şifre hatalı",Toast.LENGTH_LONG
                            ).show();
                        }
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
                map.put("pass",password);
                return map;
            }
        };
        queue.add(request);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(getApplicationContext());
        //İstek kuyruğunu onCreate'de tanımladık.

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });
    }
}
