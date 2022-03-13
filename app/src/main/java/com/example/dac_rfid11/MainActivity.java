package com.example.dac_rfid11;

import static com.example.dac_rfid11.ChargerActivity.liste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity: ";

    private Spinner spCategorie;
    private TextView tvHello;

    private RadioGroup rgCateg1, rgCateg2;
    private RadioButton rb2, rb3, rb4, rb5, rb6;


    private void init() {
        tvHello = findViewById(R.id.tvHello);
    }

    private void initRB() {
        rgCateg1 = findViewById(R.id.rgCateg1);
        rgCateg2 = findViewById(R.id.rgCateg2);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        rb6 = findViewById(R.id.rb6);

        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
        rb4.setVisibility(View.INVISIBLE);
        rb5.setVisibility(View.INVISIBLE);
        rb6.setVisibility(View.INVISIBLE);

        Integer nbIndex = liste.size();     //import depuis charegrActivity -> public static ArrayList<String> liste
        Log.i(TAG, "Nb Index: " + nbIndex);

        //Il n'y a que 5 buttons
        Integer idx = 0;
        for (String categ:liste) {
            idx++;

            switch (idx) {
                case 1 :
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(categ);
                    break;
                case 2 :
                    rb3.setVisibility(View.VISIBLE);
                    rb3.setText(categ);
                    break;
                case 3 :
                    rb4.setVisibility(View.VISIBLE);
                    rb4.setText(categ);
                    break;
                case 4 :
                    rb5.setVisibility(View.VISIBLE);
                    rb5.setText(categ);
                    break;
                case 5 :
                    rb6.setVisibility(View.VISIBLE);
                    rb6.setText(categ);
                    break;
            }

        }

    }

    /*** Logout authentification ****/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, Menu.NONE, "Logout");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == 1){
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            FirebaseAuth.getInstance().signOut();
            return true;
        }
        else
            return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //Intent i2 = getIntent();
        //liste = i2.getStringArrayListExtra ("listeCateg");

        initRB();

        // Traitement spécifique pour la liste déroulante des Catégories
        spCategorie = findViewById(R.id.spinnerCategory);

        ArrayAdapter liste_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, liste);
        liste_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategorie.setAdapter(liste_adapter);
    }

    public void btnVal(View v1) {
        String categorie = String.valueOf(spCategorie.getSelectedItem()).trim();
        Log.i(TAG, "btnVal: " + categorie);
        tvHello.setText(categorie);
    }


    public void btnCategorie(@NonNull View view) {
        // Clear any checks from both groups:
        rgCateg1.clearCheck();
        rgCateg2.clearCheck();

        // Manually set the check in the newly clicked radio button:
        ((RadioButton) view).setChecked(true);
        String s1 = ((RadioButton) view).getText().toString();
        tvHello.setText(s1);
        Log.i(TAG, " Intituel "+s1);

        // Perform any action desired for the new selection:
        switch (view.getId()) {
            case R.id.rbTout:
                break;

            case R.id.rb2:
                Log.i(TAG, " rb2 ");
                break;

            case R.id.rb3:
                Log.i(TAG, " rb3 ");
                break;

            case R.id.rb4:
                Log.i(TAG, " rb4 ");
                break;

            case R.id.rb5:
                Log.i(TAG, " rb5 ");
                break;
            case R.id.rb6:
                Log.i(TAG, " rb6 ");
                break;
        }
    }

    public void btnRet(View v1) {
        this.startActivity(new Intent(MainActivity.this, ChargerActivity.class));
    }

}