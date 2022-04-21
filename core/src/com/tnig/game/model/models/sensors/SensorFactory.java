package com.tnig.game.model.models.sensors;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.Model;
import com.tnig.game.model.models.ModelType;
import com.tnig.game.model.models.coins.Coin;
import com.tnig.game.model.models.coins.CoinType;
import com.tnig.game.model.physics_engine.Engine;

public class SensorFactory {

    public static Model createModel(EventManager eventManager, Engine engine,
                                    float x, float y, float width, float height, ModelType modelType){
        SensorType type = (SensorType) modelType;
        switch (type){
            case DEATH_SENSOR:
                return new DeathSensor(eventManager, engine, x, y, width, height, type);
            case FINISH_LINE:
                throw new IllegalArgumentException("Not created model for Mock");
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
