package com.serifgungor.volleyloginregister.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
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
import com.serifgungor.volleyloginregister.R;
import com.serifgungor.volleyloginregister.databinding.LoginActivityBinding;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    RequestQueue request;
    Button btnLogin,btnRegister;
    EditText etEmail,etPassword;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public void login(final String email,final String password){
        StringRequest req = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android2_gun1/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("RESPONSE",response);
                        if("100".equals(response)){
                            //Kullanıcı adı ve şifre doğru
                            startActivity(new Intent(getApplicationContext(),LoggedActivity.class));

                            editor.putString("email",etEmail.getText().toString());
                            editor.putString("sifre",etPassword.getText().toString());
                            editor.commit();




                            finish();
                        }else if("101".equals(response)){
                            Toast.makeText(getApplicationContext(), "Mail adresi veya şifre hatalı", Toast.LENGTH_SHORT).show();
                        }else if("102".equals(response)){
                            Toast.makeText(getApplicationContext(),"Veritabanı ile bağlantı sağlanamadı",Toast.LENGTH_LONG).show();
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
                map.put("password",password);
                return map;
            }
        };
        request.add(req);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        request = Volley.newRequestQueue(getApplicationContext());


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();


        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);



        etEmail.setText(sharedPreferences.getString("email",""));
        etPassword.setText(sharedPreferences.getString("sifre",""));



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}
