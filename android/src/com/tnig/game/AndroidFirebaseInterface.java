package com.tnig.game;

import android.util.Log;


import androidx.annotation.NonNull;


import com.badlogic.gdx.Gdx;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tnig.game.model.models.players.Player;
import com.tnig.game.model.networking.NetworkService;
import com.tnig.game.model.networking.PlayerData;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class AndroidFirebaseInterface implements NetworkService {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference levelRef;
    private DatabaseReference playerRef;
    private ArrayList<PlayerData> players;
    private Map<Integer, ArrayList<PlayerData>> mapScores;
    private ArrayList<Integer> levels;

    public AndroidFirebaseInterface() {
        database = FirebaseDatabase.getInstance("https://nearly-impossible-game-default-rtdb.europe-west1.firebasedatabase.app/"); // Rootnode.
        myRef = database.getReference("highscore");
        mapScores = new HashMap<>();
        updateHighscore();
    }

    /**
     * Creates a new user entity, with name and score, in the highscore database.
     *
     * @param data The PlayerData that one want to post to the database.
     */
    @Override
    public void pushHighscore(PlayerData data) {
        String name = data.getName();
        int mapNum = data.getMapNumber();
        int score = data.getScore();
        levelRef = myRef.child("level" + mapNum); // Points at numbered levelnode.

        //Create new user entity, with name and score.
        playerRef = levelRef.push();
        playerRef.child("name").setValue(name);
        playerRef.child("score").setValue(score);
        updateHighscore();
    }

    /**
     * Updates the local highscorelist (getHighscores()) from the database.
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
                    //Iterate over the levels.
                    int mapNum = 0;

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        mapNum++;
                        players = new ArrayList<>();

                        // Iterate over the player records in the level.
                        for (DataSnapshot user : ds.getChildren()) {
                            if (user.child("name").getValue(String.class) != null && user.child("score").getValue(Integer.class) != null) {
                                PlayerData player = new PlayerData();

                                player.setName(user.child("name").getValue(String.class));
                                player.setScore(user.child("score").getValue(Integer.class));

                                players.add(player);
                            }
                        }

                        if (mapScores.containsKey(mapNum)) {
                            mapScores.replace(mapNum, players);
                        }
                        else {
                            mapScores.put(mapNum, players);
                        }
                    }
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

    @Override
    public ArrayList<Integer> getLevels() {
        levels = new ArrayList<>();
        for (int key : mapScores.keySet()) {
            levels.add(key);
        }
        return levels;
    }

    /**
     * NB! This method will only return a non-empty table, if the updateHighcore method has had enough time to retrieve data from the database.
     * E.g. this method should not be called immediately after updateHighscore() has been called.
     * Return the highscorelist as a table with String values: [(name1,score1),(name2,score2),...,(nameN, scoreN)].
     * @param levelNum Specifies for which level it should return the highscores from.
     * @return scores
     */
    @Override
    public ArrayList<PlayerData> getHighScores(Integer levelNum) {
        if(mapScores.containsKey(levelNum)) {
            // Sort the PlayerDatas by score
            Collections.sort(mapScores.get(levelNum), Comparator.comparingInt(PlayerData::getScore));
            return mapScores.get(levelNum);
        }
        return new ArrayList<>();
    }
}