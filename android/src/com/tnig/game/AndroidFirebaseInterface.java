package com.tnig.game;

import android.util.Log;


import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tnig.game.model.networking.Network;
import com.tnig.game.model.networking.PlayerData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AndroidFirebaseInterface implements Network {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference levelRef;
    private DatabaseReference playerRef;
    private ArrayList<PlayerData> players;
    private Map<Integer, ArrayList<PlayerData>> playerMap;
    private ArrayList<ArrayList<String>> scores;

    public AndroidFirebaseInterface() {
        database = FirebaseDatabase.getInstance("https://nearly-impossible-game-default-rtdb.europe-west1.firebasedatabase.app/"); // Rootnode.
        myRef = database.getReference("highscore");
        playerMap = new HashMap<>();
        updateHighscore();
    }

    @Override
    public void someFunction() {
        System.out.println("Just som android function.");
    }

    /**
     * Creates a new user entity, with name and score, in the highscore database.
     *
     * @param levelNum Decides which levels higscorelist one want to add the user to.
     * @param firebasePlayer The player that one want to post to the database.
     */
    @Override
    public void pushHighscore(int levelNum, PlayerData firebasePlayer) {
        String name = firebasePlayer.getName();
        int score = firebasePlayer.getScore();
        levelRef = myRef.child("level" + levelNum); //Points at numbered levelnode.
        //Create new user entity, with name and score.
        playerRef = levelRef.push();
        playerRef.child("name").setValue(name);
        playerRef.child("score").setValue(score);
        updateHighscore();
    }

    /**
     * Updates the local highscorelist (getHighcore()) from the database.
     * The methods need to be called to update, it does NOT do this automatically.
     */
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
        //Iterate over the levels.
        int count = 0;
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            count++;
            players = new ArrayList<>();
            // Iterate over the player records in the level.
            for (DataSnapshot user : ds.getChildren()) {
                PlayerData player = new PlayerData();
                player.setName(user.child("name").getValue(String.class));
                player.setScore(user.child("score").getValue(Integer.class));

                players.add(player);
            }
            if (playerMap.containsKey(count)) playerMap.replace(count, players);
            else playerMap.put(count, players);
        }
    }

    /**
     * NB! This method will only return a non-empty table, if the updateHighcore method has had enough time to retrieve data from the database.
     * E.g. this method should not be called immediately after updateHighscore() has been called.
     * Return the highscorelist as a table with String values: [(name1,score1),(name2,score2),...,(nameN, scoreN)].
     * @param levelNum Specifies for which level it should return the highscores from.
     * @return scores
     */
    @Override
    public ArrayList<ArrayList<String>> getHighScore(Integer levelNum) {
        scores = new ArrayList<>();
        ArrayList<String> internalScores;
        if(playerMap.containsKey(levelNum)) {
            for (PlayerData player : playerMap.get(levelNum)) {
                internalScores = new ArrayList<>();
                internalScores.add(player.getName());
                internalScores.add("" + player.getScore());
                scores.add(internalScores);
            }
        }
        return scores;
    }
}