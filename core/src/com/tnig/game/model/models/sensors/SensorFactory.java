package com.tnig.game.model.models.sensors;

import com.tnig.game.controller.managers.EventManager;
import com.tnig.game.model.models.ObjectProperties;
import com.tnig.game.model.models.interfaces.Model;
import com.tnig.game.model.models.interfaces.ModelType;
import com.tnig.game.model.physics_engine.Engine;

public class SensorFactory {

    public static Model createModel(EventManager eventManager, Engine engine,
                                    float x, float y, float width, float height,
                                    ObjectProperties properties, ModelType modelType){
        SensorType type = (SensorType) modelType;
        switch (type){
            case DEATH_SENSOR:
                return new DeathSensor(eventManager, engine, x, y, width, height, properties, type);
            case FINISH_LINE:
                return new FinishLine(eventManager, engine, x, y, width, height, properties, type);
            default:
                throw new IllegalArgumentException("Not created model for type yet: " + type);
        }
    }
}
