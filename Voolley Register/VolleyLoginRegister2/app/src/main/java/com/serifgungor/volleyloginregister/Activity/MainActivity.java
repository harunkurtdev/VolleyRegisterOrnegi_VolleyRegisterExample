package com.serifgungor.volleyloginregister.Activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.serifgungor.volleyloginregister.Model.UserShare;
import com.serifgungor.volleyloginregister.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etPaylasimYazisi;
    Button btnPaylas;
    ListView listViewPaylasimlar;
    RequestQueue istekKuyrugu;
    SharedPreferences sharedPreferences;
    ArrayAdapter<UserShare> adapter;
    ArrayList<UserShare> paylasimlar;


    public void paylasimiKaldir(final int paylasimId){
        StringRequest istek = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android_haftasonu/remove_share.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                map.put("shareid",""+paylasimId);
                map.put("userid",""+sharedPreferences.getInt("userid",0));
                return map;
            }
        };
        istekKuyrugu.add(istek);
    }


    public void paylasimlariGetir(){
        StringRequest istek = new StringRequest(
                Request.Method.POST,
                "http://10.1.9.14:8081/android_haftasonu/get_shares.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("WEB_RESPONSE",response);
                        paylasimlar = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("UserShares");
                            for(int i=0; i<jsonArray.length(); i++){

                                JSONObject item = jsonArray.getJSONObject(i);
                                paylasimlar.add(
                                        new UserShare(
                                            Integer.parseInt(item.getString("id")),
                                            Integer.parseInt(item.getString("user_id")),
                                            item.getString("share_time"),
                                            item.getString("share_content")
                                        )
                                );

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,paylasimlar);
                        listViewPaylasimlar.setAdapter(adapter);
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
                map.put("userid",""+sharedPreferences.getInt("userid",0));
                return map;
            }
        };
        istekKuyrugu.add(istek);
    }

    public void paylasimYap() {
        StringRequest istek = new StringRequest(
                Request.Method.POST, // İstekte bulunacağımız tip
                "http://10.1.9.14:8081/android_haftasonu/share.php", // İstekte bulunacağımız web sayfası yolu
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // sayfadan dönen yanıt String response içerisine düşer.
                        //Log.d("WEB_RESPONSE",response);
                        if("ok".equals(response)){

                            Toast.makeText(getApplicationContext(),"Paylaşım yapıldı",Toast.LENGTH_LONG).show();
                            etPaylasimYazisi.setText("");


                            paylasimlariGetir();



                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // olası network hatalarının yakalanabileceği metot
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("userid",""+sharedPreferences.getInt("userid",0));
                map.put("paylasim_yazisi",etPaylasimYazisi.getText().toString());
                return map;
                // İstekte bulunacağımız sayfaya göndereceğimiz parametreler.
            }
        };
        istekKuyrugu.add(istek);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        istekKuyrugu = Volley.newRequestQueue(getApplicationContext());

        etPaylasimYazisi = findViewById(R.id.etPaylasimYazisi);
        btnPaylas = findViewById(R.id.btnPaylas);
        listViewPaylasimlar = findViewById(R.id.listViewPaylasimlar);

        listViewPaylasimlar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                paylasimiKaldir(paylasimlar.get(position).getId());
                paylasimlariGetir();

                return false;
            }
        });

        btnPaylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paylasimYap();
            }
        });
    }
}
