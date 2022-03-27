package com.tnig.game;

import android.util.Log;
import androidx.annotation.NonNull;

import com.badlogic.gdx.Gdx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tnig.game.model.networking.FirebaseInterface;
import com.tnig.game.model.networking.FirebasePlayer;

import java.util.LinkedHashMap;
import java.util.Map;

public class AndroidFirebaseInterface implements FirebaseInterface {
    FirebaseDatabase database;
    DatabaseReference levelRef;
    DatabaseReference playerRef;
    DatabaseReference scoreRef;

    public AndroidFirebaseInterface() {
        database = FirebaseDatabase.getInstance("https://nearly-impossible-game-default-rtdb.europe-west1.firebasedatabase.app/"); //rotnoden
    }

    @Override
    public void pushHighscore(int levelNum, FirebasePlayer firebasePlayer) {
        String name = firebasePlayer.getName();
        int score = firebasePlayer.getScore();
        levelRef = database.getReference("level" + levelNum); //Peker på level nummer levelNum.
        playerRef = levelRef.push();
        playerRef.setValue("" + name + "," + score);
        Gdx.app.log("test","kjørt" + levelRef.getKey());
    }

    @Override
    public void SetOnValueChangedListener(FirebasePlayer firebasePlayer) {
        // Read from the database
        levelRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child(firebasePlayer.getName()).getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}