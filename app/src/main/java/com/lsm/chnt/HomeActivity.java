package com.lsm.chnt;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;
import com.lsm.chnt.fragment.abcFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<Palabras> palabrsList;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(this);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        fillPalabras();
        final AutoCompleteTextView autoText=findViewById(R.id.actv);
        AutoCompletePalabrasAdapter adapter= new AutoCompletePalabrasAdapter(this,palabrsList);
        autoText.setAdapter(adapter);
        autoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView video=view.findViewById(R.id.idVideo);
                String videoString= (String) video.getText();
                Intent homeIntent= new Intent(HomeActivity.this,videoActivity.class);
                homeIntent.putExtra("VIDEO_ID", videoString);
                startActivity(homeIntent);
              //  Toast.makeText(parent.getContext(),videoString,Toast.LENGTH_LONG).show();

            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

      /*  AdView adView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, DonarActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;

        if (id == R.id.abc) {
            callABC();
        } else if (id == R.id.donar) {
            startActivity(new Intent(this, DonarActivity.class));
        } else if (id == R.id.numeros) {
            callNumeros();
        } else if (id == R.id.animales) {
            fragment= new abcFragment(R.array.animales,R.array.video_animales);
        } else if (id == R.id.casa_mueble) {
            fragment= new abcFragment(R.array.casa_muebles,R.array.video_casa_muebles);
        } else if (id == R.id.colores) {
            fragment= new abcFragment(R.array.colores,R.array.video_colores);
        } else if (id == R.id.cuestionamiento) {
            fragment= new abcFragment(R.array.preguntas,R.array.video_preguntas);
        } else if (id == R.id.saludos) {
            fragment= new abcFragment(R.array.saludos,R.array.video_saludos);
        } else if (id == R.id.animo) {
            fragment= new abcFragment(R.array.eanimos,R.array.video_eanimos);
        } else if (id == R.id.comida) {
            fragment= new abcFragment(R.array.comida,R.array.video_comida);
        } else if (id == R.id.verbos) {
            fragment= new abcFragment(R.array.verbos,R.array.video_verbos);
        } else if (id == R.id.personas) {
            fragment= new abcFragment(R.array.personas,R.array.video_personas);
        } else if (id == R.id.pronombres) {
            fragment= new abcFragment(R.array.pronombres,R.array.video_pronombres);
        }

        if(fragment!=null){
            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area,fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//esta seccion llena la lista con todas las secciones
    //hay que poner el for para cada seccion
    public void fillPalabras(){

        palabrsList= new ArrayList<>();
        Resources res = getResources();
        List<String> listDatos= Arrays.asList(res.getStringArray(R.array.animales));
        List<String> listVideos= Arrays.asList(res.getStringArray(R.array.video_animales));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        listDatos= Arrays.asList(res.getStringArray(R.array.casa_muebles));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_casa_muebles));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }

        //Colores
        listDatos= Arrays.asList(res.getStringArray(R.array.colores));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_colores));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }

        //Preguntas
        listDatos= Arrays.asList(res.getStringArray(R.array.preguntas));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_preguntas));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //Saludos
        listDatos= Arrays.asList(res.getStringArray(R.array.saludos));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_saludos));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //Animos
        listDatos= Arrays.asList(res.getStringArray(R.array.eanimos));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_eanimos));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //Comida
        listDatos= Arrays.asList(res.getStringArray(R.array.comida));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_comida));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //verbos
        listDatos= Arrays.asList(res.getStringArray(R.array.verbos));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_verbos));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //personas
        listDatos= Arrays.asList(res.getStringArray(R.array.personas));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_personas));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }
        //pronombres
        listDatos= Arrays.asList(res.getStringArray(R.array.pronombres));
        listVideos= Arrays.asList(res.getStringArray(R.array.video_pronombres));
        for(int i=0;i<listDatos.size();i++){
            palabrsList.add(new Palabras(listDatos.get(i),listVideos.get(i)));
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
         AutoCompleteTextView autoText=findViewById(R.id.actv);
        autoText.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void callABC(){
        Intent homeIntent= new Intent(HomeActivity.this,videoActivity.class);
        String video="NtXQRiAa9OE";
        homeIntent.putExtra("VIDEO_ID", video);
        startActivity(homeIntent);
    }
    public void callNumeros(){
        Intent homeIntent= new Intent(HomeActivity.this,videoActivity.class);
        String video="236OyfNDyvw";
        homeIntent.putExtra("VIDEO_ID", video);
        startActivity(homeIntent);
    }
}
