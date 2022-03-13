package com.example.dac_rfid11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChargerActivity extends AppCompatActivity {
    private static final String TAG = "ChargerActivity: ";

    private FirebaseFirestore db;
    public static ArrayList<String> liste;     //Permet de charger une seul fois à l'initialisation de la classe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charger);

        if(liste==null || liste.isEmpty()) {
            liste = new ArrayList<String>();
            db = FirebaseFirestore.getInstance();

            db.collection("Categorie")
                    .orderBy("Index")   //String non integer
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot qds) {
                            if (qds.isEmpty()) {
                                Log.d(TAG, "onSuccess: LIST EMPTY 1");

                            } else {

                                for (DocumentSnapshot documentSnapshot : qds) {
                                    if (documentSnapshot.exists()) {
                                        Log.d(TAG, "onSuccess: DOCUMENT" + documentSnapshot.getId() + " ; " + documentSnapshot.getData());
                                        String s1 = documentSnapshot.getString("Intitule");
                                        liste.add(s1);
                                        Log.d(TAG, "onSuccess: Intitule " + s1);

                                    }
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else {
            Log.d(TAG, "Déjà Charger ");
    }

    }

    public void btnSuite(View v1) {
        Intent i3 = new Intent(ChargerActivity.this, MainActivity.class);
        //i3.putExtra("listeCateg", liste);
        this.startActivity(i3);
    }
}