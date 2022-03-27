package fr.iut.duel.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duel.R;

import java.io.FileOutputStream;
import java.util.Objects;

import fr.iut.duel.manager.GameManager;
import fr.iut.duel.model.CharacterPlayable;
import fr.iut.duel.model.Chimiste;
import fr.iut.duel.model.Magicien;
import fr.iut.duel.model.Paladin;
import fr.iut.duel.model.Voleur;

public class ChoixpersoActivity extends AppCompatActivity {

    CharacterPlayable joueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixperso);

        EditText pseudo = findViewById(R.id.pseudo);
        TextView desc = findViewById(R.id.description);

        Button paladin = findViewById(R.id.paladin);
        Button magicien = findViewById(R.id.magicien);
        Button chimiste = findViewById(R.id.chimiste);
        Button voleur = findViewById(R.id.voleur);

        if(infoRenseigne())
            confirmer();

        /**
         * Action du bouton qui clear le texte pre enregistré dans l'editeur de texte
         */
        pseudo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                pseudo.getText().clear();
                return false;
            }
        });

        pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(infoRenseigne())
                    confirmer();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(infoRenseigne())
                    confirmer();
            }
        });
        /**
         * Action du bouton pour afficher le CharacterPlayable paladin
         * et le cree
         */
        paladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ImageView perso = findViewById(R.id.perso);
                    perso.setImageResource(R.drawable.paladin);
                    //création du perso ici pour avoir accès a ses différentes information pour les affichers
                    joueur = new Paladin(pseudo.getText().toString());
                    //Log.d("perso", joueur.toString());
                    affichageDesc();

            }
        });

        /**
         * Action du bouton pour afficher le CharacterPlayable magicien
         * et le cree
         */
        magicien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.mage);
                joueur = new Magicien(pseudo.getText().toString());
                affichageDesc();
            }
        });

        /**
         * Action du bouton pour afficher le CharacterPlayable chimiste
         * et le cree
         */
        chimiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.chimiste);
                joueur = new Chimiste(pseudo.getText().toString());
                affichageDesc();
            }
        });

        /**
         * Action du bouton pour afficher le CharacterPlayable voleur
         * et le cree
         */
        voleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView perso = findViewById(R.id.perso);
                perso.setImageResource(R.drawable.voleur);
                joueur = new Voleur(pseudo.getText().toString());
                affichageDesc();
            }
        });

    }

    /**
     * Appel la page de choix de niveau et sauvegarde du personnage
     */
    public void confirmer(){
        GameManager.getInstance().setJoueur(joueur);

        save(); // on save le perso

        Button confirmation = findViewById(R.id.confirmer);
        confirmation.setOnClickListener(view -> {
            Intent choixNiv = new Intent(ChoixpersoActivity.this, ChoixNiveauActivity.class);
            startActivity(choixNiv);
        });
    }

    /**
     * Affiche la description du personnage choisit
     */
    public void affichageDesc(){
        if(infoRenseigne())
            confirmer();
        TextView description = findViewById(R.id.description);
        description.setTextKeepState(joueur.getDescription());
    }

    /**
     *  Verifie si le pseudo a ete rentre
     * @return vrais si le pseudo est renseigne, faux si il ne l'ai pas
     */
    public boolean pseudoRenseigne(){
        EditText pseudo = findViewById(R.id.pseudo);
        String ps = pseudo.getText().toString();
        if ( ps != null && ps.length() != 0 && !ps.equals("Pseudo")){
            Log.d("pseudoRenseigne", ps);
            return true;
        }else{
            return false;
        }
    }

    /**
     * verifie si un personnage a été choisit
     * @return vrais si choisit, faux si pas choisit
     */
    public boolean jouerRenseigne(){
        if (Objects.isNull(joueur)){return false;}else{return true;}
    }

    /**
     * Verifie si le joueur a fait les action requise pour confirmer
     * @return
     */
    public boolean infoRenseigne(){
        String textButton = "Veuillez reseigner ";
        boolean ps = false, jo = false;
        Button confirmation = findViewById(R.id.confirmer);

        if (!pseudoRenseigne()){
            textButton = textButton + ",un pseudo";
        }else ps = true;
        if (!jouerRenseigne()){
            textButton = textButton + ",un personnage";
        }else jo = true;

        if (jo && ps){
            confirmation.setText("Confirmer");
            return true;
        }

        // insert un text demandant choisir perso
        confirmation.setText(textButton);
        return false;
    }

    public void save(){
        FileOutputStream outputStream= null;
        try {
            outputStream = openFileOutput("monSave",MODE_PRIVATE);
            String audio_name=GameManager.getInstance().getJoueur().getClass().getName()+' '+GameManager.getInstance().getJoueur().getPseudo();
            outputStream.write(audio_name.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}