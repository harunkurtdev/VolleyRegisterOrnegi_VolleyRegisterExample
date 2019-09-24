package com.serifgungor.volleyloginregister.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    RegisterActivityBinding binding;
    RequestQueue queue;


    private void fillData(final String city, final String country,final String email,final String nameSurname,final String password){
        StringRequest postRequest = new StringRequest(
                Request.Method.POST, // POST tipinde istekte bulunduk
                "https://gungor.online/apps/ders1_volley/kayit.php",
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

        binding = DataBindingUtil.setContentView(this,R.layout.register_activity);
        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = binding.etRegisterCity.getText().toString();
                String country = binding.etRegisterCountry.getText().toString();
                String email = binding.etRegisterEmail.getText().toString();
                String password = binding.etRegisterPassword.getText().toString();
                String namesurname = binding.etRegisterNameSurname.getText().toString();
                fillData(city,country,email,namesurname,password);

            }
        });


    }
}
