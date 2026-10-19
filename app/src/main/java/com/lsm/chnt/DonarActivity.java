package com.lsm.chnt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonarActivity extends AppCompatActivity {

    private static final String PAYPAL_URL = "https://paypal.me/LSMDiccionario";
    private static final String MONEDA = "MXN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.donar_20).setOnClickListener(v -> abrirPayPal(20));
        findViewById(R.id.donar_50).setOnClickListener(v -> abrirPayPal(50));
        findViewById(R.id.donar_100).setOnClickListener(v -> abrirPayPal(100));
        findViewById(R.id.donar_otro).setOnClickListener(v -> abrirPayPal(0));
    }

    // monto <= 0 abre PayPal.me para que el usuario escriba la cantidad.
    private void abrirPayPal(int monto) {
        String url = monto > 0 ? PAYPAL_URL + "/" + monto + MONEDA : PAYPAL_URL;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.donar_sin_navegador, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
