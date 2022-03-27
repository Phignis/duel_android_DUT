package fr.iut.duel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.duel.R;

import java.util.ArrayList;

import fr.iut.duel.FragmentPersos;
import fr.iut.duel.model.Chimiste;
import fr.iut.duel.model.Magicien;
import fr.iut.duel.model.Paladin;
import fr.iut.duel.model.Voleur;

public class BoucheTrouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bouchetrou);

        ArrayList<String> perso = new ArrayList<String>();
        perso.add(new Magicien("Magicien").getPseudo());
        perso.add(new Chimiste("Chimiste").getPseudo());
        perso.add(new Voleur("Voleur").getPseudo());
        perso.add(new Paladin("Paladin").getPseudo());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,perso);
        //ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, android.R.layout.simple_list_item_1,perso);

        ListView l = findViewById(R.id.list);
        l.setAdapter(adapter);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragmentImage, FragmentPersos.class,null).commit();

    }
}
