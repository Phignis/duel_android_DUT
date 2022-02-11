package fr.iut.duel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duel.R;

import fr.iut.duel.model.Chimiste;
import fr.iut.duel.model.Magicien;
import fr.iut.duel.model.Personnage;
import fr.iut.duel.model.Paladin;
import fr.iut.duel.model.Voleur;

public class Choixperso extends AppCompatActivity {

    Personnage joueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixperso);

        EditText pseudo = findViewById(R.id.pseudo);

        Button paladin = findViewById(R.id.paladin);
        Button magicien = findViewById(R.id.magicien);
        Button chimiste = findViewById(R.id.chimiste);
        Button voleur = findViewById(R.id.voleur);
        Button confirmation = findViewById(R.id.confirmer);

        pseudo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pseudo.getText().clear();
                return false;
            }
        });
        paladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.paladin);
                //création du perso ici pour avoir accès a ses différentes information pour les affichers
                joueur = new Paladin(pseudo.getText().toString());
                Log.d("perso",joueur.toString() );
                affichageDesc();
            }
        });
        magicien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.mage);
                joueur = new Magicien(pseudo.getText().toString());
                affichageDesc();
            }
        });
        chimiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.chimiste);
                joueur = new Chimiste(pseudo.getText().toString());
                affichageDesc();
            }
        });
        voleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.voleur);
                joueur = new Voleur(pseudo.getText().toString());
                affichageDesc();
            }
        });
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void affichageDesc(){
        TextView description = findViewById(R.id.description);
        description.setTextKeepState(joueur.getDescription());
    }
}