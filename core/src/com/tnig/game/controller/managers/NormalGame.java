package com.tnig.game.controller.managers;

import com.tnig.game.controller.game_objects.Controller;
import com.tnig.game.model.physics_engine.Engine;

import java.util.ArrayList;
import java.util.List;

//TODO: FINISH CLASS
public class NormalGame implements GameManager{
    private List<Controller> controllers = new ArrayList<>();

    @Override
    public void initGame(Engine engine) {

        initObstacleControllers();
        initBlockControllers();
        initPlayerControllers();

    }

    private void initObstacleControllers(){
        // TODO: IMPLEMENT
    }


    private void initPlayerControllers(){
        // TODO: IMPLEMENT
    }


    private void initBlockControllers(){
        // TODO: IMPLEMENT
    }


}
