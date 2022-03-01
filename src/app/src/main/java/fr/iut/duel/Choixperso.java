package fr.iut.duel;

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

import java.util.Objects;

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
        TextView desc = findViewById(R.id.description);

        Button paladin = findViewById(R.id.paladin);
        Button magicien = findViewById(R.id.magicien);
        Button chimiste = findViewById(R.id.chimiste);
        Button voleur = findViewById(R.id.voleur);

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
                if (jouerRenseigne()){
                    confirmer();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                infoRenseigne();

            }
        });
        /**
         * Action du bouton pour afficher le Personnage paladin
         * et le cree
         */
        paladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificationPseudo(pseudo)) {
                    ImageView perso = findViewById(R.id.perso);
                    perso.setImageResource(R.drawable.paladin);
                    //création du perso ici pour avoir accès a ses différentes information pour les affichers
                    joueur = new Paladin(pseudo.getText().toString());
                    //Log.d("perso", joueur.toString());
                    affichageDesc();
                }
            }
        });

        /**
         * Action du bouton pour afficher le Personnage magicien
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
         * Action du bouton pour afficher le Personnage chimiste
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
         * Action du bouton pour afficher le Personnage voleur
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
     * Appel la page de choix de niveau
     */
    public void confirmer(){
        Button confirmation = findViewById(R.id.confirmer);
        confirmation.setOnClickListener(view ->{
            Intent choixNiv = new Intent(Choixperso.this, ChoixNiveau.class);
            startActivity(choixNiv);
        });
    }

    /**
     * Affiche la description du personnage choisit
     */
    public void affichageDesc(){
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
        if ( ps != null && ps.length() != 0){
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
        if (!pseudoRenseigne()){
            // insert un text demandant pseudo
        }
        if (!jouerRenseigne()){
            // insert un text demandant choisir perso
        }
        return false;
    }

    /**
     * Verifie si le pseudo du joueur est renseigne
     * Il faut renseigne le pseudo avant de choisir un personnage
     * @return true : si il est renseigne, false : si il n'est pas renseigne
     */
    /*
    public boolean verificationPseudo(EditText pseudo){
        Log.d("verif", String.valueOf(pseudo.getText()));
        String s = String.valueOf(pseudo.getText());
        if(String.valueOf(pseudo.getText()) == s) {
            Log.d("verifFalse", "false");
            return false;
        }
        Log.d("verifTRUE", "true");
        return true;
    }
*/
    /**
     * Verifie si on a choisit un personnage puis le sauvegarde en temps que joueur et charge la page de choix de niveau.
     * Sinon utilise popUp
     */

    /*
    public void valider() {
        if (Objects.isNull(joueur)){
            popUp("Veuillez choisir un personnage");
        }else{
            GameManager.getInstance().setJoueur(p);
            GameManager.getInstance().ChoixNiveau();
        }
    }
    */

    /**
     * Charge la page d'acceuil
     */

}