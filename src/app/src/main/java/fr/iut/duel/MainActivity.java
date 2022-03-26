package fr.iut.duel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duel.R;

import java.io.FileInputStream;

import fr.iut.duel.manager.GameManager;
import fr.iut.duel.model.CharacterPlayable;
import fr.iut.duel.model.Chimiste;
import fr.iut.duel.model.Magicien;
import fr.iut.duel.model.Paladin;
import fr.iut.duel.model.Voleur;

public class MainActivity extends AppCompatActivity {

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        Button nPartie = findViewById(R.id.nPartie);
        Button continuer = findViewById(R.id.continuer);
        Button bouche = findViewById(R.id.bouche);
        Button testrapide = findViewById(R.id.testRapide);

        nPartie.setOnClickListener(view -> {
            Intent choixPerso = new Intent(MainActivity.this, ChoixpersoActivity.class);
            startActivity(choixPerso);
        });

        if (GameManager.getInstance().getJoueur() != null) {
            continuer.setOnClickListener(view -> {
                Intent choixNiv = new Intent(MainActivity.this, ChoixNiveauActivity.class);
                startActivity(choixNiv);
            });
            continuer.setVisibility(View.VISIBLE);
        }else{
            read();
            if (GameManager.getInstance().getJoueur() != null) {
                continuer.setOnClickListener(view -> {
                    Intent choixNiv = new Intent(MainActivity.this, ChoixNiveauActivity.class);
                    startActivity(choixNiv);
                });
                continuer.setVisibility(View.VISIBLE);
            }
            else
                continuer.setVisibility(View.GONE);
        }

        testrapide.setOnClickListener(view -> {
            CharacterPlayable p = new Paladin("Testeur");
            GameManager.getInstance().setJoueur(p);
            Intent choixNiv = new Intent(MainActivity.this, ChoixNiveauActivity.class);
            startActivity(choixNiv);
        });

        bouche.setOnClickListener(view ->{
            Intent bouchetrou = new Intent(MainActivity.this, BoucheTrouActivity.class);
            startActivity(bouchetrou);
        });


    }
    public void read(){
        String value = null;
        String [] vP = null;
        try {
            FileInputStream inputStream=openFileInput("monSave");
            StringBuilder stringb= new StringBuilder();
            int content;
            while ((content=inputStream.read())!=-1){
                value = String.valueOf(stringb.append((char)content));
            }
        }catch (Exception e){
            Log.d("Error", "read: e");
        }
        vP = value.split(" ");
        switch (vP[0]){
            case "fr.iut.duel.model.Paladin":
                GameManager.getInstance().setJoueur(new Paladin(vP[1]));
                break;

            case "fr.iut.duel.model.Chimiste":
                GameManager.getInstance().setJoueur(new Chimiste(vP[1]));
                break;

            case "fr.iut.duel.model.Magicien":
                GameManager.getInstance().setJoueur(new Magicien(vP[1]));
                break;

            case "fr.iut.duel.model.Voleur":
                GameManager.getInstance().setJoueur(new Voleur(vP[1]));
                break;
        }


        //fr.iut.duel.model.Paladin
    }
}
