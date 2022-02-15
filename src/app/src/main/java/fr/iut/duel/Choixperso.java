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
        TextView desc = findViewById(R.id.description);

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
                    Log.d("perso", joueur.toString());
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
    // la c'est la limite

    /**
     * Affiche un message en popup
     * @param msg : message a afficher
     */


    /**
     * Verifie si le pseudo du joueur est renseigne
     * Il faut renseigne le pseudo avant de choisir un personnage
     * @return true : si il est renseigne, false : si il n'est pas renseigne
     */
    public boolean verificationPseudo(EditText pseudo){
        Log.d("verif", String.valueOf(pseudo.getText()));
        if(String.valueOf(pseudo.getText()) == "Pseudo") {
            Log.d("veriff", "false");
            return false;
        }
        Log.d("verift", "true");
        return true;
    }

    /**
     * Verifie si on a choisit un personnage puis le sauvegarde en temps que joueur et charge la page de choix de niveau.
     * Sinon utilise popUp
     * @throws IOException
     */
    /*
    public void valider() throws IOException {
        if (Objects.isNull(p)){
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