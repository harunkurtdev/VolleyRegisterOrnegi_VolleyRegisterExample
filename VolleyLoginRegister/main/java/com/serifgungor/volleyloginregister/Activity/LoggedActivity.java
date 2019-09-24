package com.serifgungor.volleyloginregister.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.serifgungor.volleyloginregister.Adapter.PaylasimAdapter;
import com.serifgungor.volleyloginregister.Model.Paylasim;
import com.serifgungor.volleyloginregister.R;

import java.util.ArrayList;
import java.util.Map;

public class LoggedActivity extends AppCompatActivity {

    PaylasimAdapter adapter;
    ArrayList<Paylasim> paylasim = new ArrayList<>();
    ListView listView;

    RequestQueue queue;


    public void paylasimlariListele(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android2_gun1/paylasim_listele.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE",response);
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
                return super.getParams();
            }
        };
        queue.add(stringRequest);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        queue = Volley.newRequestQueue(getApplicationContext());

        listView = findViewById(R.id.listViewPaylasimlar);

        /*
        paylasim.add(new Paylasim(1,"Tarih","İçerik","Email"));
        paylasim.add(new Paylasim(1,"Tarih","İçerik","Email"));
        paylasim.add(new Paylasim(1,"Tarih","İçerik","Email"));
        paylasim.add(new Paylasim(1,"Tarih","İçerik","Email"));

        adapter = new PaylasimAdapter(paylasim,this);

        listView.setAdapter(adapter);
        */


        paylasimlariListele();


    }
}
