package com.example.duel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuil);

        Button nPartie = findViewById(R.id.nPartie);
        Button continuer = findViewById(R.id.continuer);
        Button exit = findViewById(R.id.exit);

        nPartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choixPerso = new Intent(MainActivity.this, Choixperso.class);
                startActivity(choixPerso);
            }
        });
    }
}
