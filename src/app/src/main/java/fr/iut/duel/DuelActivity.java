package fr.iut.duel;

import static java.lang.Thread.sleep;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duel.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.iut.duel.manager.CombatManager;
import fr.iut.duel.manager.GameManager;
import fr.iut.duel.util.Action;

public class DuelActivity extends AppCompatActivity {
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duel);

        // INITIALISATION
        // des pseudo
        TextView psJ = findViewById(R.id.joueurPseudo);
        psJ.setText(GameManager.getInstance().getJoueur().getPseudo());
        TextView psB = findViewById(R.id.botPseudo);
        psB.setText(GameManager.getInstance().getAdversaire().getPseudo());

        // des image de perso
        ImageView perso = findViewById(R.id.joueurImage);
        perso.setImageResource(GameManager.getInstance().getJoueur().getImage());
        ImageView bot = findViewById(R.id.botImage);
        bot.setImageResource(GameManager.getInstance().getAdversaire().getImage());
        //animation vue

        //des barres de vies
        ProgressBar viePerso = findViewById(R.id.joueurVie);
        viePerso.setMax(GameManager.getInstance().getJoueur().getVie());
        viePerso.setMin(0);
        viePerso.setProgress(GameManager.getInstance().getJoueur().getVie());
        // faut mettre des observable pour changer la vie

        ProgressBar vieBot = findViewById(R.id.botVie);
        vieBot.setMax(GameManager.getInstance().getJoueur().getVie());
        vieBot.setMin(0);
        vieBot.setProgress(GameManager.getInstance().getAdversaire().getVie());
        //différents button
        Button attaquer = findViewById(R.id.att);
        Button defendre = findViewById(R.id.def);

        CombatManager CbM = new CombatManager();
        attaquer.setOnClickListener(view -> {
            CbM.mancheExecution(GameManager.getInstance().getJoueur(),GameManager.getInstance().getAdversaire(),5,Action.ATTAQUE);
            //deplacementJoueur(perso);
        });



    }

    // a mettre dans la boucle de jeux je crois
    public void deplacementJoueur(ImageView j){
        ObjectAnimator.ofFloat(j,"translationY",  -400).setDuration(500).start();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ObjectAnimator.ofFloat(j,"translationY",  0).setDuration(500).start();
            }
        }, 500);
    }

}
