package fr.iut.duel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duel.R;

import fr.iut.duel.manager.GameManager;
import fr.iut.duel.model.CharacterPlayable;
import fr.iut.duel.model.Paladin;

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
        }else
            continuer.setVisibility(View.GONE);

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
}
