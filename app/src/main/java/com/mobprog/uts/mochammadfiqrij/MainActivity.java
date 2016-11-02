package com.mobprog.uts.mochammadfiqrij;

/**
 * Created by Mobprog on 02/11/2016.
 *
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SessionManager session;
    protected ListView lv;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    ArrayList<String> mylistnama;
    ArrayList<String> mylistgambar;
    String[] Pil;
    String[] Ltn;
    String[] Gbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        lv = (ListView) findViewById(R.id.lv);

        Pil = new String[] {"Bubble Rainbow Cake", "Chocolate Cake", "Coldstone Cake", "Rainbow Cake", "Digger Cake"};
        Gbr = new String[] {
                Integer.toString(R.drawable.bubble_rainbow),
                Integer.toString(R.drawable.chocolate),
                Integer.toString(R.drawable.coldstone),
                Integer.toString(R.drawable.rainbow),
                Integer.toString(R.drawable.digger),};
        lv.setOnItemClickListener(this);
        mylist = new ArrayList<HashMap<String,String>>();
        mylistnama = new ArrayList();
        mylistgambar = new ArrayList();

        for (int i = 0; i < Pil.length; i++){
            map = new HashMap<String, String>();
            map.put("list", Pil[i]);
            map.put("gbr", Gbr[i]);
            mylist.add(map);
        }

        for (int i = 0; i < Pil.length; i++){

            mylistnama.add(Pil[i]);
        }

        for (int i = 0; i < Pil.length; i++){

            mylistgambar.add(Gbr[i]);
        }

        Adapter = new SimpleAdapter(this, mylist, R.layout.layout_isi_lv,
                new String[] {"list","gbr"}, new int[] {R.id.tv_nama, R.id.imV});
        lv.setAdapter(Adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(),PesanActivity.class);
        Bundle b = new Bundle();
        String txt = (String)mylistnama.get(position);
        String txt2 = (String)mylistgambar.get(position);

        //Menyisipkan tipe data String ke dalam obyek bundle
        b.putString("namakue", txt);
        b.putString("gambarkue",txt2);
        //Menambahkan bundle intent
        intent.putExtras(b);

        //memulai Activity kedua
        startActivity(intent);

    }

    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
    }
}
