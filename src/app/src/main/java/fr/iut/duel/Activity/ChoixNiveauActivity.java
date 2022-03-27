package fr.iut.duel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duel.R;

import fr.iut.duel.manager.GameManager;
import fr.iut.duel.model.Chimiste;
import fr.iut.duel.model.Paladin;
import fr.iut.duel.model.CharacterPlayable;
import fr.iut.duel.model.Voleur;

public class ChoixNiveauActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixniveau);

        Button niv1 = findViewById(R.id.niv1);
        Button niv2 = findViewById(R.id.niv2);
        Button niv3 = findViewById(R.id.niv3);
        Button mnPrincipal = findViewById(R.id.mnp);

        niv1.setOnClickListener(view -> demarrerNiv(1));
        niv2.setOnClickListener(view -> demarrerNiv(2));
        niv3.setOnClickListener(view -> demarrerNiv(3));

        TextView pseudo = findViewById(R.id.joueurPseudo);

        mnPrincipal.setOnClickListener(view -> {
            Intent mnp = new Intent(ChoixNiveauActivity.this, MainActivity.class);
            startActivity(mnp);
        });

        pseudo.setText(GameManager.getInstance().getJoueur().getPseudo());

        pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                GameManager.getInstance().getJoueur().setPseudo(pseudo.getText().toString());
            }
        });

    }

    /**
     * demarre un niveau spéciale suivant le niveau passé en paramettre
     * @param number = niveau voulu
     */
    public void demarrerNiv(int number){
        CharacterPlayable adv;
        Intent niv;
        switch (number){
            case 1:
                adv = new Paladin("Le fort");
                GameManager.getInstance().setAdversaire(adv);
                 niv = new Intent(ChoixNiveauActivity.this, DuelActivity.class);
                startActivity(niv);
                break;
            case 2:
                adv = new Voleur("L'egorgeur");
                GameManager.getInstance().setAdversaire(adv);
                niv = new Intent(ChoixNiveauActivity.this, DuelActivity.class);
                startActivity(niv);
                break;
            case 3:
                adv = new Chimiste("l'Empoisonneur");
                GameManager.getInstance().setAdversaire(adv);
                niv = new Intent(ChoixNiveauActivity.this, DuelActivity.class);
                startActivity(niv);
                break;
        }
    }


}
