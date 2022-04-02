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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AndroidFirebaseInterface implements FirebaseInterface {
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference levelRef;
    DatabaseReference playerRef;
    ArrayList<FirebasePlayer> players;
    Map<Integer, ArrayList<FirebasePlayer>> playerMap;

    public AndroidFirebaseInterface() {
        database = FirebaseDatabase.getInstance("https://nearly-impossible-game-default-rtdb.europe-west1.firebasedatabase.app/"); // Rootnode.
        myRef = database.getReference("highscore");
        playerMap = new HashMap<>();
    }

    /**
     * Creates a new user entity, with name and score, in the highscore database.
     *
     * @param levelNum Decides which levels higscorelist one want to add the user to.
     * @param firebasePlayer The player that one want to post to the database.
     */
    @Override
    public void pushHighscore(int levelNum, FirebasePlayer firebasePlayer) {
        String name = firebasePlayer.getName();
        int score = firebasePlayer.getScore();
        levelRef = myRef.child("level" + levelNum); //Points at numbered levelnode.
        //Create new user entity, with name and score.
        playerRef = levelRef.push();
        playerRef.child("name").setValue(name);
        playerRef.child("score").setValue(score);
        Gdx.app.log("test","kjørt" + levelRef.getKey());
    }

    @Override
    public void updateHighscore() {
        // Read from the database.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.exists()) {
                    showData(dataSnapshot);
                    Log.d("onDataChanged", "players: " + players);
                }
                else Log.d("onDataChange", "not exist");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        playerMap = new HashMap<>();

        //Iterate over the levels.
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            players = new ArrayList<>();
            int count = (int) ds.getChildrenCount();

            // Iterate over the player records in the level.
            for (int i = 1; i <= count; i++) {
                FirebasePlayer player = new FirebasePlayer();
                player.setName(ds.child("level" + i).child("name").getValue(FirebasePlayer.class).getName());
                player.setScore(ds.child("level" + i).child("score").getValue(FirebasePlayer.class).getScore());

                //Display all the information
                Log.d("player", "showdata: name:" + player.getName());
                Log.d("player", "showdata: score:" + player.getScore());

                players.add(player);
                playerMap.put(i, players);
            }

        }
    }

    @Override
    public ArrayList getHighScore(int levelNum) {
        ArrayList<FirebasePlayer> scores = new ArrayList<>();
        if(playerMap.containsKey(levelNum)) {
            for (FirebasePlayer player : playerMap.get(levelNum)) {
                scores.add(player);
            }
        }
        return scores;
    }
}