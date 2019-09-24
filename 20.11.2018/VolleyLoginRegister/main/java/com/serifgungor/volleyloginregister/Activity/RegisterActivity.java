package com.serifgungor.volleyloginregister.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.serifgungor.volleyloginregister.R;
import com.serifgungor.volleyloginregister.databinding.RegisterActivityBinding;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    RequestQueue queue;
    EditText etRegisterCity,etRegisterCountry,etRegisterEmail,etRegisterPassword,etRegisterNameSurname;

    Button btnReg;


    private void fillData(final String city, final String country,final String email,final String nameSurname,final String password){
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, // POST tipinde istekte bulunduk
                "http://10.1.9.14:8081/android2_gun1/kayit.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE",response);
                        // String response web sayfasından sonucu döndürecektir.
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("RESPONSE",error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("city",city);
                params.put("country",country);
                params.put("email",email);
                params.put("nameSurname",nameSurname);
                params.put("password",password);
                /*
                POST tipinde ilgili sayfaya ilgili parametreleri gönder.
                 */
                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        queue = Volley.newRequestQueue(getApplicationContext());


        etRegisterCity = findViewById(R.id.etRegisterCity);
        etRegisterCountry = findViewById(R.id.etRegisterCountry);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterNameSurname = findViewById(R.id.etRegisterNameSurname);
        btnReg = findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = etRegisterCity.getText().toString();
                String country = etRegisterCountry.getText().toString();
                String email = etRegisterEmail.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String namesurname = etRegisterNameSurname.getText().toString();
                fillData(city,country,email,namesurname,password);

            }
        });


    }
}
