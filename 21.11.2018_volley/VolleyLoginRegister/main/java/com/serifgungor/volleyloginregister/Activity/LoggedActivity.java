package com.serifgungor.volleyloginregister.Activity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoggedActivity extends AppCompatActivity {

    PaylasimAdapter adapter;
    ArrayList<Paylasim> paylasim = new ArrayList<>();
    ListView listView;

    RequestQueue queue;
    SharedPreferences sharedPreferences;


    public void icerikEkle(final String icerikYazisi) {
        StringRequest istek = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android2_gun1/icerik_ekle.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // GÖNDERDİĞİN SAYFADAN GELEN DEĞERİ YAKALA
                        Log.d("RESPONSE",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("icerik",icerikYazisi);
                map.put("mail",sharedPreferences.getString("email",""));
                /*
                PHP SAYFASINA DEĞER GÖNDER
                 */
                return map;
            }
        };
        queue.add(istek);
    }


    public void dialogCagir() {
        final Dialog d = new Dialog(LoggedActivity.this);
        d.setContentView(R.layout.yazi_ekle_layout);

        final EditText etIcerik = d.findViewById(R.id.etIcerik);
        Button btn = d.findViewById(R.id.btnEkle);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icerikEkle(etIcerik.getText().toString());
                d.dismiss();
            }
        });

        d.show();

    }


    public void paylasimlariListele() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android2_gun1/paylasim_listele.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE", response);


                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.optJSONArray("paylasimlar");
                            int elemansayisi = array.length();


                            for (int i = 0; i < elemansayisi; i++) {

                                JSONObject eleman = array.getJSONObject(i);


                                int id = eleman.getInt("id");
                                String tarih = eleman.getString("tarih");
                                String paylasan_mail = eleman.getString("paylasan_mail");
                                String paylasim_icerik = eleman.getString("paylasim_icerik");

                                paylasim.add(
                                        new Paylasim(
                                                id,
                                                tarih,
                                                paylasan_mail,
                                                paylasim_icerik
                                        )
                                );

                            }

                            adapter = new PaylasimAdapter(paylasim, getApplicationContext());
                            listView.setAdapter(adapter);


                            //Log.d("DENEME","Eleman sayısı: "+array.length());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        queue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logged, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            dialogCagir();
        }else if(item.getItemId() == R.id.item_yenile){
            paylasim.clear();
            paylasimlariListele();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        queue = Volley.newRequestQueue(getApplicationContext());

        listView = findViewById(R.id.listViewPaylasimlar);


        paylasimlariListele();


    }
}
